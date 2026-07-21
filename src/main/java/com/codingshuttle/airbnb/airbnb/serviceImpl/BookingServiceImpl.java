package com.codingshuttle.airbnb.airbnb.serviceImpl;

import com.codingshuttle.airbnb.airbnb.dto.BookingDto;
import com.codingshuttle.airbnb.airbnb.dto.BookingRequest;
import com.codingshuttle.airbnb.airbnb.dto.GuestDto;
import com.codingshuttle.airbnb.airbnb.entity.*;
import com.codingshuttle.airbnb.airbnb.entity.enums.BookingStatus;
import com.codingshuttle.airbnb.airbnb.exception.ResourceNotFoundException;
import com.codingshuttle.airbnb.airbnb.repository.BookingRepository;
import com.codingshuttle.airbnb.airbnb.repository.HotelRepository;
import com.codingshuttle.airbnb.airbnb.repository.InventoryRepository;
import com.codingshuttle.airbnb.airbnb.repository.RoomRepository;
import com.codingshuttle.airbnb.airbnb.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final ModelMapper modelMapper;
    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final InventoryRepository inventoryRepository;


    @Override
    @Transactional
    public BookingDto initialsBooking(BookingRequest bookingRequest) {

      Hotel hotel= hotelRepository.findById(bookingRequest.getHotelId()).orElseThrow(() ->
        new ResourceNotFoundException("Hotel not found with Id:"+bookingRequest.getHotelId()));

        Room room= roomRepository.findById(bookingRequest.getRoomId()).orElseThrow(() ->
                new ResourceNotFoundException("Room not found with Id:"+bookingRequest.getRoomId()));

        List<Inventory> inventoryList =inventoryRepository.findAndLockAvailableInventory(room.getId(),
                bookingRequest.getCheckInDate(),bookingRequest.getCheckOutDate(),bookingRequest.getRoomCount());

        long daysCount = ChronoUnit.DAYS.between(bookingRequest.getCheckInDate(),bookingRequest.getCheckOutDate()) +1;

        if(inventoryList.size()!=daysCount){
            throw new IllegalStateException("Room is not available anymore");
        }
        for(Inventory inventory:inventoryList){
            inventory.setReservedCount(inventory.getReservedCount()+ bookingRequest.getRoomCount());
        }
        inventoryRepository.saveAll(inventoryList);
        User user =new User();
        user.setId(1L);

        Booking booking =Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .checkInDate(bookingRequest.getCheckInDate())
                .checkOutDate(bookingRequest.getCheckOutDate())
                .user(user)
                .roomCount(bookingRequest.getRoomCount())
                .amount(BigDecimal.TEN)
                .build();

booking =bookingRepository.save(booking);
return modelMapper.map(booking,BookingDto.class);

    }

    @Override
    public BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList) {

        Booking booking= bookingRepository.findById(bookingId).orElseThrow(() ->
                new ResourceNotFoundException("Booking not found with Id:"+bookingId));

        return null;
    }
    public boolean hssBookingExpired(Booking booking){
        return booking.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now());
    }
}

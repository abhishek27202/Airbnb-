package com.codingshuttle.airbnb.airbnb.serviceImpl;

import com.codingshuttle.airbnb.airbnb.dto.HotelDto;
import com.codingshuttle.airbnb.airbnb.entity.Hotel;
import com.codingshuttle.airbnb.airbnb.entity.Room;
import com.codingshuttle.airbnb.airbnb.exception.ResourceNotFoundException;
import com.codingshuttle.airbnb.airbnb.repository.HotelRepository;
import com.codingshuttle.airbnb.airbnb.repository.RoomRepository;
import com.codingshuttle.airbnb.airbnb.service.HotelService;

import com.codingshuttle.airbnb.airbnb.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private  final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    private final RoomRepository roomRepository;
    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a hotel with name: {}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Hotel created with id: {}", hotel.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        Hotel hotel=hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not Found with id"+id));
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto updateHotel( Long id,HotelDto hotelDto) {
        log.info("Creating a hotel with name: {}", hotelDto.getName());
        Hotel hotel=hotelRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID:"+id));
        modelMapper.map(hotelDto,hotel);
     //   hotel.setId(id);
        hotel=hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Hotel hotel=hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID:"+id));
       // hotelRepository.deleteById(id);
        for(Room room:hotel.getRooms()){
            inventoryService.deleteFutureInventory(room);
            roomRepository.findById(room.getId());
        }
        hotelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void activeHotel(Long id) {
        Hotel hotel=hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID:"+id));
           hotel.setActive(true);
           for(Room room:hotel.getRooms()){
            inventoryService.initializeRoomForAYear(room);
        }

    }
}

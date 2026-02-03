package com.codingshuttle.airbnb.airbnb.serviceImpl;

import com.codingshuttle.airbnb.airbnb.dto.RoomDto;
import com.codingshuttle.airbnb.airbnb.entity.Hotel;
import com.codingshuttle.airbnb.airbnb.entity.Room;
import com.codingshuttle.airbnb.airbnb.exception.ResourceNotFoundException;
import com.codingshuttle.airbnb.airbnb.repository.HotelRepository;
import com.codingshuttle.airbnb.airbnb.repository.RoomRepository;
import com.codingshuttle.airbnb.airbnb.service.InventoryService;
import com.codingshuttle.airbnb.airbnb.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;

    @Override
    public RoomDto saveRoom(Long hotelId, RoomDto roomDto) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found for given id " + hotelId));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        Room savedRoom = roomRepository.save(room);
        if (hotel.getActive()) {
            inventoryService.initializeRoomForAYear(room);
        }
        RoomDto dto = modelMapper.map(savedRoom, RoomDto.class);
        dto.setHotelId(savedRoom.getHotel().getId());
        return dto;
    }

    @Override
    public List<RoomDto> findAllRoomByHotel(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("No room found for hotelID" + hotelId));
        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not find with given id" + roomId));
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public void  deleteById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + roomId));
        roomRepository.deleteById(roomId);
        inventoryService.deleteFutureInventory(room);

    }


}

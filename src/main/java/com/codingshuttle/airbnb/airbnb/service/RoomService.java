package com.codingshuttle.airbnb.airbnb.service;

import com.codingshuttle.airbnb.airbnb.dto.RoomDto;

import java.util.List;

public interface RoomService {
  public   RoomDto saveRoom(Long hotelId,RoomDto roomDto);

    List<RoomDto> findAllRoomByHotel(Long hotelId);

    public RoomDto  getRoomById(Long roomId);

public void deleteById(Long roomId);
}

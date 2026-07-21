package com.codingshuttle.airbnb.airbnb.dto;

import com.codingshuttle.airbnb.airbnb.entity.Hotel;
import com.codingshuttle.airbnb.airbnb.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HotelInfoDto {
    private HotelDto hotel;
    private List<RoomDto> rooms;

}

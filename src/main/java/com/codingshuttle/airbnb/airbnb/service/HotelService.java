package com.codingshuttle.airbnb.airbnb.service;

import com.codingshuttle.airbnb.airbnb.dto.HotelDto;
import com.codingshuttle.airbnb.airbnb.entity.Hotel;

public interface HotelService {
    public HotelDto createNewHotel(HotelDto hotelDto);

    public HotelDto getHotelById(Long id);

    HotelDto updateHotel( Long id,HotelDto hotelDto);

    void deleteById(Long id);

    void activeHotel(Long id);
}

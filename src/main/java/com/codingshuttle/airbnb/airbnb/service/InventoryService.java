package com.codingshuttle.airbnb.airbnb.service;

import com.codingshuttle.airbnb.airbnb.dto.HotelDto;
import com.codingshuttle.airbnb.airbnb.dto.HotelSearchRequest;
import com.codingshuttle.airbnb.airbnb.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {
    void initializeRoomForAYear(Room room);

    void deleteFutureInventory(Room room);

    Page<HotelDto> searchHotel(HotelSearchRequest hotelSearchRequest);
}

package com.codingshuttle.airbnb.airbnb.service;

import com.codingshuttle.airbnb.airbnb.entity.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);

    void deleteFutureInventory(Room room);
}

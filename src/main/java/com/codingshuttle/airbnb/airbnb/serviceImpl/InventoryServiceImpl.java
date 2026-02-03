package com.codingshuttle.airbnb.airbnb.serviceImpl;

import com.codingshuttle.airbnb.airbnb.entity.Inventory;
import com.codingshuttle.airbnb.airbnb.entity.Room;
import com.codingshuttle.airbnb.airbnb.repository.InventoryRepository;
import com.codingshuttle.airbnb.airbnb.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today= LocalDate.now();
        LocalDate endDate=today.plusYears(1);
        for(;!today.isAfter(endDate);today=today.plusDays(1)){
            Inventory inventory=Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepository.save(inventory);

        }
    }

    public void deleteFutureInventory(Room room){
        LocalDate today =LocalDate.now();
        inventoryRepository.deleteByDateAfterAndRoom(today,room);
    }
}

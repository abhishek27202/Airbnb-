package com.codingshuttle.airbnb.airbnb.repository;

import com.codingshuttle.airbnb.airbnb.entity.Hotel;
import com.codingshuttle.airbnb.airbnb.entity.Inventory;
import com.codingshuttle.airbnb.airbnb.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    void deleteByDateAfterAndRoom(LocalDate  date, Room room);

@Query("""
        Select Distinct i.hotel
       FROM  Inventory i where i.city= :city
        AND i.date BETWEEN :startDate AND :endDate
        AND i.closed= false
        AND (i.totalCount-i.bookCount) >= :roomsCount
        GROUP BY i.hotel,i.room
        HAVING COUNT(i.date)=:dateCount
       """)
    Page<Hotel> findHotelWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomCount") Integer roomCount,
            @Param("dateCount") Long dateCount,
            Pageable pageable

    );
}
package com.codingshuttle.airbnb.airbnb.repository;

import com.codingshuttle.airbnb.airbnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
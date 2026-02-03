package com.codingshuttle.airbnb.airbnb.repository;

import com.codingshuttle.airbnb.airbnb.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
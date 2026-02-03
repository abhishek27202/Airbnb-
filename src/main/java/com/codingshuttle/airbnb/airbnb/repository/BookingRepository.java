package com.codingshuttle.airbnb.airbnb.repository;

import com.codingshuttle.airbnb.airbnb.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
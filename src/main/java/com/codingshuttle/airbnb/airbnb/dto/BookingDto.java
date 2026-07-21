package com.codingshuttle.airbnb.airbnb.dto;

import com.codingshuttle.airbnb.airbnb.entity.Guest;
import com.codingshuttle.airbnb.airbnb.entity.Hotel;
import com.codingshuttle.airbnb.airbnb.entity.Room;
import com.codingshuttle.airbnb.airbnb.entity.User;
import com.codingshuttle.airbnb.airbnb.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private Integer roomCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
}



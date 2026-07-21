package com.codingshuttle.airbnb.airbnb.controller;

import com.codingshuttle.airbnb.airbnb.dto.BookingDto;
import com.codingshuttle.airbnb.airbnb.dto.BookingRequest;
import com.codingshuttle.airbnb.airbnb.dto.GuestDto;
import com.codingshuttle.airbnb.airbnb.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/booking")
public class HotelBookingController {

private final BookingService bookingService;

@PostMapping("/init")
    public ResponseEntity<BookingDto> initialsBooking(@RequestBody BookingRequest bookingRequest){
return ResponseEntity.ok(bookingService.initialsBooking(bookingRequest));
    }

    public ResponseEntity<BookingDto> addGuests(@PathVariable Long bookingId,
                                      @RequestBody List<GuestDto> guestDtoList){
    return ResponseEntity.ok(bookingService.addGuests(bookingId,guestDtoList));
    }
}


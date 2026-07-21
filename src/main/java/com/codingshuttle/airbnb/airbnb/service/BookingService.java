package com.codingshuttle.airbnb.airbnb.service;

import com.codingshuttle.airbnb.airbnb.dto.BookingDto;
import com.codingshuttle.airbnb.airbnb.dto.BookingRequest;
import com.codingshuttle.airbnb.airbnb.dto.GuestDto;

import java.util.List;

public interface BookingService {

    BookingDto initialsBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}

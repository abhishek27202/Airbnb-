package com.codingshuttle.airbnb.airbnb.controller;

import com.codingshuttle.airbnb.airbnb.dto.HotelDto;
import com.codingshuttle.airbnb.airbnb.dto.HotelInfoDto;
import com.codingshuttle.airbnb.airbnb.dto.HotelSearchRequest;
import com.codingshuttle.airbnb.airbnb.service.HotelService;
import com.codingshuttle.airbnb.airbnb.service.InventoryService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowserController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

//    @PostMapping("/search")
//    public ResponseEntity<Page<HotelDto>> searchHotel(
//            @Valid @RequestBody HotelSearchRequest hotelSearchRequest){
//        Page<HotelDto> page= inventoryService.searchHotel(hotelSearchRequest);
//        return ResponseEntity.ok(page);
//    }
    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchHotels(@Valid @RequestBody HotelSearchRequest hotelSearchRequest) {
        System.out.println("START DATE: " + hotelSearchRequest.getStartDate());
        System.out.println("END DATE: " + hotelSearchRequest.getEndDate());
        System.out.println("CITY: " + hotelSearchRequest.getCity());
        Page<HotelDto> page = inventoryService.searchHotel(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/Info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }
}

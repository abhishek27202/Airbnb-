package com.codingshuttle.airbnb.airbnb.controller;

import com.codingshuttle.airbnb.airbnb.dto.HotelDto;
import com.codingshuttle.airbnb.airbnb.dto.HotelSearchRequest;
import com.codingshuttle.airbnb.airbnb.service.InventoryService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowserController {

    private final InventoryService inventoryService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchHotel(@RequestBody HotelSearchRequest hotelSearchRequest){
        Page<HotelDto> page= inventoryService.searchHotel(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }
}

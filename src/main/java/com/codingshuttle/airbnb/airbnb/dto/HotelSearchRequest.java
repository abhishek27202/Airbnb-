package com.codingshuttle.airbnb.airbnb.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelSearchRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private String city;
    private Integer roomCount;

    private Integer page=0;
    private Integer size =10;

}

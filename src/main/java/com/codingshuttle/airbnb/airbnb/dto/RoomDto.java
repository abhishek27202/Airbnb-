package com.codingshuttle.airbnb.airbnb.dto;

import com.codingshuttle.airbnb.airbnb.entity.Hotel;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomDto {

    private Long id;
    private Long hotelId;
    private String type;
    private BigDecimal basePrice;
    private String[] photo;
    private String[] amenities;
    private Integer totalCount;
    private Integer capacity;

}

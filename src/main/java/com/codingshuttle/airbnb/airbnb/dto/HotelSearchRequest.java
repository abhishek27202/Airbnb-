package com.codingshuttle.airbnb.airbnb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class HotelSearchRequest {


    @NotNull(message = "please enter valid Date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "please enter valid Date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "please enter valid City")
    private String city;

    private Integer roomCount;
    private Integer page=0;
    private Integer size =10;

}

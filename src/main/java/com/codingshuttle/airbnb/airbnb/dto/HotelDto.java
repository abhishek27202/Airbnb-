package com.codingshuttle.airbnb.airbnb.dto;

import com.codingshuttle.airbnb.airbnb.entity.HotelContactInfo;
import com.codingshuttle.airbnb.airbnb.entity.Room;
import lombok.Data;
import java.util.List;

@Data
public class HotelDto {


    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private  Boolean active;
    private HotelContactInfo contactInfo;


}

package com.codingshuttle.airbnb.airbnb.dto;

import com.codingshuttle.airbnb.airbnb.entity.User;
import com.codingshuttle.airbnb.airbnb.entity.enums.Gender;
import jakarta.persistence.*;

public class GuestDto {

    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}

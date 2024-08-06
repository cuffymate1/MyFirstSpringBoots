package com.example.conndb.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class UserDto {


    private String userId;
    private String userName;
}

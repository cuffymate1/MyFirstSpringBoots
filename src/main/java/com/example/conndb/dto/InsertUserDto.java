package com.example.conndb.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class InsertUserDto {

    private String userId;
    private String userName;
}

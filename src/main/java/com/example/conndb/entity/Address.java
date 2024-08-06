package com.example.conndb.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "user_id")
    private String userId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "house_no")
    private String houseNo;

    @Column(name = "sub_district")
    private String subDistrict;

    @Column(name = "district")
    private String district;


    @Column(name = "city")
    private String city;

    @Column(name = "postcode")
    private String postCode;
}

package com.example.conndb;


import com.example.conndb.dto.AddressDto;
import com.example.conndb.dto.InsertAddressDto;
import com.example.conndb.entity.Address;
import com.example.conndb.entity.User;
import com.example.conndb.repository.AddressRepository;
import com.example.conndb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("add/{userId}")
    public AddressDto addGetById(
            @PathVariable("userId") String userId
    ) {
        Address address = addressRepository.findById(userId).orElseThrow();
        return new AddressDto()
                .setUserId(address.getUserId())
                .setUserName(address.getUser().getUserName())
                .setHouseNo(address.getHouseNo())
                .setSubDistrict(address.getSubDistrict())
                .setDistrict(address.getDistrict())
                .setCity(address.getCity())
                .setPostCode(address.getPostCode());
    }


    @PostMapping("add/insert")
    public AddressDto insertAdd(
            @RequestBody InsertAddressDto insertAddressDto
    ) {
        User user = userRepository.findById(insertAddressDto.getUserId()).orElseThrow();

        Address address = new Address()
                .setUser(user)
                .setHouseNo(insertAddressDto.getHouseNo())
                .setSubDistrict(insertAddressDto.getSubDistrict())
                .setDistrict(insertAddressDto.getDistrict())
                .setCity(insertAddressDto.getCity())
                .setPostCode(insertAddressDto.getPostCode());

        address = addressRepository.save(address);

        return new AddressDto()
                .setUserId(address.getUserId())
                .setUserName(address.getUser().getUserName())
                .setHouseNo(address.getHouseNo())
                .setSubDistrict(address.getSubDistrict())
                .setDistrict(address.getDistrict())
                .setCity(address.getCity())
                .setPostCode(address.getPostCode());
    }



    @GetMapping("add/delete")
    public void deleteAdd(
            @RequestParam("id")String id
    ){
        addressRepository.findById(id).orElseThrow();
        addressRepository.deleteById(id);
    }
}

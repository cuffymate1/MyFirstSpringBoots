package com.example.conndb;


import com.example.conndb.dto.InsertUserDto;
import com.example.conndb.dto.UserDto;
import com.example.conndb.entity.User;
import com.example.conndb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;



    @GetMapping("user/{userId}")
    public UserDto getUserById(
            @PathVariable("userId") String userId
    ){
       User user = userRepository.findById(userId).orElseThrow();
       return new UserDto().setUserId(user.getUserId())
               .setUserName(user.getUserName());
    }

    @PostMapping("user/insert")
    public UserDto insertUser(
            @RequestBody InsertUserDto insertUserDto
    ){
        User user = new User()
                .setUserId(insertUserDto.getUserId())
                .setUserName(insertUserDto.getUserName());
        user = userRepository.save(user);
        return new UserDto().setUserId(user.getUserId())
                .setUserName(user.getUserName());
    }

    @GetMapping("user/update")
    public User updateUser(
            @RequestBody User user
    ){
        userRepository.findById(user.getUserId()).orElseThrow();
       return userRepository.save(user);
    }

    @GetMapping("user/delete")
    public void deleteUser(
            @RequestParam("userId") String userId
    ){
        userRepository.findById(userId).orElseThrow();
        userRepository.deleteById(userId);
    }
}

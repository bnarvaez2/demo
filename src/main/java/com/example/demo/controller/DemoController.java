package com.example.demo.controller;

import java.util.List;

import com.example.demo.discount.DiscountRequest;
import com.example.demo.discount.DiscountResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("User/Api/v1")
public class DemoController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        ResponseEntity<List<User>> response = ResponseEntity.noContent().build();
        List<User> list = userService.findAll();
        if(list != null){
            response= ResponseEntity.ok(list);
        }
        return response;
    }

    @GetMapping(value = "/login")
    public ResponseEntity<User> getMethodName(@RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password) {

        ResponseEntity<User> response = ResponseEntity.notFound().build();
        User user = userService.getUserByCredentials(email, password);
        if(user != null){
            response = ResponseEntity.ok(user);
        }
        return response;
    }

    @PostMapping(value="new")
    public ResponseEntity<Object> postMethodName(@RequestBody DiscountRequest entity) {
        DiscountResponse discount = new DiscountResponse();
        discount.setStatus(false);
        if(!entity.getDiscountCode().isBlank() && !entity.getDiscountCode().equals("InvalidCode")){
            discount.setId("20");
            discount.setHouseId(entity.getHouseId());
            discount.setUserId(entity.getUserId());
            discount.setDiscountCode(entity.getDiscountCode());
            discount.setStatus(true);

            return ResponseEntity.ok(discount);
        }
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(discount);
    }
    

}

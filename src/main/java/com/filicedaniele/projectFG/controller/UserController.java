package com.filicedaniele.projectFG.controller;


import com.filicedaniele.projectFG.Data.UserRequest;
import com.filicedaniele.projectFG.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserRequest request){
        HashMap<String,Object> prop = new HashMap<>();
        prop.put("user created",userservice.create(request));
        return ResponseEntity.ok(prop);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody UserRequest request){
        HashMap<String,Object> prop = new HashMap<>();
        prop.put("user updated",userservice.update(request));
        return ResponseEntity.ok(prop);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("email") String email){
        HashMap<String,Object> prop = new HashMap<>();
        prop.put("user deleted",userservice.delete(email));
        return ResponseEntity.ok(prop);
    }

    @GetMapping
    public  ResponseEntity<?> get(@RequestParam(value = "nome", required = false)  String nome,
                                  @RequestParam(value = "cognome", required = false) String cognome){
        HashMap<String,Object> prop = new HashMap<>();
        prop.put("users: ",userservice.get(nome,cognome));
        return ResponseEntity.ok(prop);
    }

    @PostMapping("/importCsv")
    public  ResponseEntity<?> insertUsersByCsv(@RequestParam("file")MultipartFile file) throws IOException {
        HashMap<String,Object> prop = new HashMap<>();
        prop.put("users added: ",userservice.addUsersCsv(file));
        return ResponseEntity.ok(prop);
    }
}

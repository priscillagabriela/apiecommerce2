/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppsi.apiecommerce2.controller;

import com.ppsi.apiecommerce2.helper.ResponseBase;
import com.ppsi.apiecommerce2.model.User;
import com.ppsi.apiecommerce2.model.UserRepository;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
public class UserController {
    ResponseBase rb = new ResponseBase();
    @Autowired UserRepository ur;
    
    @GetMapping("/readusers")
    public HashMap<String, Object> readUsers() {
        return rb.Response(HttpStatus.OK, "All User", ur.findAll());
    }
    
    @GetMapping("/readusersbyid/{id}")
    public HashMap<String, Object> readUsersById(@PathVariable(value = "id") String iduser) {
        return rb.Response(HttpStatus.OK, "User by Id", ur.findById(iduser));
    }
    
    @GetMapping("/loginusername/{username}/pass/{pass}")
    public HashMap<String, Object> getUserByUsernamePass(@PathVariable(value = "username") String username, 
            @PathVariable(value = "pass") String pass) {
        return rb.Response(HttpStatus.OK, "All User", ur.findByUsernamePass(username, pass));
    }
    
    @GetMapping("/login_user")
    public  HashMap<String, Object> login(@RequestBody User usr) {
        User user_db = ur.findByUsername(usr.getUsername());
        if (user_db == null) {
            return rb.Response(HttpStatus.UNAUTHORIZED, "User Not Found", usr);
        } else if (usr.getNotelp().equals(user_db.getNotelp())) {
            return rb.Response(HttpStatus.OK, "Access Granted", "");
        }
        return rb.Response(HttpStatus.UNAUTHORIZED, "Phone Number Not Recognize", usr);
    }
    
    @PostMapping("/createusers")
    public HashMap<String, Object> createUsers(@RequestBody User usr) {
        return rb.Response(HttpStatus.OK, "Create User", ur.save(usr));
    }
    
    @PutMapping("/updatepasswithusername/{username}")
    public HashMap<String, Object> updatePassWithUsername(@PathVariable(value = "username") String username, @RequestBody User usd) {
        User usr = ur.findByUsername(username);
        usr.setPass(usd.getPass());
        
        User updatedUser = ur.save(usr);
        
        return rb.Response(HttpStatus.OK, "Update Password", updatedUser);
    }
    
    @PutMapping("/updatepasswithid/{id}")
    public HashMap<String, Object> updatePassWithId(@PathVariable(value = "id") String iduser, @RequestBody User usd) {
        User usr = ur.findById(iduser).orElseThrow(() -> new DataAccessException("User "
                + "ID : " + iduser + "Not Found") {                   
                });
        usr.setPass(usd.getPass());
        
        User updatedUser = ur.save(usr);
        
        return rb.Response(HttpStatus.OK, "Update Password", updatedUser);
    }
    
    @PutMapping("/updateusers/{id}")
    public HashMap<String, Object> updateUsers(@PathVariable(value = "id") String iduser, @RequestBody User usd) {
        User usr = ur.findById(iduser).orElseThrow(() -> new DataAccessException("User "
                + "ID : " + iduser + "Not Found") {                   
                });
        usr.setIduser(usd.getIduser());
        usr.setNama(usd.getNama());
        usr.setAlamat(usd.getAlamat());
        usr.setNotelp(usd.getNotelp());
        usr.setEmail(usd.getEmail());
        usr.setStat(usd.getStat());
        usr.setUsername(usd.getUsername());
        usr.setPass(usd.getPass());
        
        User updatedUser = ur.save(usr);
        
        return rb.Response(HttpStatus.OK, "Update User", updatedUser);
    }
    
    @DeleteMapping("/deleteusers/{id}")
    public HashMap<String, Object> deleteUsers(@PathVariable(value = "id") String iduser) {
        User usr = ur.findById(iduser).orElseThrow(() -> new DataAccessException("User "
                + "ID : " + iduser + "Not Found") {                   
                });
        ur.delete(usr);
        
        return rb.Response(HttpStatus.OK, "Delete User", ResponseEntity.ok().build());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppsi.apiecommerce2.controller;

import com.ppsi.apiecommerce2.helper.ResponseBase;
import com.ppsi.apiecommerce2.model.UserDetail;
import com.ppsi.apiecommerce2.model.UserDetailRepository;
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
public class UserDetailController {
    ResponseBase rb = new ResponseBase();
    @Autowired UserDetailRepository udr;
    
    @GetMapping("/readdetailuser")
    public HashMap<String, Object> readDetailUser() {
        return rb.Response(HttpStatus.OK, "All Detail User", udr.findAll());
    }
    
    @GetMapping("/readdetailuserwithid/{id}")
    public HashMap<String, Object> readDetailUserWithId(@PathVariable(value = "id") String iddetuser) {
        return rb.Response(HttpStatus.OK, "Detail User by Id", udr.findById(iddetuser));
    }
    
    @PostMapping("/createdetailuser")
    public HashMap<String, Object> createDetailUser(@RequestBody UserDetail udsr) {
        return rb.Response(HttpStatus.OK, "Create Detail User", udr.save(udsr));
    }
    
    @PutMapping("/updatedetailuser/{id}")
    public HashMap<String, Object> updateDetailUser(@PathVariable(value = "id") String iddetuser, @RequestBody UserDetail udsd) {
        UserDetail udsr = udr.findById(iddetuser).orElseThrow(() -> new DataAccessException("Detail User "
                + "ID : " + iddetuser + "Not Found") {                   
                }); 
        udsr.setIduserdetail(udsd.getIduserdetail());
        udsr.setIduser(udsd.getIduser());
        udsr.setJeniskulit(udsd.getJeniskulit());
        udsr.setJenisbibir(udsd.getJenisbibir());
        
        UserDetail updatedUserDetail = udr.save(udsr);
        
        return rb.Response(HttpStatus.OK, "Update Detail User", updatedUserDetail);
    }
    
    @DeleteMapping("/deletedetailuser/{id}")
    public HashMap<String, Object> deleteDetailUser(@PathVariable(value = "id") String iddetuser) {
        UserDetail udsr = udr.findById(iddetuser).orElseThrow(() -> new DataAccessException("Detail User "
                + "ID : " + iddetuser + "Not Found") {                   
                });
        udr.delete(udsr);
        
        return rb.Response(HttpStatus.OK, "Delete Detail User", ResponseEntity.ok().build());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppsi.apiecommerce2.controller;

import com.ppsi.apiecommerce2.helper.ResponseBase;
import com.ppsi.apiecommerce2.model.Review;
import com.ppsi.apiecommerce2.model.ReviewRepository;
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
public class ReviewController {
    ResponseBase rb = new ResponseBase();
    @Autowired ReviewRepository rr;
    
    @GetMapping("/readreview")
    public HashMap<String, Object> readReview() {
        return rb.Response(HttpStatus.OK, "All Review", rr.findAll());
    }
    
    @GetMapping("/readreviewwithid/{id}")
    public HashMap<String, Object> readReviewWithId(@PathVariable(value = "id") String idreview) {
        return rb.Response(HttpStatus.OK, "Review by Id", rr.findById(idreview));
    }
    
    @GetMapping("/readreviewwithiduser/{id}")
    public HashMap<String, Object> readReviewWithIdUser(@PathVariable(value = "id") String iduser) {
        return rb.Response(HttpStatus.OK, "Review by Id User", rr.findByIdUser(iduser));
    }
    
    @GetMapping("/readreviewwithidprod/{id}")
    public HashMap<String, Object> readReviewWithIdProd(@PathVariable(value = "id") String idproduk) {
        return rb.Response(HttpStatus.OK, "Review by Id Product", rr.findByIdProd(idproduk));
    }
    
    @PostMapping("/createreview")
    public HashMap<String, Object> createReview(@RequestBody Review rsr) {
        return rb.Response(HttpStatus.OK, "Create Review", rr.save(rsr));
    }
    
    @PutMapping("/updatereview/{id}")
    public HashMap<String, Object> updateReview(@PathVariable(value = "id") String idreview, @RequestBody Review rsd) {
        Review rsr = rr.findById(idreview).orElseThrow(() -> new DataAccessException("Review"
                + "ID : " + idreview + "Not Found") {                   
                }); 
        rsr.setIdreview(rsd.getIdreview());
        rsr.setIduser(rsd.getIduser());
        rsr.setIdproduk(rsd.getIdproduk());
        rsr.setKomentar(rsd.getKomentar());
        rsr.setDurability(rsd.getDurability());
        rsr.setPackaging(rsd.getPackaging());
        rsr.setValueofmoney(rsd.getValueofmoney());
        rsr.setTglreview(rsd.getTglreview());
        
        Review updatedReview = rr.save(rsr);
        
        return rb.Response(HttpStatus.OK, "Update Review", updatedReview);
    }
    
    @DeleteMapping("/deletereview/{id}")
    public HashMap<String, Object> deleteReview(@PathVariable(value = "id") String idreview) {
        Review rsr = rr.findById(idreview).orElseThrow(() -> new DataAccessException("Review "
                + "ID : " + idreview + "Not Found") {                   
                });
        rr.delete(rsr);
        
        return rb.Response(HttpStatus.OK, "Delete Review", ResponseEntity.ok().build());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppsi.apiecommerce2.controller;

import com.ppsi.apiecommerce2.helper.ResponseBase;
import com.ppsi.apiecommerce2.model.ProdukDetail;
import com.ppsi.apiecommerce2.model.ProdukDetailRepository;
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
public class ProdukDetailController {
    ResponseBase rb = new ResponseBase();
    @Autowired ProdukDetailRepository pdr;
    
    @GetMapping("/readdetailmakeup")
    public HashMap<String, Object> readAllDetailMakeup() {
        return rb.Response(HttpStatus.OK, "All Detail Makeup", pdr.findAll());
    }
    
    @GetMapping("/readdetailmakeupwithid/{id}")
    public HashMap<String, Object> readDetailMakeupWithId(@PathVariable(value = "id") String iddetmakeup) {
        return rb.Response(HttpStatus.OK, "Detail Makeup by Id", pdr.findById(iddetmakeup));
    }
    
    @GetMapping("/readdetailmakeupwithidprod/{id}")
    public HashMap<String, Object> readDetailMakeupWithIdProd(@PathVariable(value = "id") String idproduk) {
        return rb.Response(HttpStatus.OK, "Detail Makeup by Id Product", pdr.findByIdProd(idproduk));
    }
    
    @PostMapping("/createdetailmakeup")
    public HashMap<String, Object> createDetailMakeup(@RequestBody ProdukDetail pdsr) {
        return rb.Response(HttpStatus.OK, "Create Detail Makeup", pdr.save(pdsr));
    }
    
    @PutMapping("/updatedetailmakeup/{id}")
    public HashMap<String, Object> updateDetailMakeup(@PathVariable(value = "id") String iddetmakeup, @RequestBody ProdukDetail pdsd) {
        ProdukDetail pdsr = pdr.findById(iddetmakeup).orElseThrow(() -> new DataAccessException("Detail Makeup "
                + "ID : " + iddetmakeup + "Not Found") {                   
                });
        pdsr.setIdproddetail(pdsd.getIdproddetail());
        pdsr.setIdproduk(pdsd.getIdproduk());
        pdsr.setVariasi(pdsd.getVariasi());
        pdsr.setUkuran(pdsd.getUkuran());
        pdsr.setSatuanukuran(pdsd.getSatuanukuran());
        
        ProdukDetail updatedDetailMakeup = pdr.save(pdsr);
        
        return rb.Response(HttpStatus.OK, "Update Detail Makeup", updatedDetailMakeup);
    }
    
    @DeleteMapping("/deletedetailmakeup/{id}")
    public HashMap<String, Object> deleteDetailMakeup(@PathVariable(value = "id") String iddetmakeup) {
        ProdukDetail pdsr = pdr.findById(iddetmakeup).orElseThrow(() -> new DataAccessException("Detail Makeup "
                + "ID : " + iddetmakeup + "Not Found") {                   
                });
        pdr.delete(pdsr);
        
        return rb.Response(HttpStatus.OK, "Delete Detail Makeup", ResponseEntity.ok().build());
    }
}

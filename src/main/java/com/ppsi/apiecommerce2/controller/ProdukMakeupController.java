/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppsi.apiecommerce2.controller;

import com.ppsi.apiecommerce2.helper.ResponseBase;
import com.ppsi.apiecommerce2.model.ProdukMakeup;
import com.ppsi.apiecommerce2.model.ProdukMakeupRepository;
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
public class ProdukMakeupController {
    ResponseBase rb = new ResponseBase();
    @Autowired ProdukMakeupRepository pmr;
    
    @GetMapping("/readmakeup")
    public HashMap<String, Object> readAllMakeup() {
        return rb.Response(HttpStatus.OK, "All Makeup", pmr.findAll());
    }
    @GetMapping("/readmakeupwithid/{id}")
    public HashMap<String, Object> readMakeupWithId(@PathVariable(value = "id") String idmakeup) {
        return rb.Response(HttpStatus.OK, "Makeup by Id", pmr.findById(idmakeup));
    }
    
    @GetMapping("/readmakeupwithcategory/{category}")
    public HashMap<String, Object> readMakeupWithCategory(@PathVariable(value = "category") String kategori) {
        return rb.Response(HttpStatus.OK, "Makeup by Category", pmr.findByCategory(kategori));
    }
    
    @GetMapping("/readmakeupwithinfo/{info}")
    public HashMap<String, Object> readMakeupWithInfo(@PathVariable(value = "info") String info) {
        return rb.Response(HttpStatus.OK, "Makeup by Info", pmr.findByInfo(info));
    }
    
    @PostMapping("/createmakeup")
    public HashMap<String, Object> createMakeup(@RequestBody ProdukMakeup pmsr) {
        return rb.Response(HttpStatus.OK, "Create Makeup", pmr.save(pmsr));
    }
    
    @PutMapping("/updatemakeup/{id}")
    public HashMap<String, Object> updateMakeup(@PathVariable(value = "id") String idmakeup, @RequestBody ProdukMakeup pmsd) {
        ProdukMakeup pmsr = pmr.findById(idmakeup).orElseThrow(() -> new DataAccessException("Makeup "
                + "ID : " + idmakeup + "Not Found") {                   
                });
        pmsr.setIdproduk(pmsd.getIdproduk());
        pmsr.setMerek(pmsd.getMerek());
        pmsr.setNama(pmsd.getNama());
        pmsr.setKategori(pmsd.getKategori());
        pmsr.setDeskripsi(pmsd.getDeskripsi());
        pmsr.setRatingbintang(pmsd.getRatingbintang());
        pmsr.setKeterangan(pmsd.getKeterangan());
        
        ProdukMakeup updatedMakeup = pmr.save(pmsr);
        
        return rb.Response(HttpStatus.OK, "Update Makeup", updatedMakeup);
    }
    
    @DeleteMapping("/deletemakeup/{id}")
    public HashMap<String, Object> deleteMakeup(@PathVariable(value = "id") String idmakeup) {
        ProdukMakeup msr = pmr.findById(idmakeup).orElseThrow(() -> new DataAccessException("Makeup "
                + "ID : " + idmakeup + "Not Found") {                   
                });
        pmr.delete(msr);
        
        return rb.Response(HttpStatus.OK, "Delete Makeup", ResponseEntity.ok().build());
    }
}

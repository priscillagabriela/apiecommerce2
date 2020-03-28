/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppsi.apiecommerce2.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface ProdukDetailRepository extends
    JpaRepository<ProdukDetail, String> {
    @Query(value = "select * from produkdetail where idproduk=?1", nativeQuery = true)
    List <ProdukDetail> findByIdProd(String idproduk);
}

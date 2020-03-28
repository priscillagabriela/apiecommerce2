/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppsi.apiecommerce2.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author user
 */
@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id int idreview;
    int iduser;
    int idproduk;
    String komentar;
    double durability;
    double packaging;
    double valueofmoney;
    String tglreview;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppsi.apiecommerce2.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface UserRepository extends
    JpaRepository<User, String> {
    
    @Query(value = "select * from users where username=?1 and pass=?2", nativeQuery = true)
    public User findByUsernamePass(String username, String pass);
    
    @Query(value = "select * from users where username=?1", nativeQuery = true)
    public User findByUsername(String username);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ppsi.apiecommerce2.helper;

import java.util.HashMap;
import org.springframework.http.HttpStatus;

/**
 *
 * @author user
 */
public class ResponseBase {
    public HashMap<String,Object> Response(HttpStatus status,
            String message, Object data) {
        HashMap<String,Object> response = new HashMap<>();
        response.put("Status", status);
        response.put("Message", message);
        response.put("Data", data);
        return response;
    }
}

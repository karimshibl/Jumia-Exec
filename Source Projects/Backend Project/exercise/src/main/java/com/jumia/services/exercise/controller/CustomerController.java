/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jumia.services.exercise.controller;

import com.jumia.services.exercise.dto.CustomerDto;
import com.jumia.services.exercise.dto.ResponseDto;
import com.jumia.services.exercise.dto.StatusDto;
import com.jumia.services.exercise.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nour_Mahmoud
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    private Environment env;

    @ApiOperation(value = "Search for Customers by Country and state", notes = "get customers and their country phone details with optional filters", produces = "application/Json", response = ResponseDto.class)
    @CrossOrigin
    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto search(@RequestParam(name = "country", required = false) String countryName, @RequestParam(name = "state", required = false) String validState,
            @RequestParam(required = false) Integer startIndex, @RequestParam(required = false) Integer resultSize) {
        ResponseDto response = new ResponseDto();
        try {
            //setting default values for request parameters
            if (countryName != null && countryName.isEmpty()) {
                countryName = null;
            }
            Boolean state = null;
            if (validState != null && !validState.isEmpty()) {
                state = validState.equals("true") ? Boolean.TRUE : Boolean.FALSE;
            }

            response = customerService.searchCustomers(countryName, state, startIndex, resultSize);
            if (response.getData() != null && !((List<CustomerDto>) response.getData()).isEmpty()) {
                response.getStatus().setArabicMessage(this.env.getProperty("message.success"));
            } else {
                response.setStatus(new StatusDto(404, "No Data Found", this.env.getProperty("message.missingData")));
            }
        } catch (Exception ex) {
            response.setStatus(new StatusDto(500, ex.getMessage(), this.env.getProperty("message.generalError")));
        }
        return response;
    }

}

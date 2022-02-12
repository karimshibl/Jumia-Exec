/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jumia.services.exercise.controller;

import com.jumia.services.exercise.dto.ResponseDto;
import com.jumia.services.exercise.dto.StatusDto;
import com.jumia.services.exercise.enums.CountryEnum;
import com.jumia.services.exercise.service.CountryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nour_Mahmoud
 */
@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    CountryService countryService;

    @Autowired
    private Environment env;

    @ApiOperation(value = "Get all Countires list", produces = "application/Json", response = ResponseDto.class)
    @CrossOrigin
    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto getAllCountries() {
        ResponseDto response = new ResponseDto();
        try {
            CountryEnum[] allCountries = countryService.getAllCountries();
            if (allCountries != null && allCountries.length != 0) {
                response.setData(allCountries);
                response.getStatus().setArabicMessage(this.env.getProperty("message.success"));
            } else {
                response.setStatus(new StatusDto(404, "No Data Found", this.env.getProperty("message.missingData")));
            }
        } catch (Exception ex) {
            response.setStatus(new StatusDto(500, "General Error", this.env.getProperty("message.generalError")));
        }
        return response;
    }

}

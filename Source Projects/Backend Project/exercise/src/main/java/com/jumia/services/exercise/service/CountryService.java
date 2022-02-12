/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jumia.services.exercise.service;

import com.jumia.services.exercise.enums.CountryEnum;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nour_Mahmoud
 */
@Service
public class CountryService {

    final static Logger LOGGER = Logger.getLogger(CountryService.class);

    public CountryEnum[] getAllCountries() {
        return CountryEnum.class.getEnumConstants();
    }

}

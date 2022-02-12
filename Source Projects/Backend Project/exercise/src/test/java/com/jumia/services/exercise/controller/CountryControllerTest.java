/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jumia.services.exercise.controller;

import com.jumia.services.exercise.ExerciseApplicationTests;
import com.jumia.services.exercise.dto.ResponseDto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author Nour_Mahmoud
 */
public class CountryControllerTest extends ExerciseApplicationTests {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getCountriesList() throws Exception {
        String uri = "/api/country/all";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        ResponseDto response = super.mapFromJson(content, ResponseDto.class);
        assertTrue(response.getData() != null);
    }
}

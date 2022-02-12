/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jumia.services.exercise.service;

import com.jumia.services.exercise.dao.CustomerRepo;
import com.jumia.services.exercise.dto.CustomerDto;
import com.jumia.services.exercise.dto.ResponseDto;
import com.jumia.services.exercise.enums.CountryEnum;
import com.jumia.services.exercise.model.Customer;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nour_Mahmoud
 */
@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    final static Logger LOGGER = Logger.getLogger(CustomerService.class);

    public ResponseDto searchCustomers(String countryName, Boolean validState, Integer index, Integer size) {
        LOGGER.info("Inside searchCustomers method");
        ResponseDto response = new ResponseDto();
        List<CustomerDto> filteredCustomers = new ArrayList<>();
        Integer totalCount;

        LOGGER.info("checking if parameter countryName has value , countryName = " + countryName);
        CountryEnum country = null;
        List<Customer> customersList;
        if (countryName != null && !countryName.isEmpty()) {
            LOGGER.info("selecting the available Country Data");
            country = CountryEnum.valueByName(countryName.toLowerCase());

            if (country == null) {
                LOGGER.error("No valid Country data found");
                return null;
            }
            LOGGER.info("Country data found successfully , " + country.toString());
            //filter by country if requested through database
            customersList = getCustomersByCountry(country);
            totalCount = customerRepo.findByCountryCodeCount(country.code);

        } //if filtered country doesn't exist
        else {
            LOGGER.info("selecting all customers from database");
            customersList = customerRepo.findAll();
            totalCount = customerRepo.CountAll();
        }

        //no customers data found in database
        if (customersList == null || customersList.isEmpty()) {
            LOGGER.error("No customer data found in database");
            return null;
        }

        filteredCustomers = ConvertToDto(customersList);

        //filter by state if requested
        if (validState != null) {
            filteredCustomers = filterByState(filteredCustomers, validState);
            totalCount = filteredCustomers.size();
        }

        //returing final result
        LOGGER.info("Returning filtered cutomers dto rsult list");
        response.setTotalDataCount(totalCount);
        response.setData(filteredCustomers);
        return response;
    }

    private List<Customer> getCustomersByCountry(CountryEnum country) {
        LOGGER.info("selecting all customers with country code = " + country.code);
        return customerRepo.findByCountryCode(country.code);
    }

    private List<CustomerDto> ConvertToDto(List<Customer> customersList) {
        LOGGER.info("COnverting customers found to DTO objects");
        List<CustomerDto> resultList = new ArrayList<>();
        customersList.stream().map((customer) -> {
            LOGGER.info("current customer = " + customer.toString());
            CustomerDto dto = new CustomerDto();
            dto.setName(customer.getName());
            dto.setPhone(customer.getPhone());
            CountryEnum country = getCustomerCountry(customer.getPhone());
            if (country != null) {
                LOGGER.info("country details for customer id = " + customer.getId() + " is " + country.toString());
                dto.setCountry(country.name);
                dto.setCountryCode(country.code);
                dto.setValidState(validateCustomerPhone(country, customer.getPhone()));
                LOGGER.info("phone valid state for customer id = " + customer.getId() + " is " + dto.getValidState());
            }
            return dto;
        }).forEachOrdered((dto) -> {
            LOGGER.info("cunverted DTO = " + dto.toString());
            resultList.add(dto);
        });
        return resultList;
    }

    private Boolean validateCustomerPhone(CountryEnum country, String phone) {
        LOGGER.info("validating phone " + phone + " with country regex = " + country.regex);
        return phone.matches(country.regex);
    }

    private CountryEnum getCustomerCountry(String phone) {
        LOGGER.info("getting customer country details using phone = " + phone);
        if (phone == null || phone.isEmpty()) {
            return null;
        }
        return CountryEnum.valueByCode(phone.substring(1, 4));
    }

    private List<CustomerDto> filterByState(List<CustomerDto> customersList, Boolean state) {
        LOGGER.info("filtering selected customers with state = " + state.toString());
        List<CustomerDto> filteredList = new ArrayList<>();
        customersList.stream().filter((dto) -> (dto.getValidState().equals(state))).forEachOrdered((dto) -> {
            filteredList.add(dto);
        });
        return filteredList;
    }

}

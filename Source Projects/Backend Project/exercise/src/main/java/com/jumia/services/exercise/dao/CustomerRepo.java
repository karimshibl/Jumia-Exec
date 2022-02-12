/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jumia.services.exercise.dao;

import com.jumia.services.exercise.model.Customer;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nour_Mahmoud
 */
@Repository
public interface CustomerRepo extends JpaRepository<Customer, BigInteger> {

    @Query("select c from Customer c where c.phone like '('||:code||') %'")
    List<Customer> findByCountryCode(@Param("code") String code);

    @Query(nativeQuery = true, value = "select count(*) from Customer c where c.phone like '('||:code||') %'")
    Integer findByCountryCodeCount(@Param("code") String code);

    @Query(nativeQuery = true, value = "select count(*) from Customer c")
    Integer CountAll();

}

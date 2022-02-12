package com.jumia.services.exercise.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    final static org.jboss.logging.Logger LOGGER = Logger.getLogger(LoggingAspect.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Before("execution( * com.jumia.services.exercise.*.*.*(..))")
    public void before(JoinPoint joinPoint) throws JsonProcessingException {

        LOGGER.info("Enter : " + joinPoint.getSignature());
        try {
            LOGGER.info("Parameters data : " + mapper.writeValueAsString(joinPoint.getArgs()));

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @After("execution( * com.jumia.services.exercise.*.*.*(..))")
    public void after(JoinPoint joinPoint) throws JsonProcessingException {
        try {
            LOGGER.info("Exit from: " + joinPoint.getSignature());

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    @AfterReturning(pointcut = "execution( * com.jumia.services.exercise.*.*.*(..))", returning = "returnedData")
    public void afterReturning(Object returnedData) throws JsonProcessingException {

        try {
            LOGGER.info("Returned data : " + mapper.writeValueAsString(returnedData));

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }

}

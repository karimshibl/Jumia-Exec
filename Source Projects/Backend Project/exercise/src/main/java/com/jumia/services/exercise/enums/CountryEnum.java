/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jumia.services.exercise.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nour_Mahmoud
 */
public enum CountryEnum {

    CAMEROON("cameroon", "237", "\\(237\\)\\ ?[2368]\\d{7,8}$"),
    ETHIOPIA("ethiopia", "251", "\\(251\\)\\ ?[1-59]\\d{8}$"),
    MOROCCO("morocco", "212", "\\(212\\)\\ ?[5-9]\\d{8}$"),
    MOZAMBIQUE("mozambique", "258", "\\(258\\)\\ ?[28]\\d{7,8}$"),
    UGANDA("uganda", "256", "\\(256\\)\\ ?\\d{9}$");

    private static final Map<String, CountryEnum> BY_NAME = new HashMap<>();
    private static final Map<String, CountryEnum> BY_CODE = new HashMap<>();
    private static final Map<String, CountryEnum> BY_REGEX = new HashMap<>();

    static {
        for (CountryEnum c : values()) {
            BY_NAME.put(c.name, c);
            BY_CODE.put(c.code, c);
            BY_REGEX.put(c.regex, c);
        }
    }

    public final String name;
    public final String code;
    public final String regex;

    private CountryEnum(String name, String code, String regex) {
        this.name = name;
        this.code = code;
        this.regex = regex;
    }

    public static CountryEnum valueByCode(String code) {
        return BY_CODE.get(code);
    }

    public static CountryEnum valueByRegex(String regex) {
        return BY_REGEX.get(regex);
    }

    public static CountryEnum valueByName(String name) {
        return BY_NAME.get(name);
    }

    @Override
    public String toString() {
        return "CountryEnum{" + "name=" + name + ", code=" + code + ", regex=" + regex + '}';
    }

}

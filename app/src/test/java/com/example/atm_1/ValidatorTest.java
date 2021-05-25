package com.example.atm_1;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    private Validator validateTest = new Validator();

    private String name;
    private int password;

    private String expectedNameResult;
    private String actualNameResult;

    private int expectedPasswordResult;
    private int actualPasswordResult;

    @Test
    public void validate() {
        name = "Tim";
        password = 1234;

        expectedNameResult = "Tim";
        expectedPasswordResult = 1234;
    }


}
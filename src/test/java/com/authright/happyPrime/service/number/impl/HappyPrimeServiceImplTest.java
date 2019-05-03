package com.authright.happyPrime.service.number.impl;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class HappyPrimeServiceImplTest {

    private HappyPrimeServiceImpl happyPrimeServiceImpl;


    private int inputNum;
    private boolean isPrime;
    private boolean isHappy;
    private boolean isHappyPrime;

    public HappyPrimeServiceImplTest(int inputNum, boolean isPrime, boolean isHappy, boolean isHappyPrime) {
        this.inputNum = inputNum;
        this.isHappy = isHappy;
        this.isPrime = isPrime;
        this.isHappyPrime = isHappyPrime;
    }

    @Before
    public void setUp( ) {
        this.happyPrimeServiceImpl = new HappyPrimeServiceImpl();
    }


    @Parameterized.Parameters(name = "{index}: input number:{0}, isPrime:{1}, isHappy:{2}, isHappyPrime: {3} ")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, false, true, false},
                {2, true, false, false},
                {19, true, true, true},
                {20, false, false, false},
                {167, true,true,true},
                {409, true, true, true},
                {487, true, true, true}
        });
    }

    @Test
    public void testIsHappy() {
        assertEquals(happyPrimeServiceImpl.isHappy(this.inputNum), this.isHappy);
    }


    @Test
    public void testIsPrime() {
        assertEquals(happyPrimeServiceImpl.isPrime(this.inputNum), this.isPrime);
    }

    @Test
    public void testIsHappyPrime() {
        assertEquals(this.isHappyPrime, this.isHappy && this.isPrime);
        assertEquals(happyPrimeServiceImpl.isHappyPrime(this.inputNum), this.isHappyPrime);
    }

}
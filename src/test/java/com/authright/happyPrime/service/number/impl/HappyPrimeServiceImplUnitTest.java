package com.authright.happyPrime.service.number.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HappyPrimeServiceImplUnitTest {

    private HappyPrimeServiceImpl happyPrimeServiceImpl;

    @Before
    public void setUp( ) {
        this.happyPrimeServiceImpl = new HappyPrimeServiceImpl();
    }

    @Test
    public void testIsHappy() {
        assertTrue(happyPrimeServiceImpl.isHappy(1));
        assertTrue(happyPrimeServiceImpl.isHappy(19));
        assertFalse(happyPrimeServiceImpl.isHappy(2));
        assertFalse(happyPrimeServiceImpl.isHappy(83));
    }


    @Test
    public void testIsPrime() {
        assertFalse(happyPrimeServiceImpl.isPrime(1));
        assertTrue(happyPrimeServiceImpl.isPrime(2));
        assertTrue(happyPrimeServiceImpl.isPrime(3));
        assertTrue(happyPrimeServiceImpl.isPrime(7));
        assertTrue(happyPrimeServiceImpl.isPrime(167));
        assertTrue(happyPrimeServiceImpl.isPrime(409));
        assertTrue(happyPrimeServiceImpl.isPrime(487));
        assertFalse(happyPrimeServiceImpl.isPrime(9821));
    }

    @Test
    public void testIsHappyPrime() {
        assertFalse(happyPrimeServiceImpl.isHappyPrime(2));
        assertFalse(happyPrimeServiceImpl.isHappyPrime(169));
        assertTrue(happyPrimeServiceImpl.isHappyPrime(167));
        assertTrue(happyPrimeServiceImpl.isHappyPrime(487));
       ;
    }

}
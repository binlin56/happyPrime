package com.authright.happyPrime.service.number.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.authright.happyPrime.service.number.HappyPrimeService;
import java.util.*;

@Service("happyPrimeService")
public class HappyPrimeServiceImpl implements HappyPrimeService {

    private static final Logger LOG = LogManager.getLogger(HappyPrimeServiceImpl.class);

	@Override
	public boolean isHappyPrime(long number) {
		return isPrime(number) && isHappy(number);
	}


	protected boolean isPrime(long n) {
		if (n <= 1) return false;
		if (n == 2 || n == 3) return true;

		if (n % 2 == 0 || n % 3 == 0) {
			//it's not a prime
			return false;
		}

		for ( long i = 5; i * i <= n; i += 6 ) {
			// odd numbers
			if ( n % i == 0 || n % ( i + 2 ) == 0 ) {
				return false;
			}
		}

		return true;
	}

	protected boolean isHappy(long num) {

		Set<Long> unique_num = new HashSet<>();

		while (unique_num.add(num))
		{
			long value = 0;
			while (num > 0)
			{
				value += Math.pow(num % 10, 2);
				num /= 10;
			}
			num = value;
		}

		return num == 1;
	}


}

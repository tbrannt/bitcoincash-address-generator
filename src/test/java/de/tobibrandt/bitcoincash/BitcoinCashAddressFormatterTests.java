package de.tobibrandt.bitcoincash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import de.tobibrandt.MoneyNetwork;

/**
 * Copyright (c) 2018 Tobias Brandt
 * 
 * Distributed under the MIT software license, see the accompanying file LICENSE
 * or http://www.opensource.org/licenses/mit-license.php.
 */
public class BitcoinCashAddressFormatterTests {

	Random random = new Random(94924425);

	@Test
	public void testCreateBitcoinCashAddressAndValidate() {
		byte[] sha256hash160 = new byte[] { 25, 94, 4, (byte) 141, 93, (byte) 189, 92, 111, 38, 2, 82, (byte) 185,
				(byte) 229, 9, 17, 63, (byte) 134, (byte) 217, 124, 42 };

		String cashAddress = BitcoinCashAddressFormatter.toCashAddress(BitcoinCashAddressType.P2PKH, sha256hash160,
				MoneyNetwork.MAIN);

		assertEquals("bitcoincash:qqv4upydtk74cmexqfftnegfzylcdktu9gdgnyngsj", cashAddress);
		assertTrue(BitcoinCashAddressFormatter.isValidCashAddress(cashAddress, MoneyNetwork.MAIN));
		assertTrue(BitcoinCashAddressFormatter
				.isValidCashAddress(cashAddress.split(BitcoinCashAddressFormatter.SEPARATOR)[1], MoneyNetwork.MAIN));

		assertFalse(BitcoinCashAddressFormatter
				.isValidCashAddress("bitcoincash:Qqv4upydtk74cmexqfftnegfzylcdktu9gdgnyngsj", MoneyNetwork.MAIN));
		assertTrue(BitcoinCashAddressFormatter
				.isValidCashAddress("bitcoincash:QQV4UPYDTK74CMEXQFFTNEGFZYLCDKTU9GDGNYNGSJ", MoneyNetwork.MAIN));

		byte[] randomBytes = new byte[20];
		for (long i = 0; i < 20000; i++) {
			random.nextBytes(randomBytes);

			String randomCashAddress = BitcoinCashAddressFormatter.toCashAddress(BitcoinCashAddressType.P2PKH,
					randomBytes, MoneyNetwork.MAIN);
			assertTrue(BitcoinCashAddressFormatter.isValidCashAddress(randomCashAddress, MoneyNetwork.MAIN));

			String randomTestCashAddress = BitcoinCashAddressFormatter.toCashAddress(BitcoinCashAddressType.P2PKH,
					randomBytes, MoneyNetwork.TEST);
			assertTrue(BitcoinCashAddressFormatter.isValidCashAddress(randomTestCashAddress, MoneyNetwork.TEST));
		}
	}

	@Test
	public void testChecksumTestsFromSpec() {
		assertTrue(BitcoinCashAddressFormatter.isValidCashAddress("prefix:x64nx6hz", MoneyNetwork.MAIN));
		assertTrue(BitcoinCashAddressFormatter.isValidCashAddress("p:gpf8m4h7", MoneyNetwork.MAIN));
		assertTrue(BitcoinCashAddressFormatter
				.isValidCashAddress("bitcoincash:qpzry9x8gf2tvdw0s3jn54khce6mua7lcw20ayyn", MoneyNetwork.MAIN));
		assertFalse(
				BitcoinCashAddressFormatter.isValidCashAddress("bchtest:testnetaddress4d6njnut", MoneyNetwork.MAIN));
		assertTrue(BitcoinCashAddressFormatter.isValidCashAddress("bchtest:testnetaddress4d6njnut", MoneyNetwork.TEST));
		assertTrue(BitcoinCashAddressFormatter
				.isValidCashAddress("bchreg:555555555555555555555555555555555555555555555udxmlmrz", MoneyNetwork.MAIN));
	}
}

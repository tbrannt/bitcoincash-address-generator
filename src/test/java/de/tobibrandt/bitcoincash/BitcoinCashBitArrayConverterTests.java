package de.tobibrandt.bitcoincash;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * Copyright (c) 2018 Tobias Brandt
 * 
 * Distributed under the MIT software license, see the accompanying file LICENSE
 * or http://www.opensource.org/licenses/mit-license.php.
 */
public class BitcoinCashBitArrayConverterTests {

	@Test
	public void test8To5BitArrayConverter() {
		byte[] from = { (byte) 0b11001101, (byte) 0b11011101 };
		byte[] expectedTo = { 0b00011001, 0b00010111, 0b00001110, 0b00010000 };

		byte[] convertBits = BitcoinCashBitArrayConverter.convertBits(from, 8, 5, false);

		assertArrayEquals(expectedTo, convertBits);

		from = new byte[] { 0b01010101, 0b01010101 };
		expectedTo = new byte[] { 0b00001010, 0b00010101, 0b00001010, 0b00010000 };

		convertBits = BitcoinCashBitArrayConverter.convertBits(from, 8, 5, false);

		assertArrayEquals(expectedTo, convertBits);

		from = new byte[] { 0b01010101, 0b01010101, 0b01010101 };
		expectedTo = new byte[] { 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001010 };

		convertBits = BitcoinCashBitArrayConverter.convertBits(from, 8, 5, false);

		assertArrayEquals(expectedTo, convertBits);

		from = new byte[] { 0b01010101, 0b01010101, 0b01010101, 0b01010101 };
		expectedTo = new byte[] { 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001000 };

		convertBits = BitcoinCashBitArrayConverter.convertBits(from, 8, 5, false);

		assertArrayEquals(expectedTo, convertBits);

		from = new byte[] { 0b01010101, 0b01010101, 0b01010101, 0b01010101, 0b01010101 };
		expectedTo = new byte[] { 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001010,
				0b00010101 };

		convertBits = BitcoinCashBitArrayConverter.convertBits(from, 8, 5, false);

		assertArrayEquals(expectedTo, convertBits);

		from = new byte[] { 0b01010101, 0b01010101, 0b01010101, 0b01010101, 0b01010101, 0b01010101 };
		expectedTo = new byte[] { 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001010,
				0b00010101, 0b00001010, 0b00010100 };

		convertBits = BitcoinCashBitArrayConverter.convertBits(from, 8, 5, false);

		assertArrayEquals(expectedTo, convertBits);

		from = new byte[] { (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111,
				(byte) 0b11111111, (byte) 0b11111111 };
		expectedTo = new byte[] { 0b00011111, 0b00011111, 0b00011111, 0b00011111, 0b00011111, 0b00011111, 0b00011111,
				0b00011111, 0b00011111, 0b00011100 };

		convertBits = BitcoinCashBitArrayConverter.convertBits(from, 8, 5, false);

		assertArrayEquals(expectedTo, convertBits);
	}

	@Test
	public void test5To8BitArrayConverter() {
		byte[] from = { 0b00011001, 0b00010111, 0b00001110, 0b00010000 };
		byte[] expectedTo = { (byte) 0b11001101, (byte) 0b11011101 };

		byte[] convertBits = BitcoinCashBitArrayConverter.convertBits(from, 5, 8, true);

		assertArrayEquals(expectedTo, convertBits);

		from = new byte[] { 0b00001010, 0b00010101, 0b00001010, 0b00010000 };
		expectedTo = new byte[] { 0b01010101, 0b01010101 };

		convertBits = BitcoinCashBitArrayConverter.convertBits(from, 5, 8, true);

		assertArrayEquals(expectedTo, convertBits);

		from = new byte[] { 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001010 };
		expectedTo = new byte[] { 0b01010101, 0b01010101, 0b01010101 };

		convertBits = BitcoinCashBitArrayConverter.convertBits(from, 5, 8, true);

		assertArrayEquals(expectedTo, convertBits);

		from = new byte[] { 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001000 };
		expectedTo = new byte[] { 0b01010101, 0b01010101, 0b01010101, 0b01010101 };

		convertBits = BitcoinCashBitArrayConverter.convertBits(from, 5, 8, true);

		assertArrayEquals(expectedTo, convertBits);

		from = new byte[] { 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001010,
				0b00010101 };
		expectedTo = new byte[] { 0b01010101, 0b01010101, 0b01010101, 0b01010101, 0b01010101 };

		convertBits = BitcoinCashBitArrayConverter.convertBits(from, 5, 8, true);

		assertArrayEquals(expectedTo, convertBits);

		from = new byte[] { 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001010, 0b00010101, 0b00001010,
				0b00010101, 0b00001010, 0b00010100 };
		expectedTo = new byte[] { 0b01010101, 0b01010101, 0b01010101, 0b01010101, 0b01010101, 0b01010101 };

		convertBits = BitcoinCashBitArrayConverter.convertBits(from, 5, 8, true);

		assertArrayEquals(expectedTo, convertBits);

		from = new byte[] { 0b00011111, 0b00011111, 0b00011111, 0b00011111, 0b00011111, 0b00011111, 0b00011111,
				0b00011111, 0b00011111, 0b00011100 };
		expectedTo = new byte[] { (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111, (byte) 0b11111111,
				(byte) 0b11111111, (byte) 0b11111111 };

		convertBits = BitcoinCashBitArrayConverter.convertBits(from, 5, 8, true);

		assertArrayEquals(expectedTo, convertBits);
	}

}

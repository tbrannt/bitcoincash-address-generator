package de.tobibrandt.bitcoincash;

import java.math.BigInteger;

import de.tobibrandt.MoneyNetwork;
import de.tobibrandt.Utils;

/**
 * Copyright (c) 2018 Tobias Brandt
 * 
 * Distributed under the MIT software license, see the accompanying file LICENSE
 * or http://www.opensource.org/licenses/mit-license.php.
 */
public class BitcoinCashAddressFormatter {

	public static final String SEPARATOR = ":";

	public static final String MAIN_NET_PREFIX = "bitcoincash";

	public static final String TEST_NET_PREFIX = "bchtest";

	public static final String ASSUMED_DEFAULT_PREFIX = MAIN_NET_PREFIX;

	private static final BigInteger[] POLYMOD_GENERATORS = new BigInteger[] { new BigInteger("98f2bc8e61", 16),
			new BigInteger("79b76d99e2", 16), new BigInteger("f33e5fb3c4", 16), new BigInteger("ae2eabe2a8", 16),
			new BigInteger("1e4f43e470", 16) };

	private static final BigInteger POLYMOD_AND_CONSTANT = new BigInteger("07ffffffff", 16);

	public static String toCashAddress(BitcoinCashAddressType addressType, byte[] hash, MoneyNetwork network) {
		String prefixString = getPrefixString(network);
		byte[] prefixBytes = getPrefixBytes(prefixString, network);
		byte[] payloadBytes = Utils.concatenateByteArrays(new byte[] { addressType.getVersionByte() }, hash);
		payloadBytes = BitcoinCashBitArrayConverter.convertBits(payloadBytes, 8, 5, false);
		byte[] allChecksumInput = Utils.concatenateByteArrays(
				Utils.concatenateByteArrays(Utils.concatenateByteArrays(prefixBytes, new byte[] { 0 }), payloadBytes),
				new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 });
		byte[] checksumBytes = calculateChecksumBytesPolymod(allChecksumInput);
		checksumBytes = BitcoinCashBitArrayConverter.convertBits(checksumBytes, 8, 5, true);
		String cashAddress = BitcoinCashBase32.encode(Utils.concatenateByteArrays(payloadBytes, checksumBytes));
		return prefixString + SEPARATOR + cashAddress;
	}

	public static boolean isValidCashAddress(String bitcoinCashAddress, MoneyNetwork moneyNetwork) {
		String prefix;
		if (bitcoinCashAddress.contains(SEPARATOR)) {
			String[] split = bitcoinCashAddress.split(SEPARATOR);
			prefix = split[0];
			bitcoinCashAddress = split[1];

			if (MAIN_NET_PREFIX.equals(prefix) && !moneyNetwork.equals(MoneyNetwork.MAIN)) {
				return false;
			}
			if (TEST_NET_PREFIX.equals(prefix) && !moneyNetwork.equals(MoneyNetwork.TEST)) {
				return false;
			}
		} else {
			prefix = moneyNetwork == MoneyNetwork.MAIN ? MAIN_NET_PREFIX : TEST_NET_PREFIX;
		}

		if (!isSingleCase(bitcoinCashAddress))
			return false;

		bitcoinCashAddress = bitcoinCashAddress.toLowerCase();

		byte[] checksumData = Utils.concatenateByteArrays(
				Utils.concatenateByteArrays(getPrefixBytes(prefix, moneyNetwork), new byte[] { 0x00 }),
				BitcoinCashBase32.decode(bitcoinCashAddress));

		byte[] calculateChecksumBytesPolymod = calculateChecksumBytesPolymod(checksumData);
		return new BigInteger(calculateChecksumBytesPolymod).compareTo(BigInteger.ZERO) == 0;
	}

	private static boolean isSingleCase(String bitcoinCashAddress) {
		if (bitcoinCashAddress.equals(bitcoinCashAddress.toLowerCase())) {
			return true;
		}
		if (bitcoinCashAddress.equals(bitcoinCashAddress.toUpperCase())) {
			return true;
		}

		return false;
	}

	/**
	 * @param checksumInput
	 * @return Returns a 40 bits checksum in form of 5 8-bit arrays. This still
	 *         has to me mapped to 5-bit array representation
	 */
	private static byte[] calculateChecksumBytesPolymod(byte[] checksumInput) {
		BigInteger c = BigInteger.ONE;

		for (int i = 0; i < checksumInput.length; i++) {
			byte c0 = c.shiftRight(35).byteValue();
			c = c.and(POLYMOD_AND_CONSTANT).shiftLeft(5)
					.xor(new BigInteger(String.format("%02x", checksumInput[i]), 16));

			if ((c0 & 0x01) != 0)
				c = c.xor(POLYMOD_GENERATORS[0]);
			if ((c0 & 0x02) != 0)
				c = c.xor(POLYMOD_GENERATORS[1]);
			if ((c0 & 0x04) != 0)
				c = c.xor(POLYMOD_GENERATORS[2]);
			if ((c0 & 0x08) != 0)
				c = c.xor(POLYMOD_GENERATORS[3]);
			if ((c0 & 0x10) != 0)
				c = c.xor(POLYMOD_GENERATORS[4]);
		}

		byte[] checksum = c.xor(BigInteger.ONE).toByteArray();
		if (checksum.length == 5) {
			return checksum;
		} else {
			byte[] newChecksumArray = new byte[5];

			System.arraycopy(checksum, Math.max(0, checksum.length - 5), newChecksumArray,
					Math.max(0, 5 - checksum.length), Math.min(5, checksum.length));

			return newChecksumArray;
		}

	}

	private static byte[] getPrefixBytes(String prefixString, MoneyNetwork network) {
		byte[] prefixBytes = new byte[prefixString.length()];

		char[] charArray = prefixString.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			prefixBytes[i] = (byte) (charArray[i] & 0x1f);
		}

		return prefixBytes;
	}

	private static String getPrefixString(MoneyNetwork network) {
		switch (network) {
		case MAIN:
			return MAIN_NET_PREFIX;
		case TEST:
			return TEST_NET_PREFIX;
		default:
			throw new RuntimeException("MoneyNetwork not handled yet");
		}
	}

}

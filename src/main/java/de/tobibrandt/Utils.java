package de.tobibrandt;

public class Utils {

	/**
	 * Concatenates the two given byte arrays and returns the combined byte
	 * array.
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static byte[] concatenateByteArrays(byte[] first, byte[] second) {
		byte[] concatenatedBytes = new byte[first.length + second.length];

		System.arraycopy(first, 0, concatenatedBytes, 0, first.length);
		System.arraycopy(second, 0, concatenatedBytes, first.length, second.length);

		return concatenatedBytes;
	}

}

package de.tobibrandt.bitcoincash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Copyright (c) 2018 Tobias Brandt
 * 
 * Distributed under the MIT software license, see the accompanying file LICENSE
 * or http://www.opensource.org/licenses/mit-license.php.
 */
public class BitcoinCashBase32Tests {

	@Test
	public void testDecode() {
		// 19 4 30 15
		byte[] decoded = BitcoinCashBase32.decode("ny70");
		assertEquals(19, decoded[0]);
		assertEquals(4, decoded[1]);
		assertEquals(30, decoded[2]);
		assertEquals(15, decoded[3]);
	}

	@Test
	public void testEncode() {
		// qy0g7w
		String encoded = BitcoinCashBase32.encode(new byte[] { 0, 4, 15, 8, 30, 14 });
		assertEquals('q', encoded.charAt(0));
		assertEquals('y', encoded.charAt(1));
		assertEquals('0', encoded.charAt(2));
		assertEquals('g', encoded.charAt(3));
		assertEquals('7', encoded.charAt(4));
		assertEquals('w', encoded.charAt(5));
	}

	@Test
	public void testDecodeValidBase32StringsWithoutError() {
		BitcoinCashBase32.decode("qzntaenwx59e26hn3pz0azpjezps3mt28sx8upcvwz");
		BitcoinCashBase32.decode("qpr3xsss5nxrpk6u5yf08kzsjtmyl97st57gffs20u");
		BitcoinCashBase32.decode("qz6wc9egsuqxes22yzqdhkqefmr56y32zuw2079yq8");
		BitcoinCashBase32.decode("qr6ul07l6u5988ccfdz84m8796rvzk2x9yvezdffl6");
		BitcoinCashBase32.decode("qzy9l663eac39zed396lw075m38w3ntff5lq3ux4ff");
		BitcoinCashBase32.decode("qqe8ajfagu6wdnxz6lt3c3fynas9zsgp4cld5ukalf");
		BitcoinCashBase32.decode("qpjhpve89zgqdtep25knrvw40zs0as9r6vt3w70dsm");
		BitcoinCashBase32.decode("qzgnc3p466364pyua93dy4fgw0kcftxu0sw0lsqcel");
		BitcoinCashBase32.decode("qrg6pcmeg2fam5nd2ls7me7qzs426vfjr5gyduzecs");
		BitcoinCashBase32.decode("qpae6z34dnp745wm4u8hewnvuvejx9fraqt745wf0u");

		try {
			BitcoinCashBase32.decode("qpae6z34dnp745wm4u8hebwnvuvejx9fraqt745wf0u");
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.decode("qpae6z34dnp745wm4u8hewNv1uvejx9fraqt745wf0u");
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.decode("qpae6z34dnp745wm4u8iewnvuvejx9fraqt745wf0u");
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

	}

	@Test
	public void testWorksOnlyWith5BitBytes() {
		// works
		byte[] a = { 0x00 };
		byte[] b = { 0x01 };
		byte[] c = { 0x02 };
		byte[] d = { 0x03 };
		byte[] e = { 0x0f };
		byte[] f = { 0x10 };
		byte[] g = { 0x1e };
		byte[] h = { 0x1f };

		BitcoinCashBase32.encode(a);
		BitcoinCashBase32.encode(b);
		BitcoinCashBase32.encode(c);
		BitcoinCashBase32.encode(d);
		BitcoinCashBase32.encode(e);
		BitcoinCashBase32.encode(f);
		BitcoinCashBase32.encode(g);
		BitcoinCashBase32.encode(h);

		// does not work
		byte[] i = { 0x20 };
		byte[] j = { 0x21 };
		byte[] k = { 0x2f };
		byte[] l = { 0x30 };
		byte[] m = { 0x31 };
		byte[] n = { 0x32 };
		byte[] o = { 0x42 };
		byte[] p = { 0x56 };
		byte[] q = { 0x7e };
		byte[] r = { (byte) 0xa1 };
		byte[] s = { (byte) 0xaf };
		byte[] t = { (byte) 0xb4 };
		byte[] u = { (byte) 0xbe };
		byte[] v = { (byte) 0xc5 };
		byte[] w = { (byte) 0xd7 };
		byte[] x = { (byte) 0xea };
		byte[] y = { (byte) 0xf4 };
		byte[] z = { (byte) 0xff };

		try {
			BitcoinCashBase32.encode(i);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(j);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(k);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(l);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(m);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(n);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(o);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(p);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(q);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(r);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(s);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(t);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(u);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(v);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(w);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(x);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(y);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

		try {
			BitcoinCashBase32.encode(z);
			fail("must have thrown");
		} catch (RuntimeException ex) {
		}

	}
}

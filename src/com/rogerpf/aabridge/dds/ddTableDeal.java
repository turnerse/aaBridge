package com.rogerpf.aabridge.dds;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * <i>native declaration : line 213</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class ddTableDeal extends Structure {
	/** C type : unsigned int[4][4] */
	public int[] cards = new int[((4) * (4))];

	public ddTableDeal() {
		super();
	}

	protected List<?> getFieldOrder() {
		return Arrays.asList("cards");
	}

	/** @param cards C type : unsigned int[4][4] */
	public ddTableDeal(int cards[]) {
		super();
		if ((cards.length != this.cards.length))
			throw new IllegalArgumentException("Wrong array size !");
		this.cards = cards;
	}

	public ddTableDeal(Pointer peer) {
		super(peer);
	}

	public static class ByReference extends ddTableDeal implements Structure.ByReference {

	};

	public static class ByValue extends ddTableDeal implements Structure.ByValue {

	};
}

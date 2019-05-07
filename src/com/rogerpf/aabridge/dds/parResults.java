package com.rogerpf.aabridge.dds;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * <i>native declaration : line 246</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class parResults extends Structure {
	/** C type : char[2][16] */
	public byte[] parScore = new byte[((2) * (16))];
	/** C type : char[2][128] */
	public byte[] parContractsString = new byte[((2) * (128))];

	public parResults() {
		super();
	}

	protected List<?> getFieldOrder() {
		return Arrays.asList("parScore", "parContractsString");
	}

	/**
	 * @param parScore C type : char[2][16]<br>
	 * @param parContractsString C type : char[2][128]
	 */
	public parResults(byte parScore[], byte parContractsString[]) {
		super();
		if ((parScore.length != this.parScore.length))
			throw new IllegalArgumentException("Wrong array size !");
		this.parScore = parScore;
		if ((parContractsString.length != this.parContractsString.length))
			throw new IllegalArgumentException("Wrong array size !");
		this.parContractsString = parContractsString;
	}

	public parResults(Pointer peer) {
		super(peer);
	}

	public static class ByReference extends parResults implements Structure.ByReference {

	};

	public static class ByValue extends parResults implements Structure.ByValue {

	};
}

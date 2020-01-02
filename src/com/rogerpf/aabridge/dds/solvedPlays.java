package com.rogerpf.aabridge.dds;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * <i>native declaration : line 326</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class solvedPlays extends Structure {
	public int noOfBoards;
	/** C type : solvedPlay[200] */
	public solvedPlay[] solved = new solvedPlay[200];

	public solvedPlays() {
		super();
	}

	protected List<?> getFieldOrder() {
		return Arrays.asList("noOfBoards", "solved");
	}

	/** @param solved C type : solvedPlay[200] */
	public solvedPlays(int noOfBoards, solvedPlay solved[]) {
		super();
		this.noOfBoards = noOfBoards;
		if ((solved.length != this.solved.length))
			throw new IllegalArgumentException("Wrong array size !");
		this.solved = solved;
	}

	public solvedPlays(Pointer peer) {
		super(peer);
	}

	public static class ByReference extends solvedPlays implements Structure.ByReference {

	};

	public static class ByValue extends solvedPlays implements Structure.ByValue {

	};
}
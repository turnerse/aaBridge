package com.rogerpf.aabridge.dds;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

/**
 * JNA Wrapper for library <b>com.rogerpf.aabridge.dds</b><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public interface ComRogerpfAabridgeDdsLibrary extends Library {
	public static final String JNA_LIBRARY_NAME = "com.rogerpf.aabridge.dds";
	public static final NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(ComRogerpfAabridgeDdsLibrary.JNA_LIBRARY_NAME);
	public static final ComRogerpfAabridgeDdsLibrary INSTANCE = (ComRogerpfAabridgeDdsLibrary) Native.loadLibrary(ComRogerpfAabridgeDdsLibrary.JNA_LIBRARY_NAME,
			ComRogerpfAabridgeDdsLibrary.class);
	public static final int DDS_VERSION = (int) 20801;
	public static final int DDS_HANDS = (int) 4;
	public static final int DDS_SUITS = (int) 4;
	public static final int DDS_STRAINS = (int) 5;
	public static final int MAXNOOFBOARDS = (int) 200;
	public static final int MAXNOOFTABLES = (int) 32;
	public static final int RETURN_NO_FAULT = (int) 1;
	public static final String TEXT_NO_FAULT = (String) "Success";
	public static final int RETURN_UNKNOWN_FAULT = (int) -1;
	public static final String TEXT_UNKNOWN_FAULT = (String) "General error";
	public static final int RETURN_ZERO_CARDS = (int) -2;
	public static final String TEXT_ZERO_CARDS = (String) "Zero cards";
	public static final int RETURN_TARGET_TOO_HIGH = (int) -3;
	public static final String TEXT_TARGET_TOO_HIGH = (String) "Target exceeds number of tricks";
	public static final int RETURN_DUPLICATE_CARDS = (int) -4;
	public static final String TEXT_DUPLICATE_CARDS = (String) "Cards duplicated";
	public static final int RETURN_TARGET_WRONG_LO = (int) -5;
	public static final String TEXT_TARGET_WRONG_LO = (String) "Target is less than -1";
	public static final int RETURN_TARGET_WRONG_HI = (int) -7;
	public static final String TEXT_TARGET_WRONG_HI = (String) "Target is higher than 13";
	public static final int RETURN_SOLNS_WRONG_LO = (int) -8;
	public static final String TEXT_SOLNS_WRONG_LO = (String) "Solutions parameter is less than 1";
	public static final int RETURN_SOLNS_WRONG_HI = (int) -9;
	public static final String TEXT_SOLNS_WRONG_HI = (String) "Solutions parameter is higher than 3";
	public static final int RETURN_TOO_MANY_CARDS = (int) -10;
	public static final String TEXT_TOO_MANY_CARDS = (String) "Too many cards";
	public static final int RETURN_SUIT_OR_RANK = (int) -12;
	public static final String TEXT_SUIT_OR_RANK = (String) "currentTrickSuit or currentTrickRank has wrong data";
	public static final int RETURN_PLAYED_CARD = (int) -13;
	public static final String TEXT_PLAYED_CARD = (String) "Played card also remains in a hand";
	public static final int RETURN_CARD_COUNT = (int) -14;
	public static final String TEXT_CARD_COUNT = (String) "Wrong number of remaining cards in a hand";
	public static final int RETURN_THREAD_INDEX = (int) -15;
	public static final String TEXT_THREAD_INDEX = (String) "Thread index is not 0 .. maximum";
	public static final int RETURN_MODE_WRONG_LO = (int) -16;
	public static final String TEXT_MODE_WRONG_LO = (String) "Mode parameter is less than 0";
	public static final int RETURN_MODE_WRONG_HI = (int) -17;
	public static final String TEXT_MODE_WRONG_HI = (String) "Mode parameter is higher than 2";
	public static final int RETURN_TRUMP_WRONG = (int) -18;
	public static final String TEXT_TRUMP_WRONG = (String) "Trump is not in 0 .. 4";
	public static final int RETURN_FIRST_WRONG = (int) -19;
	public static final String TEXT_FIRST_WRONG = (String) "First is not in 0 .. 2";
	public static final int RETURN_PLAY_FAULT = (int) -98;
	public static final String TEXT_PLAY_FAULT = (String) "AnalysePlay input error";
	public static final int RETURN_PBN_FAULT = (int) -99;
	public static final String TEXT_PBN_FAULT = (String) "PBN string error";
	public static final int RETURN_TOO_MANY_BOARDS = (int) -101;
	public static final String TEXT_TOO_MANY_BOARDS = (String) "Too many boards requested";
	public static final int RETURN_THREAD_CREATE = (int) -102;
	public static final String TEXT_THREAD_CREATE = (String) "Could not create threads";
	public static final int RETURN_THREAD_WAIT = (int) -103;
	public static final String TEXT_THREAD_WAIT = (String) "Something failed waiting for thread to end";
	public static final int RETURN_NO_SUIT = (int) -201;
	public static final String TEXT_NO_SUIT = (String) "Denomination filter vector has no entries";
	public static final int RETURN_TOO_MANY_TABLES = (int) -202;
	public static final String TEXT_TOO_MANY_TABLES = (String) "Too many DD tables requested";
	public static final int RETURN_CHUNK_SIZE = (int) -301;
	public static final String TEXT_CHUNK_SIZE = (String) "Chunk size is less than 1";

	/**
	 * Original signature : <code>void SetMaxThreads(int)</code><br>
	 * <i>native declaration : line 334</i>
	 */
	void SetMaxThreads(int userThreads);

	/**
	 * Original signature : <code>void FreeMemory()</code><br>
	 * <i>native declaration : line 337</i>
	 */
	void FreeMemory();

	/**
	 * Original signature : <code>int SolveBoard(deal, int, int, int, futureTricks*, int)</code><br>
	 * <i>native declaration : line 339</i>
	 */
	int SolveBoard(com.rogerpf.aabridge.dds.deal.ByValue dl, int target, int solutions, int mode, futureTricks futp, int threadIndex);

	/**
	 * Original signature : <code>int SolveBoardPBN(dealPBN, int, int, int, futureTricks*, int)</code><br>
	 * <i>native declaration : line 347</i>
	 */
	int SolveBoardPBN(com.rogerpf.aabridge.dds.dealPBN.ByValue dlpbn, int target, int solutions, int mode, futureTricks futp, int thrId);

	/**
	 * Original signature : <code>int CalcDDtable(ddTableDeal, ddTableResults*)</code><br>
	 * <i>native declaration : line 355</i>
	 */
	int CalcDDtable(com.rogerpf.aabridge.dds.ddTableDeal.ByValue tableDeal, ddTableResults tablep);

	/**
	 * Original signature : <code>int CalcDDtablePBN(ddTableDealPBN, ddTableResults*)</code><br>
	 * <i>native declaration : line 359</i>
	 */
	int CalcDDtablePBN(com.rogerpf.aabridge.dds.ddTableDealPBN.ByValue tableDealPBN, ddTableResults tablep);

	/**
	 * Original signature : <code>int CalcAllTables(ddTableDeals*, int, int[5], ddTablesRes*, allParResults*)</code><br>
	 * <i>native declaration : line 363</i><br>
	 * @deprecated use the safer methods {@link #CalcAllTables(com.rogerpf.aabridge.dds.ddTableDeals, int, java.nio.IntBuffer, com.rogerpf.aabridge.dds.ddTablesRes, com.rogerpf.aabridge.dds.allParResults)} and {@link #CalcAllTables(com.rogerpf.aabridge.dds.ddTableDeals, int, com.sun.jna.ptr.IntByReference, com.rogerpf.aabridge.dds.ddTablesRes, com.rogerpf.aabridge.dds.allParResults)} instead
	 */
	@Deprecated
	int CalcAllTables(ddTableDeals dealsp, int mode, IntByReference trumpFilter, ddTablesRes resp, allParResults presp);

	/**
	 * Original signature : <code>int CalcAllTables(ddTableDeals*, int, int[5], ddTablesRes*, allParResults*)</code><br>
	 * <i>native declaration : line 363</i>
	 */
	int CalcAllTables(ddTableDeals dealsp, int mode, IntBuffer trumpFilter, ddTablesRes resp, allParResults presp);

	/**
	 * Original signature : <code>int CalcAllTablesPBN(ddTableDealsPBN*, int, int[5], ddTablesRes*, allParResults*)</code><br>
	 * <i>native declaration : line 370</i><br>
	 * @deprecated use the safer methods {@link #CalcAllTablesPBN(com.rogerpf.aabridge.dds.ddTableDealsPBN, int, java.nio.IntBuffer, com.rogerpf.aabridge.dds.ddTablesRes, com.rogerpf.aabridge.dds.allParResults)} and {@link #CalcAllTablesPBN(com.rogerpf.aabridge.dds.ddTableDealsPBN, int, com.sun.jna.ptr.IntByReference, com.rogerpf.aabridge.dds.ddTablesRes, com.rogerpf.aabridge.dds.allParResults)} instead
	 */
	@Deprecated
	int CalcAllTablesPBN(ddTableDealsPBN dealsp, int mode, IntByReference trumpFilter, ddTablesRes resp, allParResults presp);

	/**
	 * Original signature : <code>int CalcAllTablesPBN(ddTableDealsPBN*, int, int[5], ddTablesRes*, allParResults*)</code><br>
	 * <i>native declaration : line 370</i>
	 */
	int CalcAllTablesPBN(ddTableDealsPBN dealsp, int mode, IntBuffer trumpFilter, ddTablesRes resp, allParResults presp);

	/**
	 * Original signature : <code>int SolveAllBoards(boardsPBN*, solvedBoards*)</code><br>
	 * <i>native declaration : line 377</i>
	 */
	int SolveAllBoards(boardsPBN bop, solvedBoards solvedp);

	/**
	 * Original signature : <code>int SolveAllChunks(boardsPBN*, solvedBoards*, int)</code><br>
	 * <i>native declaration : line 381</i>
	 */
	int SolveAllChunks(boardsPBN bop, solvedBoards solvedp, int chunkSize);

	/**
	 * Original signature : <code>int SolveAllChunksBin(boards*, solvedBoards*, int)</code><br>
	 * <i>native declaration : line 386</i>
	 */
	int SolveAllChunksBin(boards bop, solvedBoards solvedp, int chunkSize);

	/**
	 * Original signature : <code>int SolveAllChunksPBN(boardsPBN*, solvedBoards*, int)</code><br>
	 * <i>native declaration : line 391</i>
	 */
	int SolveAllChunksPBN(boardsPBN bop, solvedBoards solvedp, int chunkSize);

	/**
	 * Original signature : <code>int Par(ddTableResults*, parResults*, int)</code><br>
	 * <i>native declaration : line 396</i>
	 */
	int Par(ddTableResults tablep, parResults presp, int vulnerable);

	/**
	 * Original signature : <code>int CalcPar(ddTableDeal, int, ddTableResults*, parResults*)</code><br>
	 * <i>native declaration : line 401</i>
	 */
	int CalcPar(com.rogerpf.aabridge.dds.ddTableDeal.ByValue tableDeal, int vulnerable, ddTableResults tablep, parResults presp);

	/**
	 * Original signature : <code>int CalcParPBN(ddTableDealPBN, ddTableResults*, int, parResults*)</code><br>
	 * <i>native declaration : line 407</i>
	 */
	int CalcParPBN(com.rogerpf.aabridge.dds.ddTableDealPBN.ByValue tableDealPBN, ddTableResults tablep, int vulnerable, parResults presp);

	/**
	 * Original signature : <code>int SidesPar(ddTableResults*, parResultsDealer[2], int)</code><br>
	 * <i>native declaration : line 413</i><br>
	 * @deprecated use the safer method {@link #SidesPar(com.rogerpf.aabridge.dds.ddTableResults, com.rogerpf.aabridge.dds.parResultsDealer[], int)} instead
	 */
	@Deprecated
	int SidesPar(ddTableResults tablep, Pointer sidesRes, int vulnerable);

	/**
	 * Original signature : <code>int SidesPar(ddTableResults*, parResultsDealer[2], int)</code><br>
	 * <i>native declaration : line 413</i>
	 */
	int SidesPar(ddTableResults tablep, parResultsDealer sidesRes[], int vulnerable);

	/**
	 * Original signature : <code>int DealerPar(ddTableResults*, parResultsDealer*, int, int)</code><br>
	 * <i>native declaration : line 418</i>
	 */
	int DealerPar(ddTableResults tablep, parResultsDealer presp, int dealer, int vulnerable);

	/**
	 * Original signature : <code>int DealerParBin(ddTableResults*, parResultsMaster*, int, int)</code><br>
	 * <i>native declaration : line 424</i>
	 */
	int DealerParBin(ddTableResults tablep, parResultsMaster presp, int dealer, int vulnerable);

	/**
	 * Original signature : <code>int SidesParBin(ddTableResults*, parResultsMaster[2], int)</code><br>
	 * <i>native declaration : line 430</i><br>
	 * @deprecated use the safer method {@link #SidesParBin(com.rogerpf.aabridge.dds.ddTableResults, com.rogerpf.aabridge.dds.parResultsMaster[], int)} instead
	 */
	@Deprecated
	int SidesParBin(ddTableResults tablep, Pointer sidesRes, int vulnerable);

	/**
	 * Original signature : <code>int SidesParBin(ddTableResults*, parResultsMaster[2], int)</code><br>
	 * <i>native declaration : line 430</i>
	 */
	int SidesParBin(ddTableResults tablep, parResultsMaster sidesRes[], int vulnerable);

	/**
	 * Original signature : <code>int ConvertToDealerTextFormat(parResultsMaster*, char*)</code><br>
	 * <i>native declaration : line 435</i><br>
	 * @deprecated use the safer methods {@link #ConvertToDealerTextFormat(com.rogerpf.aabridge.dds.parResultsMaster, java.nio.ByteBuffer)} and {@link #ConvertToDealerTextFormat(com.rogerpf.aabridge.dds.parResultsMaster, com.sun.jna.Pointer)} instead
	 */
	@Deprecated
	int ConvertToDealerTextFormat(parResultsMaster pres, Pointer resp);

	/**
	 * Original signature : <code>int ConvertToDealerTextFormat(parResultsMaster*, char*)</code><br>
	 * <i>native declaration : line 435</i>
	 */
	int ConvertToDealerTextFormat(parResultsMaster pres, ByteBuffer resp);

	/**
	 * Original signature : <code>int ConvertToSidesTextFormat(parResultsMaster*, parTextResults*)</code><br>
	 * <i>native declaration : line 439</i>
	 */
	int ConvertToSidesTextFormat(parResultsMaster pres, parTextResults resp);

	/**
	 * Original signature : <code>int AnalysePlayBin(deal, playTraceBin, solvedPlay*, int)</code><br>
	 * <i>native declaration : line 443</i>
	 */
	int AnalysePlayBin(com.rogerpf.aabridge.dds.deal.ByValue dl, com.rogerpf.aabridge.dds.playTraceBin.ByValue play, solvedPlay solved, int thrId);

	/**
	 * Original signature : <code>int AnalysePlayPBN(dealPBN, playTracePBN, solvedPlay*, int)</code><br>
	 * <i>native declaration : line 449</i>
	 */
	int AnalysePlayPBN(com.rogerpf.aabridge.dds.dealPBN.ByValue dlPBN, com.rogerpf.aabridge.dds.playTracePBN.ByValue playPBN, solvedPlay solvedp, int thrId);

	/**
	 * Original signature : <code>int AnalyseAllPlaysBin(boards*, playTracesBin*, solvedPlays*, int)</code><br>
	 * <i>native declaration : line 455</i>
	 */
	int AnalyseAllPlaysBin(boards bop, playTracesBin plp, solvedPlays solvedp, int chunkSize);

	/**
	 * Original signature : <code>int AnalyseAllPlaysPBN(boardsPBN*, playTracesPBN*, solvedPlays*, int)</code><br>
	 * <i>native declaration : line 461</i>
	 */
	int AnalyseAllPlaysPBN(boardsPBN bopPBN, playTracesPBN plpPBN, solvedPlays solvedp, int chunkSize);

	/**
	 * Original signature : <code>void ErrorMessage(int, char[80])</code><br>
	 * <i>native declaration : line 467</i><br>
	 * @deprecated use the safer methods {@link #ErrorMessage(int, java.nio.ByteBuffer)} and {@link #ErrorMessage(int, com.sun.jna.Pointer)} instead
	 */
	@Deprecated
	void ErrorMessage(int code, Pointer line);

	/**
	 * Original signature : <code>void ErrorMessage(int, char[80])</code><br>
	 * <i>native declaration : line 467</i>
	 */
	void ErrorMessage(int code, ByteBuffer line);
}

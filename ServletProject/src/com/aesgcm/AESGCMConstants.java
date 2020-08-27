/**
 * 
 */
package com.aesgcm;

/**
 * @author TP21291930
 *
 */
public class AESGCMConstants {
	
	public static int AES_KEY_SIZE = 128;
	public static final int GCM_IV_LENGTH = 12;
	public static final int GCM_TAG_LENGTH = 16;
	public static int ITERATION_COUNT = 1;
	public static final String SALT = "abcd";
	public static final String PASS_PHRASE = "sfcpnnjFG6dULJfo1BEGqczpfN0SmwZ6bgKO5FcDRfI=";
	// IV stands for Initialization Vector
	public static final byte[] IV = new byte[GCM_IV_LENGTH];
	
	public static final String AES = "AES";
	public static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";

}

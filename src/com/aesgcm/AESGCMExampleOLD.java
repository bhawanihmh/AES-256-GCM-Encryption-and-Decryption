/**
 * 
 */
package com.aesgcm;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * @author aspire
 *
 *         Advanced Encryption Standard (AES) algorithm in Galois Counter Mode
 *         (GCM), known as AES-GCM.
 */
public class AESGCMExampleOLD {
	static String plainText = "This is a plain text which need to be encrypted by Java AES 256 GCM Encryption Algorithm";
	public static final int AES_KEY_SIZE = 256;
	public static final int GCM_IV_LENGTH = 12;
	public static final int GCM_TAG_LENGTH = 16;
	public static final int ITERATION_COUNT = 1;
	public static final String SALT = "abcd";
	public static final String PASS_PHRASE = "sfcpnnjFG6dULJfo1BEGqczpfN0SmwZ6bgKO5FcDRfI=";
	static Cipher cipher = null;
	// IV stands for Initialization Vector
	static byte[] IV = new byte[GCM_IV_LENGTH];
	
	AESGCMExampleOLD() {
		
	}
	public static void main(String[] args) throws Exception {
		cipher = Cipher.getInstance("AES/GCM/NoPadding");
		byte[] cipherText = encrypt1();
		//System.out.println("Encrypted Text : " + Base64.getEncoder().encodeToString(cipherText));
		String val = DatatypeConverter.printBase64Binary(cipherText);
		System.out.println("Encrypted Text : " + val);
		String decryptedText = decrypt1(DatatypeConverter.parseBase64Binary(val));
		System.out.println("DeCrypted Text : " + decryptedText);
	}
	
	public static byte[] encrypt1() throws Exception {		
		// SecureRandom class provides a cryptographically strong random number generator
		SecureRandom random = new SecureRandom();
		random.nextBytes(IV);		
		SecretKey secretKey = generateKey();	
		System.out.println("Original Text : " + plainText);
		// Create GCMParameterSpec
		// When using other block cipher modes such as CBC mode,
		// we require only the Initialization Vector (IV), whereas in the case of GCM
		// mode
		// we required Initialization Vector (IV) and Authentication Tag and
		// hence we need to use GCMParameterSpec instead of IvParameterSpec
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
		// Initialize Cipher Mode for ENCRYPT_MODE/DECRYPT_MODE
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);
		// Perform Encryption
		// In order to encrypt we will be calling the doFinal() method on top of the
		// Cipher instance passing the plainText as the parameter
		byte[] cipherText = cipher.doFinal(plainText.getBytes());		
		//String decryptedText = decrypt1(cipherText);		
		return cipherText;
	}
	public static String decrypt1(byte[] cipherText) throws Exception {		
		// SecureRandom class provides a cryptographically strong random number generator
		//SecureRandom random = new SecureRandom();
		//random.nextBytes(IV);		
		SecretKey secretKey = generateKey();		
		// Create GCMParameterSpec
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
		// Initialize Cipher for DECRYPT_MODE
		cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
		// Perform Decryption
		byte[] decryptedText = cipher.doFinal(cipherText);
		return new String(decryptedText);
	}	
	public static void main1(String[] args) throws Exception {
		cipher = Cipher.getInstance("AES/GCM/NoPadding");
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(AES_KEY_SIZE);

		// Generate Key(SecretKey)
		SecretKey key = keyGenerator.generateKey();
		
		// SecureRandom class provides a cryptographically strong random number generator
		SecureRandom random = new SecureRandom();
		random.nextBytes(IV);

		System.out.println("Original Text : " + plainText);

		// The encryption and decryption are handled by the Cipher class.
		// Cipher class instance is created by calling the getInstance() method
		// passing the Cipher name as the parameter, in our case, it is
		// AES/GCM/NoPadding
		byte[] cipherText = encrypt(plainText.getBytes(), key, IV);
		// We will be encoding the ciperText with Base64 to ensure it to be
		// intact without modification when it is transferred.
		System.out.println("Encrypted Text : " + Base64.getEncoder().encodeToString(cipherText));

		// To decrypt we need to pass the cipherText to the
		// doFinal() method of the Cipher instance
		String decryptedText = decrypt(cipherText, key, IV);
		System.out.println("DeCrypted Text : " + decryptedText);
	}
	public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
		// Get Cipher Instance
		// Cipher name is made up of 3 parts :
		// 1. The first part is the name of the algorithm – AES
		// 2. The second part is the mode in which the algorithm should be used – GCM
		// 3. The third part is the padding scheme which is going to be used –
		// NoPadding.
		// Since GCM Mode transforms block encryption into stream encryption
		// In cryptography, padding is any of a number of distinct practices which all
		// include adding data to the beginning, middle, or end of a message prior to
		// encryption

		// NoPadding : Internally GCM really is CTR mode along with a polynomial hashing
		// function applied on the ciphertext. CTR-mode doesn't need padding because you
		// can just partly use the bits the last counter block generated and the
		// polynomial hash does use (zero-)padding.

		//Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

		System.out.println("AESGCMExample.encrypt() = " + key.getEncoded().toString());

		// Create SecretKeySpec
		// The SecretKeySpec provides the mechanism of converting byte data into a
		// secret key
		// suitable to be passed to init() method of the Cipher class.
		//SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

		// String passPhrase = "abc";
		SecretKey keySpec = generateKey();

		// Create GCMParameterSpec
		// When using other block cipher modes such as CBC mode,
		// we require only the Initialization Vector (IV), whereas in the case of GCM
		// mode
		// we required Initialization Vector (IV) and Authentication Tag and
		// hence we need to use GCMParameterSpec instead of IvParameterSpec
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);

		// Initialize Cipher Mode for ENCRYPT_MODE/DECRYPT_MODE
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

		// Perform Encryption
		// In order to encrypt we will be calling the doFinal() method on top of the
		// Cipher instance passing the plainText as the parameter
		byte[] cipherText = cipher.doFinal(plaintext);

		return cipherText;
	}

	private static SecretKey generateKey() throws Exception {
		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec pbeKeySpec = new PBEKeySpec(PASS_PHRASE.toCharArray(), getSalt(), ITERATION_COUNT, AES_KEY_SIZE);
		SecretKey secretKey = new SecretKeySpec(secretKeyFactory.generateSecret(pbeKeySpec).getEncoded(), "AES");
		return secretKey;
	}

	public static byte[] getSalt() throws Exception {
		return DatatypeConverter.parseHexBinary(SALT);
		/*
		 * SecureRandom sr = SecureRandom.getInstance("SHA1PRNG"); byte[] salt = new
		 * byte[64]; sr.nextBytes(salt); return salt;
		 */
	}

	public static String decrypt(byte[] cipherText, SecretKey key, byte[] IV) throws Exception {
		// Get Cipher Instance
		//Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

		// Create SecretKeySpec
		// SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
		SecretKey keySpec = generateKey();
		// Create GCMParameterSpec
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);

		// Initialize Cipher for DECRYPT_MODE
		cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

		// Perform Decryption
		byte[] decryptedText = cipher.doFinal(cipherText);

		return new String(decryptedText);
	}
}
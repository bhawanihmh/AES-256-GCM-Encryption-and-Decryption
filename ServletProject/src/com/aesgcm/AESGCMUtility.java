/**
 * 
 */
package com.aesgcm;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * @author Bhawani Singh Shekhawat
 *
 */
public class AESGCMUtility {
	static Cipher cipher = null;
	// IV stands for Initialization Vector
	public static final byte[] IV = new byte[AESGCMConstants.GCM_IV_LENGTH];
	
	static {
		try {
			cipher = Cipher.getInstance(AESGCMConstants.AES_GCM_NO_PADDING);
			SecureRandom random = new SecureRandom();
			random.nextBytes(IV);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}
	
	public static String getEncryptedText(String plainText) {
		byte[] cipherText = null;
		try {
			cipherText = encrypt(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return DatatypeConverter.printBase64Binary(cipherText); 
	}
	
	/**
	 * Encrypt text
	 * 
	 * @param plaintext
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(String plainText) throws Exception {
		SecretKey keySpec = AESGCMUtility.generateKey();
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(AESGCMConstants.GCM_TAG_LENGTH * 8, IV);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
		return cipher.doFinal(plainText.getBytes());
	}

	/**
	 * Decript cipher text
	 * 
	 * @param cipherText
	 * @param IV
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encryptedText) throws Exception {
		byte[] cipherText = DatatypeConverter.parseBase64Binary(encryptedText);//encryptedText.getBytes();
		SecretKey keySpec = AESGCMUtility.generateKey();
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(AESGCMConstants.GCM_TAG_LENGTH * 8, IV);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
		byte[] decryptedText = cipher.doFinal(cipherText);		
		return new String(decryptedText);
	}	
	
	/**
	 * Generate secret key
	 * 
	 * @return
	 * @throws Exception
	 */
	public static SecretKey generateKey() throws Exception {
		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec pbeKeySpec = new PBEKeySpec(AESGCMConstants.PASS_PHRASE.toCharArray(), getSalt(),
				AESGCMConstants.ITERATION_COUNT, AESGCMConstants.AES_KEY_SIZE);
		return new SecretKeySpec(secretKeyFactory.generateSecret(pbeKeySpec).getEncoded(), AESGCMConstants.AES);
	}
	
	/**
	 * Get Salt
	 * 
	 * @return
	 * @throws Exception
	 */
	public static byte[] getSalt() throws Exception {
		return DatatypeConverter.parseHexBinary(AESGCMConstants.SALT);
	}
	
	public static void main(String[] args) throws Exception {
		/*
		 * SecureRandom random = new SecureRandom();
		 * random.nextBytes(AESGCMConstants.IV);
		 * System.out.println("AESGCMEncrypt.main() : " + new
		 * String(AESGCMConstants.IV)); String encodedString =
		 * Base64.getEncoder().encodeToString(AESGCMConstants.IV);
		 * System.out.println("AESGCMEncrypt.main() : " + encodedString); byte[]
		 * decodedBytes = Base64.getDecoder().decode(encodedString);
		 * System.out.println("AESGCMEncrypt.main() : " + new String(decodedBytes));
		 */
		String plainText = "aaa";
		String encryptedText = getEncryptedText(plainText);
		System.out.println("Encrypted Text : " + encryptedText);
		String decryptedText = null;
		try {
			decryptedText = decrypt(encryptedText);
		} catch (Exception e) {
			System.out.println("AESGCMDecrypt.doGet() getMessage = " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("DeCrypted Text : " + decryptedText);
		
		//val = aESGCMDecrypt.decrypt(val);
		//System.out.println("Decrypted Text : " + val);
		// response.getWriter().println("Encrypted Text : "+ val);
	}
	
}

/**
 * 
 */
package com.aesgcm;

import javax.xml.bind.DatatypeConverter;

/**
 * @author Bhawani Singh Shekhawat 
 *
 */
public class AESGCMTest {
	public static final int GCM_IV_LENGTH = 12;
	// IV stands for Initialization Vector
	static byte[] IV = new byte[GCM_IV_LENGTH];

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AESGCMExample aesGCMExample = new AESGCMExample(256,1);
		String plainText = "Encrypted by Java AES 256 GCM Encryption Algorithm";
		System.out.println("Original Text : " + plainText);

		// The encryption and decryption are handled by the Cipher class.
		// Cipher class instance is created by calling the getInstance() method
		// passing the Cipher name as the parameter, in our case, it is
		// AES/GCM/NoPadding
		byte[] cipherText = null;
		try {
			cipherText = aesGCMExample.encrypt(plainText.getBytes(), IV);
			
			// We will be encoding the ciperText with Base64 to ensure it to be
			// intact without modification when it is transferred.
			String val = DatatypeConverter.printBase64Binary(cipherText);
			System.out.println("Encrypted Text : " + val);
			
			// To decrypt we need to pass the cipherText to the
			// doFinal() method of the Cipher instance
			String decryptedText = aesGCMExample.decrypt(cipherText, IV);
			System.out.println("DeCrypted Text : " + decryptedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

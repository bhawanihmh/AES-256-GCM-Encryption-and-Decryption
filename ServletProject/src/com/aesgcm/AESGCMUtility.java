/**
 * 
 */
package com.aesgcm;

import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * @author TP21291930
 *
 */
public class AESGCMUtility {

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
	
}

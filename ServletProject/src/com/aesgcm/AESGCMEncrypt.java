/**
 * 
 */
package com.aesgcm;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 * @author Bhawani Singh Shekhawat
 *
 * Advanced Encryption Standard (AES) algorithm in Galois Counter Mode
 * (GCM), known as AES-GCM.
 * 
 */
@WebServlet(name="AESGCMEncrypt", urlPatterns= {"/encrypt"})
public class AESGCMEncrypt extends HttpServlet {	
	static Cipher cipher = null;

	public static void main(String[] args) {		
		SecureRandom random = new SecureRandom();
		random.nextBytes(AESGCMConstants.IV);
		System.out.println("AESGCMEncrypt.main() : " + new String(AESGCMConstants.IV));
		String encodedString = Base64.getEncoder().encodeToString(AESGCMConstants.IV);
		System.out.println("AESGCMEncrypt.main() : " + encodedString);
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		System.out.println("AESGCMEncrypt.main() : " + new String(decodedBytes));
		try {
			cipher = Cipher.getInstance(AESGCMConstants.AES_GCM_NO_PADDING);
			SecureRandom random = new SecureRandom();
			random.nextBytes(AESGCMConstants.IV);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init() throws ServletException {
		try {
			cipher = Cipher.getInstance(AESGCMConstants.AES_GCM_NO_PADDING);
			SecureRandom random = new SecureRandom();
			random.nextBytes(AESGCMConstants.IV);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String plainText = request.getParameter("plainText");
		byte[] cipherText = null;
		try {
			cipherText = encrypt(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}			
		String val = DatatypeConverter.printBase64Binary(cipherText);
		System.out.println("Encrypted Text : " + val);
		response.getWriter().println("Encrypted Text  : "+ val);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * Encrypt text
	 * 
	 * @param plaintext
	 * @return
	 * @throws Exception
	 */
	private byte[] encrypt(String plainText) throws Exception {
		SecretKey keySpec = AESGCMUtility.generateKey();
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(AESGCMConstants.GCM_TAG_LENGTH * 8, AESGCMConstants.IV);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
		return cipher.doFinal(plainText.getBytes());
	}
}

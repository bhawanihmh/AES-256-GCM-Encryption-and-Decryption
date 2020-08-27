package com.aesgcm;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bhawani Singh Shekhawat
 *
 * Advanced Encryption Standard (AES) algorithm in Galois Counter Mode (GCM), known as AES-GCM.
 * 
 */
@WebServlet(name="AESGCMDecrypt", urlPatterns= {"/decrypt"})
public class AESGCMDecrypt extends HttpServlet {
	static Cipher cipher = null;
	
	@Override
	public void init() throws ServletException{
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String encryptedText = request.getParameter("encryptedText");
		//String iv = request.getParameter("iv");
		String decryptedText = null;
		try {
			decryptedText = decrypt(encryptedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DeCrypted Text : " + decryptedText);
		response.getWriter().println("DeCrypted Text : " + decryptedText);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/**
	 * Decript cipher text
	 * 
	 * @param cipherText
	 * @param IV
	 * @return
	 * @throws Exception
	 */
	private String decrypt(String encryptedText) throws Exception {
		byte[] cipherText = encryptedText.getBytes();
		SecretKey keySpec = AESGCMUtility.generateKey();
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(AESGCMConstants.GCM_TAG_LENGTH * 8, AESGCMConstants.IV);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
		byte[] decryptedText = cipher.doFinal(cipherText);		
		return new String(decryptedText);
	}
}

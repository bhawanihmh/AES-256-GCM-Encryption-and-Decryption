package com.aesgcm;

import java.io.IOException;

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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String encryptedText = request.getParameter("encryptedText");
		System.out.println("encryptedText : " + encryptedText);
		//String iv = request.getParameter("iv");
		String decryptedText = null;
		try {
			decryptedText = AESGCMUtility.decrypt(encryptedText);
		} catch (Exception e) {
			System.out.println("AESGCMDecrypt.doGet() getMessage = " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("DeCrypted Text : " + decryptedText);
		response.getWriter().println("DeCrypted Text : " + decryptedText);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	

}

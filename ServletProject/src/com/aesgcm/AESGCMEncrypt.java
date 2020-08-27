/**
 * 
 */
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
 * Advanced Encryption Standard (AES) algorithm in Galois Counter Mode
 * (GCM), known as AES-GCM.
 * 
 */
@WebServlet(name="AESGCMEncrypt", urlPatterns= {"/encrypt"})
public class AESGCMEncrypt extends HttpServlet {	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String plainText = request.getParameter("plainText");
		String val = AESGCMUtility.getEncryptedText(plainText);
		System.out.println("Encrypted Text : " + val);
		response.getWriter().println("Encrypted Text  : "+ val);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}


}

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
 * Advanced Encryption Standard (AES) algorithm in Galois Counter Mode (GCM), known as AES-GCM.
 * 
 */
@WebServlet("/aesgcmiv")
public class AESGCMIV extends HttpServlet {
	
	public static final int GCM_IV_LENGTH = 12;
	static byte[] IV = new byte[GCM_IV_LENGTH];
	
	@Override
	public void init() throws ServletException{}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("IV : " + IV);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("IV : " + IV);
	}
}

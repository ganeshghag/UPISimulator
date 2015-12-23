package com.mastek.protos.payments.mobile.upi.simulator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UPIAccountList
 */
@WebServlet("/UPIAccountList")
public class UPIAccountList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UPIAccountList() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello from the UPIAccountList POST method");
		InputStream in = request.getInputStream();
		String inputString = new Scanner(in).useDelimiter("\\A").next();
		System.out.println("input to post"+inputString);
		
		String mobileNumber = extractMobileNumber(inputString);
		
		String filename = "WEB-INF/messages/responses/RespListAccount-"+mobileNumber+".xml";
		System.out.println("filename requested="+filename);
		String outputString = new Scanner(getServletContext().getResourceAsStream(filename),"UTF-8").useDelimiter("\\A").next();
		System.out.println("output from post"+outputString);
		response.getWriter().write(outputString);
	}

    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("Hello from the UPISimulator GET method-NOOP. Please use POST method");
	}
	
	
	private static String extractMobileNumber(String input){
		String mobile="";
		String token = "<Link type=\"MOBILE\" value=";
		try{
		String temp = input.split(token)[1];
		System.out.println("temp="+temp);
		mobile = temp.split("\"")[1];
		}
		catch(Exception e){
			System.out.println("mobile could not be extracted, using blank and mobile and default response file");
		}
		
		System.out.println("mobile="+mobile);
		
		return mobile;
	}
	

	
//	public static void main(String[] args) {
//		extractMobileNumber("<Link type=\"MOBILE\" value=\"9999933333\">");
//	}

}

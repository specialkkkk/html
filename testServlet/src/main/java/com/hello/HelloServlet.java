package com.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/HelloServlet") //서블릿   = xml파일의 url-pattern = "/HelloServlet" 넣어줌
//xml파일에 <servlet>과 중복이면 안된다  둘중 하나 선택


public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public void init() { //콜백
    	System.out.println("init()");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//콜백
		System.out.println("doGet()"); 
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();//생성
		out.println("<html>");
		out.println("<body>");
		out.println("HELOOOO Servlet<br>");
		out.println("하이 서블릿!<br>"); //ln은 페이지소스에서 엔터고  //<br>은 보이는화면에서 엔터
		out.println("</body>");
		out.println("</html>");
	}
	
	public void destroy() {//콜백
		System.out.println("destroy"); 
	}
}

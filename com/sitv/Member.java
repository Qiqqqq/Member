package com.sitv;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitv.DbUtil.ConnectionPool;
import com.sitv.directive.InfoDirective;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class Member
 */
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override

    public void init() throws ServletException {

    this.getServletContext().setAttribute("ConnectionPool", new ConnectionPool());
    System.out.print("init Member \n");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String classname = request.getParameter("func");
		ConnectionPool connPool = (ConnectionPool)this.getServletContext().getAttribute("ConnectionPool");
		JSONArray result=new JSONArray();
		try {
			result = (JSONArray)invokeMethod(request, classname);
			System.out.print(result + "\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doResponse(response, result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public Object invokeMethod(HttpServletRequest request, String classname) throws Exception {
		Class<?> classObj = Class.forName("com.sitv.directive."+classname+"Directive");
		//String tmp = "set start data";
		//Object[] args = {request,tmp};
		//Class[] argsClass = new Class[args.length];
	    //argsClass[0] = HttpServletRequest.class; 
	    //argsClass[1] = String.class; 
		Method method = classObj.getMethod("excute",HttpServletRequest.class);
		return method.invoke(classObj.newInstance(),request);
	}

	public void doResponse(HttpServletResponse response, JSONArray result) {
		  response.setContentType("text;charset=UTF-8");
		  PrintWriter out;
		try {
			out = response.getWriter();
		      out.print(result);
		      out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

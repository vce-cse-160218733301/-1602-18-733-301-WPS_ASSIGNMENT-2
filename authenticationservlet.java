package payroll;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class authenticationserverlet
 */
@WebServlet(
		description = "performs authentication based on username and password", 
		urlPatterns = { "/auth" }, 
		initParams = { 
				@WebInitParam(name = "x", value = "as")
		})
public class authenticationservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public authenticationservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//read content
		String ui=request.getParameter("uid");
		// TODO Auto-generated method stub
		
		   
		//DB CODE
	         try {
	        	 PrintWriter out=response.getWriter();
	        	 response.setContentType("text/html");
		mysqlbean bean=new mysqlbean();
		Connection con=bean.authenticate(ui);
		if(con != null)
		{
		
		out.print("<br>");
		out.println("<center>Sadana Infotech</center>");
		out.println("<center>#335, 19th Main, Rajaji Nager,1st Block, Bangalore-560010</center>");
		out.println("<center>Wage Slip for the month of Feb/2019</center></b>");
	
		java.sql.PreparedStatement stmt = con.prepareStatement("select * from emp where uid=?");
		stmt.setString(1,ui);
	      ResultSet rs=stmt.executeQuery();
	     out.println("<br><center><table>");
	     double bs = 0;
	     double conv = 0;
	     double pf=2880;
	     double pt=800;
	     double tds=0;
	     double earnings=0;
	     double da,hra,spa;
	     double deduction=0;
	     double netpay = 0;
	     String words;
	    while(rs.next())
	    {
	    	int eid = rs.getInt(1);
	    	String name= rs.getString(2);
	    	String pfno=rs.getString(3);
	    	String desig=rs.getString(4);
	    	String dept=rs.getString(5);
	    	int accno = rs.getInt(6);
	    	String pan=rs.getString(7);
	    	String pay = rs.getString(8);
	    	String uid = rs.getString(9);
	    	bs=rs.getInt(10);
	    	conv=rs.getInt(11);
	    	
	    	out.println("<tr><td>Emp ID</td><td><span style=\"padding-left:50px\">"+ eid+"<span style=\"padding-left:100px\"></td><td></td><td>Employee Name:</td><td><span style=\"padding-left:50px\">"+name+"</td></tr>");
	    	out.println("<tr><td>PF. NO</td><td><span style=\"padding-left:50px\">"+ pfno+"<span style=\"padding-left:100px\"></td><td></td><td>Designation</td><td><span style=\"padding-left:50px\">"+desig+"</td></tr>");
	    	out.println("<tr><td>Department</td><td><span style=\"padding-left:50px\">"+ dept+"<span style=\"padding-left:100px\"></td><td></td><td>A/c No:</td><td><span style=\"padding-left:50px\">"+accno+"</td></tr>");
	    	out.println("<tr><td>PAN</td><td><span style=\"padding-left:50px\">"+ pan+"<span style=\"padding-left:100px\"></td><td></td><td>Mode of Pay</td><td><span style=\"padding-left:50px\">"+pay+"</td></tr>");
	    	out.println("</table></center>");
	    }
	    da=bs*0.02;
	    hra=bs*0.3;
	    spa=bs*0.045;
	    earnings=bs+da+hra+spa;
	    tds=earnings*0.1;
	    deduction=pf+pt+tds;
	    netpay=earnings-deduction;
	   
	    out.println("<center><table  border=1 style=\"border-collapse: collapse\">");
	    
	    out.println("<tr><th border=0><span style=\"padding-left:50px\">Earnings</th><th ><span style=\"padding-left:50px\">Rate</th><th><span style=\"padding-left:50px\">Amount</th><th><span style=\"padding-left:50px\">Deeductions</th><th><span style=\"padding-left:50px\">Amount</th></tr>"
	    		+ "<tr><td height=250 style=\"vertical-align:top\">BASIC <BR>DA<BR>HRA<BR>CONV<BR>SPL ALLOW</td>"
	    		+ "<td style=\"vertical-align:top;text-align:right\">"+bs+"<br>"+bs*0.02+"<br>"+bs*0.3+"<br>"+conv+"<br>"+bs*0.045+"</td>"
	    				+ "<td style=\"vertical-align:top;text-align:right\">"+bs+"<br>"+bs*0.02+"<br>"+bs*0.3+"<br>"+conv+"<br>"+bs*0.045+"</td>"
	    						+ "<td height=250 style=\"vertical-align:top\">PF<BR>PT<BR>TDS</td>"
	    						+ "<td style=\"vertical-align:top;text-align:right\">"+pf+"<br>"+pt+"<br>"+tds+"</td></tr>"
	    								+ "<tr><th>Total</th><th style=\"text-align:right\">"+earnings+"</th><th style=\"text-align:right\">"+earnings+"</th><th>Total</th><th style=\"text-align:right\">"+deduction+"</th></tr>"
	    										+ "<tr><th colspan=5 style=\text-align:left\">Net Pay <span style=\"padding-left:90px\">" +netpay+"<br>"+"</th></tr></table></center>");
	    
	      
	    
	     
         
          con.close();  
		}
		else
		out.print("<b style='color:red'>Invalid credentials:"+ui);
	         }
	         catch(Exception e) {
	   		  e.printStackTrace();
	   		  
	   	  }
	}

	private String tostring(double netpay) {
		// TODO Auto-generated method stub
		return null;
	}
	
}


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class loginformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        Connection   conn = null;
	        RequestDispatcher dispatcher = null;

	        try {
	        	
	        	Class.forName("com.mysql.cj.jdbc.Driver"); // driver name --> java class

	           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/log","root","Macro25**");
	            PreparedStatement stmt = conn.prepareStatement("INSERT INTO login(username,password) VALUES(?,?)");
	            stmt.setString(1, username);
	            stmt.setString(2, password);
	            int  rs = stmt.executeUpdate();
	            dispatcher = request.getRequestDispatcher("booking.jsp");// forwarding the next pages
	            

	            if (rs > 0)
	            {
	            	request.setAttribute("status","success");
	            }
	            else
	            {
	            	request.setAttribute("status", "failed");
	            }
	              

	         
	          dispatcher.forward(request, response);
	        } 
	        catch ( SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        finally {
	        	try {
					conn.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
	        }
	    }
	
	}





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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class reserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// GET FORM INPUTS
			
			
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String adults = request.getParameter("adults");
			String children =request.getParameter("children");
			String travel_class = request.getParameter("travel_class");
			String destination = request.getParameter("destination");
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			String dept_date = request.getParameter("dept_date");
			@SuppressWarnings("unused")
			SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
			String dept_time = request.getParameter("dept_time");
		
		
			try {
			Date date = formatter.parse(dept_date);
			System.out.println(date);
			System.out.println(formatter.format(date));
			}catch (ParseException e) {
			e.printStackTrace();
			}
			
			
			Connection conn = null;
			PreparedStatement stmt = null;
			RequestDispatcher dispatcher = null;
			
			//INSERT RESERVATION INTO DATABASE
			
			try
			{
				// CONNECT TO MYSQL DATABASE
				Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation_system","root","Macro25**");
				stmt = conn.prepareStatement("INSERT INTO reservations(name,phone,adults,children,travel_class,destination,departure_date,departure_time) VALUES(?,?,?,?,?,?,?,?)");
		        stmt.setString(1, name);
		        stmt.setString(2, phone);
		       stmt.setString(3, adults);
		       stmt.setString(4, children);
		       stmt.setString(5, travel_class);
		       stmt.setString(6, destination);
		       stmt.setString(7, dept_date);
		       stmt.setString(8, dept_time);
		    
		    
		        
				
				int  rs = stmt.executeUpdate();
				  dispatcher = request.getRequestDispatcher("confirmation.jsp");
	        

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
			
			finally
			{
	        	try
	        	{
					conn.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
	        }
	    }
	
	}

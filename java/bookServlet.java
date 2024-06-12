

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/searchServlet")
public class bookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// set response content type
		response.setContentType("text/html");

		// New location to be redirected, it is an example
		String site = null;
		 if ("newyork".equals(request.getParameter("place"))) 
		   {
	            site = "newyork.html";
	        }
		 else if ("chennai".equals(request.getParameter("place"))) 
		  {
	            site = "chennai.html";
	       }
		 
		
		// We have different response status types.
		// It is an optional also. Here it is a valid site
		// and hence it comes with response.SC_ACCEPTED
		response.setStatus(response.SC_ACCEPTED);
		response.setHeader("Location", site);
		response.sendRedirect(site);
		return;
	}

}

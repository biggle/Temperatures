package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReserveServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


		int minTemp = Integer.parseInt(request.getParameter("min_temp"));
		int maxTemp= Integer.parseInt(request.getParameter("max_temp"));
		String created = request.getParameter("created");
		String condition= request.getParameter("tcondition");

		String table="Temperatures";
		/*
		 * Here we initialize tools for making the database connection
		 * and reading from the database
		 */

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();


		out.println("<html><head><title>Create Tables Servlet</title></head><body>");

		Connection conn=null;
		
		String url="jdbc:mysql://mysql.cc.puv.fi:3306/";
		String dBase = "your database name";
		String userName = "your username";
		String password = "your password";
		
		Statement stmt=null;

		try {
			//Here we load the database driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");

			//For your_db mySQL database the above code would look like the following:
			conn=DriverManager.getConnection(url+dBase, userName, password);

			//Here we create the statement object for executing SQL commands
			stmt=conn.createStatement();

			stmt.executeUpdate("insert into " + table +  " (min_temp, max_temp, created, tcondition) " + " values (" + minTemp + ", " + maxTemp + ", '" + created + "', '" + condition + "')"); 

			out.println("<p>Data was added to " + table + " successfully!");
			out.println("</body>");
			out.println("</html>");


		} catch(Exception e){

			out.println(e.getMessage());
		} finally {


			try {

				if(stmt !=null)
					stmt.close();

				if(conn !=null)
					conn.close();


			} catch (SQLException e) {
				out.print("<p>" + e.getMessage());
			}

		}


	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)

	throws ServletException, java.io.IOException {


		doPost(request, response);

	}

}



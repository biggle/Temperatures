//This is SearchServlet.java file
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		int minTemp = Integer.parseInt(request.getParameter("smin_temp"));

		// String query="select * from Students";

		String tableName ="Temperatures";
		String query="select * from " + tableName + " where min_temp " + "LIKE '"  + minTemp + "%'";

		/*
		 * Here we initialize tools for making the database connection
		 * and reading from the database
		 */
		Connection conn=null;
		
		String url="jdbc:mysql://mysql.cc.puv.fi:3306/";
		String dBase = "your database name";
		String userName = "your username";
		String password = "your password";		
		
		Statement stmt=null;

		ResultSet resultSet=null;
		ResultSetMetaData resultSetMeatData=null;

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();


		out.println("<html><head><title>Database Access Servlet</title></head><body>");

		out.println("<h2>" + tableName + " Information");
		out.println("<table border='1'><tr>");

		try {
			//Here we load the database driver for Oracle database
			//Class.forName("oracle.jdbc.driver.OracleDriver");

			//For mySQL database the above code would look like this:
			Class.forName("com.mysql.jdbc.Driver");

			//For your_db mySQL database the above code would look like the following:
			conn=DriverManager.getConnection(url+dBase, userName, password);


			//Herey we create the statement object for executing SQL commands.
			stmt=conn.createStatement();

			//Here we executethe SQL query and save the results to a ResultSet object
			resultSet=stmt.executeQuery(query);

			//Here we get the metadata of the query results
			resultSetMeatData=resultSet.getMetaData();

			//Here we calculate the number of columns
			int columns=resultSetMeatData.getColumnCount();

			//Here we print column names in table header cells
			//Pay attention that the column index starts with 1
			for(int i=1; i<=columns; i++) {
				out.println("<th> "+resultSetMeatData.getColumnName(i)+ "</th>");
			}

			out.println("</tr>");
			out.println("<tr>");
			for(int i=1; i<=columns; i++) {
				out.println("<th> "+resultSetMeatData.getColumnTypeName(i)+ "</th>");
			}
			out.println("</tr>");


			while(resultSet.next()){

				out.println("<tr>");

				//Here we print the value of each column
				for(int i=1; i<=columns; i++) {

					if (resultSet.getObject(i)!=null) 
						out.println("<td>" + resultSet.getObject(i).toString()+"</td>");
					else
						out.println("<td>---</td>");

				}

				out.println("</tr>");
			}

			out.println("</table>");

			out.println("</body>");
			out.println("</html>");


		}catch(Exception e){
			out.println("<p>"+ e.getMessage()); 
		}finally {
			try {
				//Here we close all open streams
				if(stmt !=null)
					stmt.close();

				if(conn!=null)
					conn.close();

			}catch(SQLException sqlexc){
				out.println("EXception occurred while closing streams!");
			}

		}

	} 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)

	throws ServletException, java.io.IOException {


		doPost(request, response);

	}	
}
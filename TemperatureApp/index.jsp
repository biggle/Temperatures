<html>
	<head>
		<title>Temperature DB</title>
	</head>
	<body>
		<h2>Temperature Data add & Search</h2>
		<table>
			<form method="POST" action="index.jsp">
				<tr>
					<td <% if(request.getParameter("min_temp")!=null && request.getParameter("min_temp").equals("")){ %>
						bgcolor="red" <% } %> >	Minimum Temperature:</td>
					<td><input type="text" name="min_temp" value=""></td>
				</tr>
				<tr>
					<td <% if(request.getParameter("max_temp")!=null && request.getParameter("max_temp").equals("")){ %>
						bgcolor="red" <% } %> >	Maximum Temperature:</td>
					<td><input type="text" name="max_temp" value=""></td>
				</tr>				
				<tr>
					<td <% if(request.getParameter("created")!=null && request.getParameter("created").equals("")){ %>
						bgcolor="red" <% } %> > Date:</td>
					<td><input type="text" name="created" value=""></td>
				</tr>
				<tr>
					<td <% if(request.getParameter("tcondition")!=null && request.getParameter("tcondition").equals("")){ %>
						bgcolor="red" <% } %> >Condition:</td>
					<td> 		
						<select name="tcondition">
							<option value="">-Condition-</option>		
						  	<option value="Sunny">Sunny</option>		
						  	<option value="Rainning">Rainning</option>					
							<option value="Foggy">Foggy</option>				
							<option value="Snowing">Snowing</option>					
							<option value="Cloudy">Cloudy</option>					
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><INPUT type="submit" value="Add"><INPUT type="reset"></td>
				</tr>
				
			</form>
			<form method="POST" action="index.jsp">
				<tr>
					<td>Search By Minimum temperature:</td>
					<td><INPUT type="text" name="smin_temp" value=""></td>
				</tr>
				<tr>
					<td></td>
					<td><INPUT type="submit" value="Search"><INPUT type="reset"></td>
				</tr>
			</form>
		</table>
	</body>
</html>

<% if (request.getParameter("min_temp")!=null && !request.getParameter("min_temp").equals("")
		&& request.getParameter("max_temp")!=null && !request.getParameter("max_temp").equals("")
		&& request.getParameter("created")!=null && !request.getParameter("created").equals("")
		&& request.getParameter("tcondition")!=null && !request.getParameter("tcondition").equals("")) { %>


<jsp:include page="reserveEvent" flush="true" />
<% } %>

<% if (request.getParameter("smin_temp")!=null && !request.getParameter("smin_temp").equals("")) { %>


<jsp:include page="searchEvents" flush="true" />
<% } %>
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/Revoke")
public class Revoke extends HttpServlet
{
       protected void service(HttpServletRequest req,HttpServletResponse resp) throws IOException
       {
    	   try {
			Class.forName("org.sqlite.JDBC");
			Connection co=DriverManager.getConnection("jdbc:sqlite:C://sqlite-tools-win32-x86-3350500//zohos.db");
			Statement st=co.createStatement();
			ResultSet rs=st.executeQuery("select * from revoke");
			resp.getWriter().print("<html>\r\n"
					+ "<head>\r\n"
					+ "<style>\r\n"
					+ "table {\r\n"
					+ "  width:100%;\r\n"
					+ "}\r\n"
					+ "table, th, td {\r\n"
					+ "  border: 1px solid black;\r\n"
					+ "  border-collapse: collapse;\r\n"
					+ "}\r\n"
					+ "th, td {\r\n"
					+ "  padding: 15px;\r\n"
					+ "  text-align: left;\r\n"
					+ "}\r\n"
					+ "#t01 tr:nth-child(even) {\r\n"
					+ "  background-color: #eee;\r\n"
					+ "}\r\n"
					+ "#t01 tr:nth-child(odd) {\r\n"
					+ " background-color: #fff;\r\n"
					+ "}\r\n"
					+ "#t01 th {\r\n"
					+ "  background-color: black;\r\n"
					+ "  color: white;\r\n"
					+ "}\r\n"
					+ "</style>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "\r\n"
					+ "<h2><center>Revoke the Users</center></h2>\r\n"
					+ "\r\n"
					+ "<table id=\"t01\">\r\n"
					+ "  <tr>\r\n"
					+"<th><center>Id</center></th> \r\n"
					+ "    <th><center>Name</center></th> \r\n"
					+"<th><center>Tokens</center></th>\r\n"
					+ "  </tr>");
			while(rs.next())
			{
				resp.getWriter().print("<tr>");
				resp.getWriter().println("<td><center>"+rs.getString("id")+"</td></center>");
				resp.getWriter().println("<td><center>"+rs.getString("name")+"</td></center>");
				String tokenss=rs.getString("token");
				resp.getWriter().printf("<td><center>"+rs.getString("token")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<a href=\"revokes?id=%s&val=%s\"><input type=\"button\" value=\"REVOKE\"></a></td></center>",rs.getString("id"),tokenss);
				resp.getWriter().println("</tr>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    	   resp.getWriter().print("</table></center></body></html>");
       }
}

import java.io.IOException;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/Admin")
public class Admin extends HttpServlet
{
              protected void service(HttpServletRequest req,HttpServletResponse resp) throws IOException
              {
            	  try {
					Class.forName("org.sqlite.JDBC");
					Connection co=DriverManager.getConnection("jdbc:sqlite:C://sqlite-tools-win32-x86-3350500//zohos.db");
					Statement st=co.createStatement();
					ResultSet rs=st.executeQuery("select * from users");
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
							+ "<h2><center>View All Users</center></h2>\r\n"
							+ "\r\n"
							+ "<table id=\"t01\">\r\n"
							+ "  <tr>\r\n"
							+ "    <th><center>id</center></th>\r\n"
							+ "    <th><center>Name</center></th> \r\n"
							+ "    <th><center>Email</center></th>\r\n"
							+ "  </tr>");
					while(rs.next())
					{
						resp.getWriter().print("<tr>");
						resp.getWriter().println("<td>"+rs.getInt("id")+"</td>");
						resp.getWriter().println("<td>"+rs.getString("name")+"</td>");
						resp.getWriter().println("<td>"+rs.getString("email")+"</td>");
						resp.getWriter().println("</tr>");
					}
				} catch (ClassNotFoundException | SQLException e) {
					System.out.print("Database not opened");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	  resp.getWriter().print("</table></center></body></html>");
              }
}

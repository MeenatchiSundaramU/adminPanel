import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/queryresult")
public class Queryresult extends HttpServlet
{
	
	protected void service(HttpServletRequest req,HttpServletResponse resp) throws IOException
	{
	    String name=req.getParameter("name");
	    String email=req.getParameter("email");
	    String id=req.getParameter("id");
	    String products=req.getParameter("pro");
	    if(name!=null)
	    {
	    	QueryUsers("select * from users where name=?",name,resp);
	    }
	    else if(id!=null)
	    {
	    	QueryUsers("select * from users where id=?",id,resp);
	    }
	    else if(email!=null)
	    {
	    	 email=email.replace("%40", "@");
	    	QueryUsers("select * from users where email=?",email,resp);
	    }
	    else if(products!=null)
	    {
	    	QueryUsers("select * from users where totalproducts=?",products,resp);
	    }
	}
	void QueryUsers(String querypara,String value,HttpServletResponse resp) throws IOException
	{
		try {
			Class.forName("org.sqlite.JDBC");
			Connection co=DriverManager.getConnection("jdbc:sqlite:C://sqlite-tools-win32-x86-3350500//zohos.db");
			PreparedStatement st=co.prepareStatement(querypara);
			if(querypara.equals("select * from users where products=?"))
			{
				st.setInt(1,Integer.parseInt(value));
			}
			else
			{
			st.setString(1, value);
			}
			ResultSet rs=st.executeQuery();
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
					+ "<th><center>Total No of products</center></th>\r\n"
					+ "  </tr>");
			while(rs.next())
			{
				resp.getWriter().print("<tr>");
				resp.getWriter().println("<td>"+rs.getInt("id")+"</td>");
				resp.getWriter().println("<td>"+rs.getString("name")+"</td>");
				resp.getWriter().println("<td>"+rs.getString("email")+"</td>");
		     	resp.getWriter().println("<td>"+rs.getString("totalproducts")+"</td>");
				resp.getWriter().println("</tr>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.print("Database not opened");
			e.printStackTrace();
		}
    	  resp.getWriter().print("</table></center></body></html>");
      }
	}

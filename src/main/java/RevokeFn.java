import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/revokes")
public class RevokeFn extends HttpServlet
{
          protected void service(HttpServletRequest req,HttpServletResponse resp) throws IOException
          {
        	  String access_tokens=req.getParameter("id");
        	  String access_token=new response().callFun("https://accounts.zoho.com/oauth/v2/token/revoke?token="+req.getParameter("val"), 0, "");
        	  resp.getWriter().print(access_token);
        	  int finind1=access_token.toString().indexOf(":")+2;
     	     int finind2=access_token.toString().lastIndexOf('"');
     	     if(access_token.toString().substring(finind1, finind2).equals("success")) {
        	  try {
        	  UpdateDatabase(access_tokens);
        	       resp.sendRedirect("http://localhost:8080/final/Revoke");
				} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     	     }
          }
          void UpdateDatabase(String token) throws ClassNotFoundException, SQLException
          {
        	Class.forName("org.sqlite.JDBC");
  			Connection co=DriverManager.getConnection("jdbc:sqlite:C://sqlite-tools-win32-x86-3350500//zohos.db");
  			Statement st=co.createStatement();
  			st.execute("delete from revoke where id="+token);
          }
}

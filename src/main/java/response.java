import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/resp"})
public class response extends HttpServlet 
{
	@SuppressWarnings("unused")
	protected void service(HttpServletRequest req,HttpServletResponse resp) throws IOException
	{
		 HttpSession session=req.getSession();
	    String username=(String) session.getAttribute("names");
	    String error=req.getParameter("error");
	   if(error!=null)
		{
			resp.sendRedirect("http://localhost:8080/final/index.html");
		}
        else if(error==null)
		{
		String code=req.getParameter("code");
		String grand_token_api="https://accounts.zoho.com/oauth/v2/token?grant_type=authorization_code&client_id=1000.CU8EIFGUKKWO76XF6DJI5PMJ072P2T&client_secret=1a69af9c58c99acb88dd7de3be5698fe9de8cfd01a&redirect_uri=http://localhost:8080/final/resp&code="+req.getParameter("code");
		String grand_token=callFun(grand_token_api,0,"");
		 int ind1=grand_token.indexOf(":");
	     int ind2=grand_token.indexOf(",");
	     String grand_token_value=grand_token.substring(ind1+2,ind2-1);
	     String users=callFun("https://accounts.zoho.com/oauth/user/info",1,grand_token_value);//Fetch the user details
	     try {
			ExtractUserProfile(users,grand_token_value);
			resp.sendRedirect("http://localhost:8080/final/resp.html");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	String callFun(String url,int mode,String grandtok) throws IOException
	{
		URL obj= new URL(url);
	    HttpsURLConnection con=(HttpsURLConnection)obj.openConnection();
	    if(mode==1)
	    {
	    	con.setRequestProperty("Authorization","Bearer "+" "+grandtok);
	        con.setRequestProperty("Content-Type","application/json");
	    }
	    con.setRequestMethod("POST");
	    int respcode=con.getResponseCode();
		 BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     return response.toString();
	}
	void ExtractUserProfile(String userjson,String grand_token_value) throws ClassNotFoundException, SQLException
	{
		String username=userjson.substring((userjson.indexOf("First_name")+16),(userjson.indexOf(",")-1));
		String email=userjson.substring((userjson.indexOf("Email")+8),(userjson.indexOf(",",(userjson.indexOf("Email")+7))-1));
		System.out.print(username+""+email);
		uploadDatabase(grand_token_value,username,email);
		
	}
	void uploadDatabase(String persistent_tokens,String name,String email) throws ClassNotFoundException, SQLException
	{
		 Connection c = null;
                 PreparedStatement stmt1 = null;
                 PreparedStatement stmt2 = null;
		 try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:C://sqlite-tools-win32-x86-3350500//zohos.db");
         c.setAutoCommit(false);
         System.out.println("database Opened");
         stmt2=c.prepareStatement("insert into revoke(name,token)values(?,?)");
	     stmt1 = c.prepareStatement("insert into users(name,email,totalproducts)values(?,?,?)");//PRIMARY KEY---->ID AUTO GENERATED;
	     stmt1.setString(1,name);
	     stmt1.setString(2,email);
	     stmt1.setInt(3,0);
	     stmt2.setString(1,name);
	     stmt2.setString(2,persistent_tokens);
	     stmt2.executeUpdate();
         stmt1.executeUpdate();
	     stmt1.close();
	     stmt2.close();
         c.commit();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("USERS ADDED");
   }
	}


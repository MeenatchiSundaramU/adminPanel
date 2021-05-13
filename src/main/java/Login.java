import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.Cookie;

@WebServlet(urlPatterns = {"/login"})
public class Login extends HttpServlet
{
    protected void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
    {
    	HttpSession session=req.getSession();
   	 session.setAttribute("names",req.getParameter("name"));
        res.sendRedirect("https://accounts.zoho.com/oauth/v2/auth?response_type=code&client_id=1000.CU8EIFGUKKWO76XF6DJI5PMJ072P2T&scope=AaaServer.profile.Read&redirect_uri=http://localhost:8080/final/resp&access_type=offline");
    }
}
//1000.a24a050378aa8cebaf09da1c152f2466.f01aca65536ca537adce887c724f43a4
///https://accounts.zoho.com/oauth/v2/token?grant_type=authorization_code&client_id=1000.CU8EIFGUKKWO76XF6DJI5PMJ072P2T&client_secret=1a69af9c58c99acb88dd7de3be5698fe9de8cfd01a&redirect_uri=http://localhost:8080/final/resp&code=1000.068c518c278212c9dae316bf8470f169.de46dc316a6eab6b7e72e4d4689178d8
///Generating access token https://accounts.zoho.com/oauth/v2/token?refresh_token=1000.19dc8ab2335207675fe7fe87f143d47b.fb2057427e2b5c51e781a99b33ece3fd&client_id=1000.CU8EIFGUKKWO76XF6DJI5PMJ072P2T&client_secret=1a69af9c58c99acb88dd7de3be5698fe9de8cfd01a&grant_type=refresh_token
//https://accounts.zoho.com/oauth/v2/token/revoke?token=1000.c5436b9f25df59875ef53ef83e213d27.1323283aee3cbbafa420c8d2a7293843
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class Login extends HttpServlet {

    // Mivel az index.jsp-ben post van megadva itt is doPost
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String n = request.getParameter("user_name");
        String p = request.getParameter("user_pass");

        // Ha a password cica, akkor tovabb kuldi a kerest a servlet2-nek
        // Ezt termeszetesen nem igy kene elesben hasznalni
        if (p.equals("cica")) {
            // Nincs megcsinalva servlet2 ugyhogy hibat fog dobni
            RequestDispatcher rd = request.getRequestDispatcher("servlet2");
            // Tovabbkuldi
            rd.forward(request, response);
        } else {
            out.print("Sorry Username or Password Error!");
            response.sendRedirect("http://www.inf.u-szeged.hu");
        }
    }

}
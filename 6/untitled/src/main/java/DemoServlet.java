import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Lekerheto az init config driver parametere
        ServletConfig config = getServletConfig();
        String driver = config.getInitParameter("driver");
        out.print("Driver is: " + driver + "<br>");

        // Lekerheto az osszes init-ben talalhato parameter neve
        Enumeration<String> e = config.getInitParameterNames();
        String str = "";
        // Majd rajtuk vegig iteralva kiirhato a nevuk
        while (e.hasMoreElements()) {
            str = e.nextElement();
            out.println("<br>Parameter from config");
            out.print("<br>Name: " + str);
            out.print("<br>Value: " + config.getInitParameter(str));
        }

        // Contextbol is lekerhetoek az ott megadott parameterek
        ServletContext context = getServletContext();
        Enumeration<String> f = context.getInitParameterNames();
        String str2 = "";

        // Ugyanugy vegig iteralunk rajta
        while (f.hasMoreElements()) {
            str2 = f.nextElement();
            out.println("<br><br>Parameter from context");
            out.print("<br>Name: " + str2);
            out.print("<br>Value:" + context.getInitParameter(str2));
        }

        // Itt a setAttribute-al lehet beallitani
        context.setAttribute("class","Druid");
        // Ha csak az adott attributumot szeretnenk lekerni
        String attrib = (String)context.getAttribute("class");
        out.println("<br><br>Using get attribute to get one value");
        out.println("<br>Class: " + attrib);

        String str3 = "";
        // Ezt kiirva az osszes contextben talalhato attributum kiirodik
        Enumeration<String> attributes = context.getAttributeNames();
        while (attributes.hasMoreElements()) {
            str3 = attributes.nextElement();
            out.println("<br><br> Attribute set to context");
            out.print("<br>Name: " + str3);
            out.print("<br>Value: " + context.getAttribute(str3));
        }

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

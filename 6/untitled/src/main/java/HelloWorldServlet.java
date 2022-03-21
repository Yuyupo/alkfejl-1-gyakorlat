import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.append(
                "<html>\n" +
                        "<head><title>Paraméterek</title></head>\n" +
                        "<body>\n" +
                        "<table>\n"
        );

        Map<String, String[]> params = request.getParameterMap();
        boolean isEmpty = false;

        // Vegig nezzuk a mapot benne a parameterekkel
        // A key lesz a valtozo neve "last_name, "first_name"
        // value[0] pedig amit kapott ertek
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            if (entry.getValue()[0].isEmpty()) {
                isEmpty = true;
                break;
            } else {
                out.append("<tr>"
                        + "<td>" + entry.getKey() + "</td>"
                        + "<td>" + entry.getValue()[0] + "</td>"
                        + "</tr>");
            }
         }

        // Ha volt benne ures field akkor 400as response
        if (isEmpty) {
            out.append("Empty form field");
            response.setStatus(400);
        } else {
            response.setStatus(200);
        }

        out.append("</table>\n" +
                "</body>" +
                "</html>");
    }

}
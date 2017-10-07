package market;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mjord
 */
@WebServlet(name = "Items", urlPatterns = {"/Items"})
public class Items extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession checkSession = request.getSession();
        User testUser = (User)checkSession.getAttribute("session");
        
        if(testUser == null){
            String login = "http://localhost:8080/mikes_market/";
            response.sendRedirect(login);
        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>\n" +
    "        <title>Mike's Market | Store</title>\n" +
    "        <link href=\"style.css\" rel='stylesheet'>\n" +
                    "<meta charset='UTF-8'>" +
    "    </head>");
            out.println("<body>");
            out.println("<div id='wrapper'>");
            out.println("<nav>\n" +
    "            <ul>\n" +
    "                <li><a href=\"Index\">Home</a></li>\n" +
    "                <li><a href=\"Registration\">Registration</a></li>\n" +
    "                <li><a href=\"Items\">Items</a></li>\n" +
    "                <li><a href='Cart'>Cart</a></li>\n" +
    "                <li><a href='Orders'>Order History</a></li>\n" +
    "            </ul>\n" +
    "        </nav>");
            out.println("Hi, " + testUser.firstName);
            out.println("<header>\n" +
    "        <h1>Electronics for Sale</h1>\n" +
    "        </header>");
            out.println("<div id='items'>\n" +
    "        <form action='Items' method='post'>" +
    "        <div>" +
    "            <input type='hidden' name='name' value='TV'>" +
    "            <input type='hidden' name='price' value='125'>" +
    "            <img src='http://cdn.toptenreviews.com/rev/scrn/large/58228-emerson-led-tv3.jpg' alt='TV'>\n" +
    "            <p>On sale for $125. The TV is as good as new with the original remote included (with batteries). It \n" +
    "            includes 3 HDMI ports, a headphone jack, a cable in jack, and a USB port.</p>\n" +
    "            Quantity: <input type='text' name='quantity'>\n" +
    "            <button>Add to Cart</button>\n" +
    "        </div>" +
    "        </form>" +
    "        <form action='Items' method='post'>" +
    "        <div>\n" +
    "            <input type='hidden' name='name' value='Raspberry Pi'>" +
    "            <input type='hidden' name='price' value='20'>" +
    "            <img src='https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTAzSE6QSWkDObdt0XGb9oGlO2kqllDwFaAYP_A_wd0aDUOM-rZ' alt='Raspberry Pi'>\n" +
    "            <p>On sale for $20. The Raspberry Pi was recently bought and doesn't have any wear \n" +
    "            and tear. It comes with the operating system Raspbian pre-installed and an 8GB \n" +
    "            SD card.</p>\n" +
    "            Quantity: <input type='text' name='quantity'>\n" +
    "            <button>Add to Cart</button>\n" +
    "        </div>\n" +
    "        </form>" +
    "        <form action='Items' method='post'>" +
    "        <div>\n" +
    "            <input type='hidden' name='name' value='PS4'>" +
    "            <input type='hidden' name='price' value='250'>" +
    "            <img src='http://www.spokeslabs.com/jstone/ps4_images/ps4-hrdware-large18.jpg' alt='PS4'>\n" +
    "            <p>On sale for $250. Playstation comes with 4 games, one controller with charging cable, \n" +
    "            and a PSN subscription. Willing to negotiate price.</p>\n" +
    "            Quantity: <input type='text' name='quantity'>\n" +
    "            <button>Add to Cart</button>\n" +
    "        </div>\n" +
    "        </form>" +
    "        <form action='Items' method='post'>" +
    "        <div>\n" +
    "            <input type='hidden' name='name' value='Apple TV'>" +
    "            <input type='hidden' name='price' value='30'>" +
    "            <img src='http://s3.amazonaws.com/digitaltrends-uploads-prod/2015/02/apple-tv.jpg' alt='Apple TV' width='300' height='400'>\n" +
    "            <p>On sale for $30. Comes with HDMI cable, power cable and original remote to the TV. Has a few \n" +
    "            scratches on the top and bottom but the Apple TV is still in great condition.</p>\n" +
    "            Quantity: <input type='text' name='quantity'>\n" +
    "            <button>Add to Cart</button>\n" +
    "        </div>\n" +
    "        </form>" +
    "        <form action='Items' method='post'>" +
    "        <div>\n" +
    "            <input type='hidden' name='name' value='Beats Headphones'>" +
    "            <input type='hidden' name='price' value='100'>" +
    "            <img src='https://ss7.vzw.com/is/image/VerizonWireless/beats-audio-headphones-red-900-00078-01-iset?$acc-lg$&amp;fmt=jpeg' alt='Beats headphones'>\n" +
    "            <p>On sale for $100. Comes with auxiliary cord. There are a few scratches on the headphones \n" +
    "            and two screws are missing.</p>\n" +
    "            Quantity: <input type='text' name='quantity'>\n" +
    "            <button>Add to Cart</button>\n" +
    "        </div>\n" +
    "        </form>" +
    "        <form action='Items' method='post'>" +
    "        <div>\n" +
    "            <input type='hidden' name='name' value='HP Laptop'>" +
    "            <input type='hidden' name='price' value='100'>" +
    "            <img src='http://images.devshed.com/dh/stories/....enrique/HP_Pavilion_g6-1a69us_pic1.jpg' alt='HP Laptop'>\n" +
    "            <p>On sale for $100. Comes with charging cable. Several scratches on the cover and mouse buttons. Laptop \n" +
    "            was originally bought 3 years ago.</p>\n" +
    "            Quantity: <input type='text' name='quantity'>\n" +
    "            <button>Add to Cart</button>\n" +
    "        </div>\n" +
    "        </form>" +
    "        </div>");
            out.println("<footer id=\"main_footer\">\n" +
    "		<ul>\n" +
    "			<li><a href=\"https://www.facebook.com/profile.php?id=100000143260199\">Facebook</a></li>\n" +
    "			<li><a href=\"https://twitter.com/mjorda39\">Twitter</a></li>\n" +
    "		</ul>\n" +
    "		<p>&copy;Copyright Michael Jordan 2016</p>\n" +
    "	</footer>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String quantity = request.getParameter("quantity");
        String price = request.getParameter("price");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("session");
        
        if(quantity == null || quantity.equals("")) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>\n" +
                    "        <title>Mike's Market | Home</title>\n" +
                    "        <link href=\"style.css\" rel='stylesheet'>\n" +
                    "        <script src=\"login.js\"></script>\n" +
                    "        <meta charset='UTF-8'>" +
                    "    </head>");
            out.println("<div id='wrapper'>");
            out.println("<nav>\n" +
                    "            <ul>\n" +
                    "                <li><a href=\"Index\">Home</a></li>\n" +
                    "                <li><a href=\"Registration\">Registration</a></li>\n" +
                    "                <li><a href=\"Items\">Items</a></li>\n" +
                    "                <li><a href='Cart'>Cart</a></li>\n" +
                    "                <li><a href='Orders'>Order History</a></li>\n" +
                    "            </ul>\n" +
                    "        </nav>");
            out.println("<p>Quantity not given");
            out.println("<p>No items ordered.</p>");
            out.println("<footer id=\"main_footer\">\n" +
                    "		<ul>\n" +
                    "			<li><a href=\"https://www.facebook.com/profile.php?id=100000143260199\">Facebook</a></li>\n" +
                    "			<li><a href=\"https://twitter.com/mjorda39\">Twitter</a></li>\n" +
                    "		</ul>\n" +
                    "		<p>&copy;Copyright Michael Jordan 2016</p>\n" +
                    "	</footer>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } else if(Integer.parseInt(quantity) <= 0) {
            doGet(request, response);
        } else {
            //session cart
            Integer[] item = {Integer.parseInt(quantity), Integer.parseInt(price)};
            if(user.cart.get(name) == null){
                user.cart.put(name, item);
            } else {
                int newQuantity = Integer.parseInt(quantity);
                user.cart.get(name)[0] += newQuantity;
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>\n" +
                    "        <title>Mike's Market | Home</title>\n" +
                    "        <link href=\"style.css\" rel='stylesheet'>\n" +
                    "        <script src=\"login.js\"></script>\n" +
                    "        <meta charset='UTF-8'>" +
                    "    </head>");
            out.println("<div id='wrapper'>");
            out.println("<nav>\n" +
                    "            <ul>\n" +
                    "                <li><a href=\"Index\">Home</a></li>\n" +
                    "                <li><a href=\"Registration\">Registration</a></li>\n" +
                    "                <li><a href=\"Items\">Items</a></li>\n" +
                    "                <li><a href='Cart'>Cart</a></li>\n" +
                    "                <li><a href='Orders'>Order History</a></li>\n" +
                    "            </ul>\n" +
                    "        </nav>");
            out.println("<p>Items successfully added to cart.</p>");
            out.println("<footer id=\"main_footer\">\n" +
                    "		<ul>\n" +
                    "			<li><a href=\"https://www.facebook.com/profile.php?id=100000143260199\">Facebook</a></li>\n" +
                    "			<li><a href=\"https://twitter.com/mjorda39\">Twitter</a></li>\n" +
                    "		</ul>\n" +
                    "		<p>&copy;Copyright Michael Jordan 2016</p>\n" +
                    "	</footer>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
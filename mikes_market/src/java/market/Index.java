package market;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mjord
 */
@WebServlet(name = "Index", urlPatterns = {"/Index"})
public class Index extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String loggingOut = request.getParameter("loggingOut");
        Dao dao = null;
        
        try {
            dao = new Dao();
            dao.retrieveUser(username);
        } catch(Exception e){
            out.println("Problem querying database.");
            e.printStackTrace();
        }
        
        if(loggingOut == null || loggingOut.equals("")){
            HttpSession checkSession = request.getSession();
            User testUser = (User)checkSession.getAttribute("session");
            
            if(testUser == null){
                if((username == null || username.equals("")) && (password == null || password.equals(""))){
                    //REGULAR LOGIN FORM
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
                    out.println("<header>\n" +
            "        <h1>Mike's Market</h1>\n" +
            "        <img id='shoppingCart' src='https://datsunspirit.com/wp-content/uploads/2015/04/shopping-cart-hi.png' alt='cart'> \n" +
            "        <p>A place for buyers and sellers alike. The aim of Mike's \n" +
            "        Market is to provide you with everything you need and in the \n" +
            "        quickest time possible from the time you click order.</p>\n" +
            "        </header>");
                    out.println("<form action='Index' method='post'>" +
            "            <label>Username: </label><input type='text' id='user' name='username'>\n" +
            "            <label>Password: </label><input type='password' id='pass' name='password'>\n" +
            "            <input type='submit' id=\"login\" value='Login'><br>\n" +
            "             </form>");
                    out.println("<p id='promo'>For prices as low as $20!!!</p>");
                    out.println("<img id='mascot' src='http://assets.sbnation.com/assets/1280371/Sam_los_angeles_1984.png' alt='mascot'>");
                    out.println("<div>\n" +
            "            <img src='http://cdn.toptenreviews.com/rev/scrn/large/58228-emerson-led-tv3.jpg' alt='Items for Sale' id='slideshow'><br>\n" +
            "            <button onclick='backward()'>&lt;-</button>\n" +
            "            <button onclick='forward()'>-&gt;</button>\n" +
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
                } else {//NO ACCOUNT
                    //check database instead of hashmap
                    if(!username.equals(dao.getUser())){
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
                            out.println("<header>\n" +
                    "        <h1>Mike's Market</h1>\n" +
                    "        <img id='shoppingCart' src='https://datsunspirit.com/wp-content/uploads/2015/04/shopping-cart-hi.png' alt='cart'> \n" +
                    "        <p>A place for buyers and sellers alike. The aim of Mike's \n" +
                    "        Market is to provide you with everything you need and in the \n" +
                    "        quickest time possible from the time you click order.</p>\n" +
                    "        </header>");
                            out.println("<form action='Index' method='post'>" +
                    "            <label>Username: </label><input type='text' id='user' name='username'>\n" +
                    "            <label>Password: </label><input type='password' id='pass' name='password'>\n" +
                    "            <input type='submit' id=\"login\" value='Login'><br>\n" +
                    "             </form>");
                            out.println("<p id='errorMsg'>You have not signed up for an account.</p>");
                            out.println("<p id='promo'>For prices as low as $20!!!</p>");
                            out.println("<img id='mascot' src='http://assets.sbnation.com/assets/1280371/Sam_los_angeles_1984.png' alt='mascot'>");
                            out.println("<div>\n" +
                    "            <img src='http://cdn.toptenreviews.com/rev/scrn/large/58228-emerson-led-tv3.jpg' alt='Items for Sale' id='slideshow'><br>\n" +
                    "            <button onclick='backward()'>&lt;-</button>\n" +
                    "            <button onclick='forward()'>-&gt;</button>\n" +
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
                    } else {//WRONG PASSWORD
                        //check database password instead of hashmap password
                        if(!password.equals(dao.getPass())){
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
                            out.println("<header>\n" +
                    "        <h1>Mike's Market</h1>\n" +
                    "        <img id='shoppingCart' src='https://datsunspirit.com/wp-content/uploads/2015/04/shopping-cart-hi.png' alt='cart'> \n" +
                    "        <p>A place for buyers and sellers alike. The aim of Mike's \n" +
                    "        Market is to provide you with everything you need and in the \n" +
                    "        quickest time possible from the time you click order.</p>\n" +
                    "        </header>");
                            out.println("<form action='Index' method='post'>" +
                    "            <label>Username: </label><input type='text' id='user' name='username'>\n" +
                    "            <label>Password: </label><input type='password' id='pass' name='password'>\n" +
                    "            <input type='submit' id=\"login\" value='Login'><br>\n" +
                    "             </form>");
                            out.println("<p id='errorMsg'>Invalid username/password combination.</p>");
                            out.println("<p id='promo'>For prices as low as $20!!!</p>");
                            out.println("<img id='mascot' src='http://assets.sbnation.com/assets/1280371/Sam_los_angeles_1984.png' alt='mascot'>");
                            out.println("<div>\n" +
                    "            <img src='http://cdn.toptenreviews.com/rev/scrn/large/58228-emerson-led-tv3.jpg' alt='Items for Sale' id='slideshow'><br>\n" +
                    "            <button onclick='backward()'>&lt;-</button>\n" +
                    "            <button onclick='forward()'>-&gt;</button>\n" +
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
                        } else {//AFTER LOGGING IN ON THIS PAGE
                        HttpSession mySession = request.getSession();
                        User user = dao.retrieveUser(username);
                        UserBean bean = dao.createBean(username);
                        mySession.setAttribute("session", user);
                        mySession.setAttribute("bean", bean);
                        //out.println(bean);
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
                        out.println("Hi, " + user.firstName);
                        out.println("<form action='Index' method='post'>");
                        out.println("<input type='hidden' name='loggingOut' value='loggingOut'><br><br>");
                        out.println("<input type='submit' value='Logout'>");
                        out.println("</form>");
                        out.println("<header>\n" +
            "        <h1>Mike's Market</h1>\n" +
            "        <img id='shoppingCart' src='https://datsunspirit.com/wp-content/uploads/2015/04/shopping-cart-hi.png' alt='cart'> \n" +
            "        <p>A place for buyers and sellers alike. The aim of Mike's \n" +
            "        Market is to provide you with everything you need and in the \n" +
            "        quickest time possible from the time you click order.</p>\n" +
            "        </header>");
                        out.println("<p id='promo'>For prices as low as $20!!!</p>");
                        out.println("<img id='mascot' src='http://assets.sbnation.com/assets/1280371/Sam_los_angeles_1984.png' alt='mascot'>");
                        out.println("<div>\n" +
            "            <img src='http://cdn.toptenreviews.com/rev/scrn/large/58228-emerson-led-tv3.jpg' alt='Items for Sale' id='slideshow'><br>\n" +
            "            <button onclick='backward()'>&lt;-</button>\n" +
            "            <button onclick='forward()'>-&gt;</button>\n" +
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
                }
            } else {//LOGGED IN AND BROWSING SITE
                HttpSession mySession = request.getSession();
                User user = (User)mySession.getAttribute("session");
                
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
                out.println("Hi, " + user.firstName);
                out.println("<form action='Index' method='post'>");
                out.println("<input type='hidden' name='loggingOut' value='loggingOut'><br><br>");
                out.println("<input type='submit' value='Logout'>");
                out.println("</form>");
                out.println("<header>\n" +
            "        <h1>Mike's Market</h1>\n" +
            "        <img id='shoppingCart' src='https://datsunspirit.com/wp-content/uploads/2015/04/shopping-cart-hi.png' alt='cart'> \n" +
            "        <p>A place for buyers and sellers alike. The aim of Mike's \n" +
            "        Market is to provide you with everything you need and in the \n" +
            "        quickest time possible from the time you click order.</p>\n" +
            "        </header>");
                out.println("<p id='promo'>For prices as low as $20!!!</p>");
                out.println("<img id='mascot' src='http://assets.sbnation.com/assets/1280371/Sam_los_angeles_1984.png' alt='mascot'>");
                out.println("<div>\n" +
            "            <img src='http://cdn.toptenreviews.com/rev/scrn/large/58228-emerson-led-tv3.jpg' alt='Items for Sale' id='slideshow'><br>\n" +
            "            <button onclick='backward()'>&lt;-</button>\n" +
            "            <button onclick='forward()'>-&gt;</button>\n" +
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
        } else {//LOGGED OUT
            HttpSession mySession = request.getSession();
            mySession.invalidate();
            
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
            out.println("<header>\n" +
                    "        <h1>Mike's Market</h1>\n" +
                    "        <img id='shoppingCart' src='https://datsunspirit.com/wp-content/uploads/2015/04/shopping-cart-hi.png' alt='cart'> \n" +
                    "        <p>A place for buyers and sellers alike. The aim of Mike's \n" +
                    "        Market is to provide you with everything you need and in the \n" +
                    "        quickest time possible from the time you click order.</p>\n" +
                    "        </header>");
            out.println("<form action='Index' method='post'>" +
                    "            <label>Username: </label><input type='text' id='user' name='username'>\n" +
                    "            <label>Password: </label><input type='password' id='pass' name='password'>\n" +
                    "            <input type='submit' id=\"login\" value='Login'><br>\n" +
                    "             </form>");
            out.println("<p id='success_msg'>Come back and see us!</p>");
            out.println("<p id='promo'>For prices as low as $20!!!</p>");
            out.println("<img id='mascot' src='http://assets.sbnation.com/assets/1280371/Sam_los_angeles_1984.png' alt='mascot'>");
            out.println("<div>\n" +
                    "            <img src='http://cdn.toptenreviews.com/rev/scrn/large/58228-emerson-led-tv3.jpg' alt='Items for Sale' id='slideshow'><br>\n" +
                    "            <button onclick='backward()'>&lt;-</button>\n" +
                    "            <button onclick='forward()'>-&gt;</button>\n" +
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
        dao.closeDatabase();
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

package market;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "Registration", urlPatterns = {"/Registration"})
public class Registration extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession mySession = request.getSession();
        User user = (User)mySession.getAttribute("session");
        
        if(user == null) {
            Dao userDao = new Dao();
            String username = request.getParameter("username");
            boolean allowed = userDao.accountCheckUser(username);
            String firstName = request.getParameter("fname");
            String lastName = request.getParameter("lname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String[] attributes = {username, firstName, lastName, email, password, role};
            ArrayList<String> errors = new ArrayList<String>();
            int filledOut = 0;
            
            for(String attribute : attributes) {
                if(attribute == null || attribute.equals("") || attribute.length() == 0) {
                    break;
                } else {
                    filledOut++;
                }
            }
            
            userDao.closeDatabase();
            
            if(filledOut == 6) {
                if(allowed == false){
                    errors.add("Username already in use.");
                }
                if(username.length() < 6){
                    errors.add("Username must be more than 6 characters.");
                }
                if(email.indexOf('@') == -1 || email.indexOf('.') == -1){
                    errors.add("Invalid email address.");
                }
                if(password.length() < 8){
                    errors.add("Password must be more than 8 characters.");
                }
                if(errors.isEmpty()) { //no errors registering
                    try {
                        Dao registerDao = new Dao();
                        User aUser = registerDao.register(username, firstName, lastName, email, password, role);                        
                        UserBean bean = registerDao.createBean(username);
                        mySession.setAttribute("session", aUser);
                        mySession.setAttribute("bean", bean);
                        
                        registerDao.closeDatabase();
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Mike's Market | Registration</title>");
                        out.println("<link href='style.css' rel='stylesheet'>");
                        out.println("<style>"
                                + "#mascot {"
                                + "     top: 20%;"
                                + "     left: 25%;"
                                + "}"
                                + "</style>");
                        out.println("<meta charset='UTF-8'>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<div id='wrapper'>");
                        out.println("<nav>"
                                + "     <ul>"
                                + "         <li><a href='Index'>Home</a></li>"
                                + "         <li><a href='Registration'>Registration</a></li>"
                                + "         <li><a href='Items'>Items</a></li>"
                                + "         <li><a href='Cart'>Cart</a></li>"
                                + "         <li><a href='Orders'>Order History</a></li>"
                                + "     </ul>"
                                + "</nav>");
                        out.println("Thank you for registering.");
                        out.println("<footer id=\"main_footer\">\n"
                                +      "<ul>\n"
                                +           "<li><a href=\"https://www.facebook.com/profile.php?id=100000143260199\">Facebook</a></li>\n"
                                +            "<li><a href=\"https://twitter.com/mjorda39\">Twitter</a></li>\n"
                                +      "</ul>\n"
                                +      "<p>&copy;Copyright Michael Jordan 2016</p>\n"
                                +      "</footer>");
                        out.println("</div>");
                        out.println("</body>");
                        out.println("</html>");
                    } catch (Exception e){
                        out.println("Problem creating user.");
                        e.printStackTrace();
                    }
                } else { //there are errors in registration
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Mike's Market | Registration</title>");
                    out.println("<link href='style.css' rel='stylesheet'>");
                    out.println("<style>"
                            + "#mascot {"
                            + "     top: 20%;"
                            + "     left: 30%;"
                            + "}"
                            + "</style>");
                    out.println("<meta charset='UTF-8'>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div id='wrapper'>");
                    out.println("<nav>"
                            + "     <ul>"
                            + "         <li><a href='Index'>Home</a></li>"
                            + "         <li><a href='Registration'>Registration</a></li>"
                            + "         <li><a href='Items'>Items</a></li>"
                            + "         <li><a href='Cart'>Cart</a></li>"
                            + "         <li><a href='Orders'>Order History</a></li>"
                            + "     </ul>"
                            + "</nav>");
                    for(String error : errors){
                        out.println("<p>" + error + "</p>");
                    }
                    out.println("<p>Use the back button to try again.</p>");
                    out.println("<footer id=\"main_footer\">\n"
                            +      "<ul>\n"
                            +           "<li><a href=\"https://www.facebook.com/profile.php?id=100000143260199\">Facebook</a></li>\n"
                            +            "<li><a href=\"https://twitter.com/mjorda39\">Twitter</a></li>\n"
                            +      "</ul>\n"
                            +      "<p>&copy;Copyright Michael Jordan 2016</p>\n"
                            +      "</footer>");
                    out.println("</div>");
                    out.println("</body>");
                    out.println("</html>");
                }
            } else {//output form again if everything isn't filled out
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Mike's Market | Registration</title>");
                    out.println("<link href='style.css' rel='stylesheet'>");
                    out.println("<style>"
                            + "#mascot {"
                            + "     top: 20%;"
                            + "     left: 25%;"
                            + "}"
                            + "</style>");
                    out.println("<meta charset='UTF-8'>");
                    out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js\"></script>");
                    out.println("<script src=\"javascriptFile.js\"></script>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div id='wrapper'>");
                    out.println("<nav>"
                            + "     <ul>"
                            + "         <li><a href='Index'>Home</a></li>"
                            + "         <li><a href='Registration'>Registration</a></li>"
                            + "         <li><a href='Items'>Items</a></li>"
                            + "         <li><a href='Cart'>Cart</a></li>"
                            + "         <li><a href='Orders'>Order History</a></li>"
                            + "     </ul>"
                            + "</nav>");
                    out.println("<img id='mascot' src='http://assets.sbnation.com/assets/1280371/Sam_los_angeles_1984.png' alt='mascot'>");
                    out.println("<form action='Registration' method='post'>");
                    out.println("<label>Username: </label><input type='text' id=\"username\" name='username' onblur='checkUsername()'><br>");
                    out.println("<div id='username_err'></div>");
                    out.println("<label>First name: </label><input type='text' name='fname'><br>");
                    out.println("<label>Last name: </label><input type='text' name='lname'><br>");
                    out.println("<label>Email: </label><input type='text' name='email'><br>");
                    out.println("<label>Password: </label><input type='password' name='password'><br>");
                    out.println("<label>Role: </label>");
                    out.println("<input type='radio' name='role' value='buyer' checked> Buyer");
                    out.println("<input type='radio' name='role' value='seller'> Seller<br>");
                    out.println("<input type='submit' value='Create Account!'>");
                    out.println("</form>");
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
        } else {//already logged in
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Mike's Market | Registration</title>");
            out.println("<link href='style.css' rel='stylesheet'>");
            out.println("<style>"
                    + "#mascot {"
                    + "     top: 20%;"
                    + "     left: 30%;"
                    + "}"
                    + "</style>");
            out.println("<meta charset='UTF-8'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='wrapper'>");
            out.println("<nav>"
                    + "     <ul>"
                    + "         <li><a href='Index'>Home</a></li>"
                    + "         <li><a href='Registration'>Registration</a></li>"
                    + "         <li><a href='Items'>Items</a></li>"
                    + "         <li><a href='Cart'>Cart</a></li>"
                    + "         <li><a href='Orders'>Order History</a></li>"
                    + "     </ul>"
                    + "</nav>");
            out.println("You are already logged in.");
            out.println("<footer id=\"main_footer\">\n"
                    +      "<ul>\n"
                    +           "<li><a href=\"https://www.facebook.com/profile.php?id=100000143260199\">Facebook</a></li>\n"
                    +            "<li><a href=\"https://twitter.com/mjorda39\">Twitter</a></li>\n"
                    +      "</ul>\n"
                    +      "<p>&copy;Copyright Michael Jordan 2016</p>\n"
                    +      "</footer>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
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
}
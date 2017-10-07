package market;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
@WebServlet(name = "Cart", urlPatterns = {"/Cart"})
public class Cart extends HttpServlet {
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
            String username = testUser.userName;
            String clear = request.getParameter("clear");
            String amount = request.getParameter("amount");
            String checkout = request.getParameter("checkout");
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/marketDB"+ "?user=MikeyJordan&password=systor!");
                Statement statement = connect.createStatement();
                ResultSet db_bal = statement.executeQuery("SELECT * FROM marketDB.Users WHERE username='" + username + "'");
                while(db_bal.next() != false){
                    testUser.balance = Double.parseDouble(db_bal.getString("balance"));
                    
                }
                if(amount != null && !amount.equals("") && Integer.parseInt(amount) > 0){
                //add funds to database instead of session
                //testUser.addFunds(Integer.parseInt(amount));
                testUser.balance += Integer.parseInt(amount);
                statement.executeUpdate("UPDATE marketDB.Users SET balance='" + testUser.balance + "' WHERE username='" + username + "'" );
                }
                if((clear == null || clear.equals("")) && (checkout == null || checkout.equals(""))) {
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
                    out.println("<div id='account'>\n" +
            "        <form action='Cart' method='post'>" +
            "        <p>Account Balance: $" + String.format("%.2f", testUser.balance) + "</p>\n" +
            "        <input type='text' id='amount' name='amount'>" +
            "        <button>Add Money</button>\n" +
            "        </form>" +
            "        </div>");
                    out.println("<header>\n" +
        "        <h1>Your Cart</h1>\n" +
        "        </header>");
                    out.println("<table id=\"cart\">\n" +
            "            <thead>\n" +
            "                <tr>\n" +
            "                    <th>Item Number</th>\n" +
            "                    <th>Name</th>\n" +
            "                    <th>Quantity</th>\n" +
            "                    <th>Total Cost</th>\n" +
            "                </tr>\n" +
            "            </thead>\n" +
            "            <tbody>\n");
                    //session cart
                    int counter = 1;
                    for(String item : testUser.cart.keySet()){
                        out.println("<tr>");
                        out.println("<td>" + counter + "</td>");
                        out.println("<td>" + item + "</td>");
                        out.println("<td>" + testUser.cart.get(item)[0] + "</td>");
                        out.println("<td>$" + testUser.cart.get(item)[0]*testUser.cart.get(item)[1] + "</td>");
                        out.println("</tr>");
                        counter++;
                    }
                    out.println("<tr>");
                    out.println("<td colspan='4'>$" + testUser.calculateTotal() + "</td>");
                    out.println("</tr>");
                    out.println(
            "            </tbody>\n" +
            "        </table>");
                    out.println("<form action='Cart' method='post'>");
                    out.println("<input type='hidden' name='clear' value='clear cart'>");
                    out.println("<button onclick=\"clearCart()\">Clear Cart</button>\n");
                    out.println("</form>");
                    out.println("<form action='Cart' method='post'>");
                    out.println("<input type='hidden' name='checkout' value='checkout'>");
                    out.println("<button onclick=\"checkout()\">Checkout</button>");
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
            } else if(clear != null && !clear.equals("")){
                testUser.cart.clear();
                try {
                        int cart = statement.executeUpdate("DELETE FROM marketDB.cart WHERE 1");
                    } catch(Exception e){
                        out.println("Problem querying database.");
                        e.printStackTrace();
                    }
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
                out.println("<div id='account'>\n" +
        "        <form action='Cart' method='post'>" +
        "        <p>Account Balance: $" + String.format("%.2f", testUser.balance) + "</p>\n" +
        "        <input type='text' id='amount' name='amount'>" +
        "        <button>Add Money</button>\n" +
        "        </form>" +
        "        </div>");
                out.println("<header>\n" +
    "        <h1>Your Cart</h1>\n" +
    "        </header>");
                out.println("Cart cleared.");
                out.println("<footer id=\"main_footer\">\n" +
    "		<ul>\n" +
    "			<li><a href=\"https://www.facebook.com/profile.php?id=100000143260199\">Facebook</a></li>\n" +
    "			<li><a href=\"https://twitter.com/mjorda39\">Twitter</a></li>\n" +
    "		</ul>\n" +
    "		<p>&copy;Copyright Michael Jordan 2016</p>\n" +
    "	</footer>");
            } else if(checkout != null && !checkout.equals("")){
                if(testUser.balance >= testUser.calculateTotal()){
                    testUser.balance -= testUser.calculateTotal();
                    try {
                        statement.executeUpdate("UPDATE marketDB.Users SET balance='" + testUser.balance + "' WHERE username='" + username + "'" );
                        ResultSet trans = statement.executeQuery("SELECT lastTransaction FROM marketDB.Users WHERE username='" + username + "'");
                        while(trans.next()!= false){
                            testUser.transNumber = Integer.parseInt(trans.getString("lastTransaction"));
                        }
                        testUser.transNumber++;
                        for(String item : testUser.cart.keySet()){
                            String name = item;
                            String quantity = Integer.toString(testUser.cart.get(item)[0]);
                            String price = Integer.toString(testUser.cart.get(item)[1]);
                            statement.executeUpdate("INSERT INTO marketDB.transactions (username, transactionNumber, name, quantity, cost) VALUES (" + "'" + username + "', '" + testUser.transNumber + "', '" + name + "', '" + Integer.parseInt(quantity) + "', '" + Integer.parseInt(quantity)*Integer.parseInt(price) + "');");
                        }
                        statement.executeUpdate("UPDATE marketDB.Users SET lastTransaction='" + testUser.transNumber + "' WHERE username='" + username + "'" );
                    } catch(Exception e){
                        out.println("Problem querying database.");
                        e.printStackTrace();
                    }
                    testUser.cart.clear();
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
                    out.println("<div id='account'>\n" +
            "        <form action='Cart' method='post'>" +
            "        <p>Account Balance: $" + String.format("%.2f", testUser.balance) + "</p>\n" +
            "        <input type='text' id='amount' name='amount'>" +
            "        <button>Add Money</button>\n" +
            "        </form>" +
            "        </div>");
                    out.println("<header>\n" +
        "        <h1>Your Cart</h1>\n" +
        "        </header>");
                    out.println("Successfully checked out.");
                    out.println("<footer id=\"main_footer\">\n" +
        "		<ul>\n" +
        "			<li><a href=\"https://www.facebook.com/profile.php?id=100000143260199\">Facebook</a></li>\n" +
        "			<li><a href=\"https://twitter.com/mjorda39\">Twitter</a></li>\n" +
        "		</ul>\n" +
        "		<p>&copy;Copyright Michael Jordan 2016</p>\n" +
        "	</footer>");
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
                    out.println("<div id='account'>\n" +
            "        <form action='Cart' method='post'>" +
            "        <p>Account Balance: $" + String.format("%.2f", testUser.balance) + "</p>\n" +
            "        <input type='text' id='amount' name='amount'>" +
            "        <button>Add Money</button>\n" +
            "        </form>" +
            "        </div>");
                    out.println("<header>\n" +
        "        <h1>Your Cart</h1>\n" +
        "        </header>");
                    out.println("There were insufficient funds in your account.");
                    out.println("<footer id=\"main_footer\">\n" +
        "		<ul>\n" +
        "			<li><a href=\"https://www.facebook.com/profile.php?id=100000143260199\">Facebook</a></li>\n" +
        "			<li><a href=\"https://twitter.com/mjorda39\">Twitter</a></li>\n" +
        "		</ul>\n" +
        "		<p>&copy;Copyright Michael Jordan 2016</p>\n" +
        "	</footer>");
                    }
                }
                connect.close();
            } catch(Exception e){
                out.println("Problem querying database.");
                e.printStackTrace();
            }
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
        doGet(request, response);
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
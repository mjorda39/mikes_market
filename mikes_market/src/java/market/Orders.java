/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package market;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
@WebServlet(name = "Orders", urlPatterns = {"/Orders"})
public class Orders extends HttpServlet {
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
        PrintWriter out = response.getWriter();
        HttpSession checkSession = request.getSession();
        User testUser = (User)checkSession.getAttribute("session");
        int numTransactions = 0;
        int numItems = 0;
        String username = testUser.userName;
        ArrayList<Integer> amountOfItems = new ArrayList<Integer>();
        int totalCost = 0;
        int grandTotal = 0;
        
        if(testUser == null){
            String login = "http://localhost:8080/mikes_market/";
            response.sendRedirect(login);
        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>\n" +
    "        <title>Mike's Market | Orders</title>\n" +
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
    "            <h1>Past Orders</h1>\n" +
    "        </header>");
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/marketDB"+ "?user=MikeyJordan&password=systor!");
                Statement os = connect.createStatement();
                Statement is = connect.createStatement();
                ResultSet orders = os.executeQuery("SELECT * FROM marketDB.Users WHERE username='" + username + "'");    
                while(orders.next() != false){
                    numTransactions = Integer.parseInt(orders.getString("lastTransaction"));
                }
                ResultSet items = is.executeQuery("SELECT COUNT(transactionNumber) AS numItems FROM marketDB.Transactions WHERE username='" + username + "' GROUP BY transactionNumber" );
                while(items.next() != false){ //fill array list with each order's number of items
                    numItems = Integer.parseInt(items.getString("numItems"));
                    amountOfItems.add(numItems);
                }
            }
            catch (Exception e){
                out.println("Problem querying database");
            }
            
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/marketDB"+ "?user=MikeyJordan&password=systor!");
                
                if(numTransactions == 0) out.println("No order history.");
                else {
                    for(int orderCounter = 1; orderCounter <= numTransactions; orderCounter++){
                        Statement os = connect.createStatement();
                        ResultSet order = os.executeQuery("SELECT * FROM marketDB.Transactions WHERE username='" + username + "' AND transactionNumber=" + orderCounter);
                        out.println("<h1>Order " + orderCounter + "</h1>");
                        out.println("<table>\n" +
                    "            <thead>\n" +
                    "                <tr>\n" +
                    "                    <th>Name</th>\n" +
                    "                    <th>Quantity</th>\n" +
                    "                    <th>Total Cost</th>\n" +
                    "                </tr>\n" +
                    "            </thead>\n");
                        out.println("<tbody>");
                        while(order.next() != false){
                            int counter = 0;
                            for(int j = 0; j < amountOfItems.get(counter); j++){
                                String name = order.getString("name");
                                String quantity = order.getString("quantity");
                                String cost = order.getString("cost");
                                int costPerItem = Integer.parseInt(cost) / Integer.parseInt(quantity);
                                int orderCost = Integer.parseInt(quantity)*costPerItem;
                                totalCost += orderCost;
                                out.println("<tr>");
                                out.println("<td>" + name + "</td>");
                                out.println("<td>" + quantity + "</td>");
                                out.println("<td>$" + orderCost + "</td>");
                                out.println("</tr>");
                            }
                            while(counter < amountOfItems.size()){
                                counter++;
                            }
                        }
                        out.println("<tr>");
                        out.println("<td colspan='3'>$" + totalCost + "</td>");
                        out.println("</tr>");
                        out.println("</tbody>");
                        out.println("</table>");
                        out.println("<br>");
                        grandTotal += totalCost;
                        totalCost = 0;
                    }
                    out.println("You have spent a total of $" + grandTotal + " on our site.");
                }
                connect.close();
            } catch (Exception e){
                out.println("Problem querying database.");
                e.printStackTrace();
            }
            
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

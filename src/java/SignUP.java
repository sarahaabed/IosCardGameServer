/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import beans.User;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author Administrator
 */
public class SignUP extends HttpServlet {
  private int Status;
    private static final int SUCCESS = 1;
    private static final int EMAIL_ALREADY_EXISTS = 2;
    private static final int FAILED = 3;
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out = response.getWriter();


            // create user object
            User user = new User();

            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            
            System.out.println("++++++++++"+request.getParameter("name"));
            
            // insert user in db
            boolean inserted = userDao.insertUser(user);

            // Create new JSON Obejct
            JSONObject jsonObj = new JSONObject();

            if (inserted) {
                // set status to success
                Status = SUCCESS;
            } else {
                // set status to email already existed
                Status = EMAIL_ALREADY_EXISTS;
            }

            // add login status to json object
            jsonObj.put("Status", Status);

            // make output of json object
            out.print(jsonObj);


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
        System.out.println("from do post methode ");
        // get response writer
        PrintWriter out = response.getWriter();

  // create user object
            User user = new User();

            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            System.out.println(request.getParameter("name"));
            
            // insert user in db
            boolean inserted = userDao.insertUser(user);

             // Create new JSON Obejct
            JSONObject jsonObj = new JSONObject();

            if (inserted) {
                // set status to success
                Status = SUCCESS;
                System.out.println("from if insertion statement  .");
            } else {
                // set status to email already existed
                Status = EMAIL_ALREADY_EXISTS;
                System.out.println("from else insrtion statement .");
            }

            // add login status to json object
            jsonObj.put("status", Status);

            // make output of json object
            out.print(jsonObj);
System.out.println("json is putted");

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

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        userDao = UserDao.getInstance();

    }

}

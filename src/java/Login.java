/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import beans.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Administrator
 */
public class Login extends HttpServlet {

    private static final int LOGIN_SUCESS = 1;
    private static final int INVALIED_EMAIL_OR_PASSWORD = 2;
    private static final int LOGIN_FAILED = 3;
    private int loginStatus;
    private UserDao userDao;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userScore=0;
        // get email parameter
        String email = request.getParameter("email");

        // get password parameter
        String password = request.getParameter("password");

        // find user by Email
        User user = userDao.findUserByEmail(email);
        // check email existance && password equality
        if (user != null && user.getPassword().equals(password)) {
                        loginStatus = 1;            
           userScore=user.getScore();                

        } else {
            loginStatus = 2;
        }

        // Create JSON Obejct
        JSONObject jsonObj = new JSONObject();

        // add login status to json object
        jsonObj.put("Status", loginStatus);
        jsonObj.put("Score",userScore);
        // get response writer
        PrintWriter out = response.getWriter();

        // make output of json object
        out.print(jsonObj);
    }

        
    
 @Override
    public void init() throws ServletException {
        super.init();
        userDao = UserDao.getInstance();
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

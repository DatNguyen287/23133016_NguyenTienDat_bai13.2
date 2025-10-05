package controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.User;
import data.UserDB;
@WebServlet("/userAdmin")
public class UsersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String url = "/index.jsp";

        // Lấy action từ request, nếu null thì mặc định = display_users
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "display_users";
        }

        switch (action) {
            case "display_users": {
                List<User> users = UserDB.selectUsers();
                if (users == null) {
                    users = new ArrayList<>();
                }
                request.setAttribute("users", users);
                url = "/index.jsp";
                break;
            }

            case "display_user": {
                String email = request.getParameter("email");
                User user = UserDB.selectUserByEmail(email);
                if (user != null) {
                    session.setAttribute("user", user);
                }
                url = "/user.jsp";
                break;
            }

            case "update_user": {
                User user = (User) session.getAttribute("user");

                if (user != null) {
                    String firstName = request.getParameter("firstName");
                    String lastName  = request.getParameter("lastName");
                    String email     = request.getParameter("email");

                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);

                    UserDB.update(user);
                }

                List<User> users = UserDB.selectUsers();
                if (users == null) {
                    users = new ArrayList<>();
                }
                request.setAttribute("users", users);

                url = "/index.jsp";
                break;
            }

            case "delete_user": {
                String email = request.getParameter("email");
                User user = UserDB.selectUserByEmail(email);
                if (user != null) {
                    UserDB.delete(user);
                }

                List<User> users = UserDB.selectUsers();
                if (users == null) {
                    users = new ArrayList<>();
                }
                request.setAttribute("users", users);

                url = "/index.jsp";
                break;
            }

            default: {
                // Nếu action không khớp, quay lại danh sách
                List<User> users = UserDB.selectUsers();
                if (users == null) {
                    users = new ArrayList<>();
                }
                request.setAttribute("users", users);
                url = "/index.jsp";
                break;
            }
        }

        // Chuyển tiếp sang trang JSP
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

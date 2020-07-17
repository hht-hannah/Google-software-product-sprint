
package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class UserServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    ArrayList<String> result = new ArrayList<String>();

    UserService userService = UserServiceFactory.getUserService();
    if (userService.isUserLoggedIn()) {
        String userEmail = userService.getCurrentUser().getEmail();
        String urlToRedirectToAfterUserLogsOut = "/";
        String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);

        result.add(userEmail);
        result.add(logoutUrl);
        response.getWriter().println(convertToJsonUsingGson(result));
    } else {
        String urlToRedirectToAfterUserLogsIn = "/";
        String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);

        result.add("");
        result.add(loginUrl);
        response.getWriter().println(convertToJsonUsingGson(result));
    }
  }

    private String convertToJsonUsingGson(ArrayList comments) {
        Gson gson = new Gson();
        String json = gson.toJson(comments);
        return json;
    }
}

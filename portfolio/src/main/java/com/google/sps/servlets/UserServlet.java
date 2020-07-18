
package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class UserServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    HashMap<String, String> result = new HashMap<String, String>();

    UserService userService = UserServiceFactory.getUserService();
    if (userService.isUserLoggedIn()) {
        String userEmail = userService.getCurrentUser().getEmail();
        String urlToRedirectToAfterUserLogsOut = "/";
        String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);

        result.put("email", userEmail);
        result.put("url", logoutUrl);
        response.getWriter().println(convertToJsonUsingGson(result));
    } else {
        String urlToRedirectToAfterUserLogsIn = "/";
        String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);

        result.put("email", "");
        result.put("url", loginUrl);
        response.getWriter().println(convertToJsonUsingGson(result));
    }
  }

    private String convertToJsonUsingGson(HashMap<String, String> data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        return json;
    }
}

package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the values entered in the form.
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String comments = request.getParameter("comments");

    // Print the value so you can see it in the server logs.
    System.out.println("You submitted: name=" + name + ", email = " + email + "comments = " + comments);

    // Write the value to the response so the user can see it.
    response.sendRedirect("/");
  }
}

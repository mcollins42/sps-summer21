package com.google.sps.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/** Returns a list of messages as a JSON array. */
@WebServlet("/messages")
public class MessagesServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");

    ArrayList<String> messages = new ArrayList<>();
    messages.add("Everything is temporary. That don't excuse nothing");
    messages.add("Which is very, very nice as long as nothing goes wrong, and something always goes wrong.");
    messages.add("Bring me the big knife");
    messages.add("I want you to know that no matter what you do you're still going to die, just like everyone else.");

    Gson gson = new Gson();
    response.getWriter().print(gson.toJson(messages));
  }
}
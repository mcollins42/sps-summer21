package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/new-comment")
public class NewCommentServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the values entered in the form.
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String comment = request.getParameter("comment");

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Comment");
    FullEntity taskEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", name)
            .set("email", email)
            .set("comment", comment)
            .build();
    datastore.put(taskEntity);

    // Print the value so you can see it in the server logs.
    System.out.println("You submitted: name=" + name + ", email = " + email + "comments = " + comment);

    // Write the value to the response so the user can see it.
    response.sendRedirect("/");
  }
}

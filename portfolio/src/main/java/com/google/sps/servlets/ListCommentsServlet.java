package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import com.google.sps.data.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/list-comments")
public class ListCommentsServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Gson gson = new Gson();
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query =
            Query.newEntityQueryBuilder().setKind("Comment").setOrderBy(OrderBy.desc("createTime")).build();
    QueryResults<Entity> results = datastore.run(query);

    List<Comment> comments = new ArrayList<Comment>();
    while (results.hasNext()) {
        Entity entity = results.next();
        long id = entity.getKey().getId();
        long createTimeMillis = entity.getLong("createTime");
        Date createTime = new Date(createTimeMillis);
        String name = entity.getString("name");
        String commentText = entity.getString("comment");
        Comment comment = new Comment(id, createTime, name, commentText);
        comments.add(comment);
    }

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(comments));
  }
}
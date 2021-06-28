package com.google.sps.data;

import java.util.Date;

public class Comment {
    private long id;
    private Date createTime;
    private String name;
    private String email;
    private String comment;

    public Comment(long id, Date createTime, String name, String comment) {
        this.id = id;
        this.createTime = createTime;
        this.name = name;
        this.email = email;
        this.comment = comment;
    }
}
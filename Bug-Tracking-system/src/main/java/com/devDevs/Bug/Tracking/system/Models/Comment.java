package com.devDevs.Bug.Tracking.system.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Comment {
    @Id
    private long Id;
    private String content;

    private long userId;
    private long bugId;

    private Date timestamp;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBugId() {
        return bugId;
    }

    public void setBugId(long bugId) {
        this.bugId = bugId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

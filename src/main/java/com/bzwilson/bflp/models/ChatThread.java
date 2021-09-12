package com.bzwilson.bflp.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "messaging")
public class ChatThread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postid;

    @ElementCollection
    private List<String> messages = new ArrayList<>();


    public ChatThread() {
    }

    public ChatThread(List<String> messages) {
        this.messages = messages;
    }

    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}

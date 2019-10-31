package com.example.cs18afs.v3; /**
 * Created by cs18afs on 31/10/2019.
 */

import java.io.Serializable;



public class SlackMessage implements Serializable {

    private String username;

    private String text;


    public SlackMessage(String User, String Message){
        setUsername(User);
        setText(Message);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }





}

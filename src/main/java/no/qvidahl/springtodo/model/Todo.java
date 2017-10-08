package no.qvidahl.springtodo.model;

import org.springframework.stereotype.Repository;


import java.time.Clock;

@Repository
public class Todo {

    private String text;
    private String start, end;
    private Boolean done;


    public Todo() {
    }

    public Todo(String text, String start, String end) {
        this.text = text;
        this.start = start;
        this.end = end;
        this.done = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Boolean isDone() {
        return done;
    }

    public Boolean getDone() {
        return done;
    }


}

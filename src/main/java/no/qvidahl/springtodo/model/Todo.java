package no.qvidahl.springtodo.model;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

@Repository
public class Todo {

    @Id
    public String id;

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

    public String getId() {
        return id;
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

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return String.format(
                "Todo[id=%s, text=%s, start=%s, end=%s]",
                id, text, start, end
        );
    }


}

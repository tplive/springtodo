package no.qvidahl.springtodo;

import no.qvidahl.springtodo.model.Todo;

import java.util.ArrayList;

public class TodoListViewModel {

    private ArrayList<Todo> todoList = new ArrayList<>();

    public TodoListViewModel() {}

    public TodoListViewModel(ArrayList<Todo> todoList) {

        this.todoList = todoList;
    }

    public ArrayList<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(ArrayList<Todo> todoList) {
        this.todoList = todoList;
    }
}

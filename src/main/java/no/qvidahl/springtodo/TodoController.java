package no.qvidahl.springtodo;

import no.qvidahl.springtodo.model.Todo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public final class TodoController {


    private List<Todo> todoList = new ArrayList<>();

    private static final Logger log = Logger.getLogger(TodoController.class);

    public TodoController() {
        todoList.add(new Todo("Kjøp brød", String.format("%s", new Date()), String.format("%s", new Date() )));

    }

    @RequestMapping(path="/", method = RequestMethod.GET)
    public ModelAndView list(Model model) {

        model.addAttribute(todoList);
        model.addAttribute(new Todo());
        return new ModelAndView("list", "todo", model);
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ModelAndView list(Todo todo, Model model) {

        log.info("Ny todo: " + todo.getText());

        todoList.add(todo);

        // Redirect to root view where we show the updated list
        return new ModelAndView(new RedirectView("/"));
    }

    @RequestMapping(path = "/todo/", method = RequestMethod.GET)
    public void todo(@RequestParam("id") Integer id, Model model) {
        model.addAttribute(todoList.get(id));

        Todo todo = todoList.get(id);
        model.addAttribute(todo);
        log.info("Todo text is: " + todo.getText());
    }

    @RequestMapping(path = "/list/json", method = RequestMethod.GET)
    public List<Todo> json() {

        return todoList;
    }


}

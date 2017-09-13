package no.qvidahl.springtodo;

import no.qvidahl.springtodo.model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {

    List<Todo> todoList = new ArrayList<>();


    @RequestMapping(path="/", method = RequestMethod.GET)
    public ModelAndView listItems() {

        return new ModelAndView("index", "todo", new Todo());


    }
}

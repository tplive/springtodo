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

    private static final Logger log = Logger.getLogger(TodoController.class);

    // Todolist, non-persistent.. ;)
    private ArrayList<Todo> todoRepo = new ArrayList<>();

    @RequestMapping(path="/", method = RequestMethod.GET)
    public ModelAndView index(Model model) {

        // Init todolist with a few items:
        if (todoRepo.size() == 0 ) {
            todoRepo.add(new Todo("Kjøp brød", String.format("%s", new Date()), String.format("%s", new Date())));
            todoRepo.add(new Todo("Kjøp melk", String.format("%s", new Date()), String.format("%s", new Date())));
        }

        model.addAttribute("newItem", new Todo());
        model.addAttribute("todoList", todoRepo);
        return new ModelAndView("index", "todos", model);
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String addTodo(@ModelAttribute Todo item) {

        Todo todo = new Todo(item.getText(), "start", "end");
        log.info(String.format("Ny todo: %s, %s - %s", item.getText(), item.getStart(), item.getEnd()));
        todoRepo.add(todo);

        // Redirect to root view where we show the updated list
        return "redirect:/";
    }

    @RequestMapping(path = "/todo/", method = RequestMethod.GET)
    public ModelAndView todo(@RequestParam("id") Integer id, Model model) {
        model.addAttribute(todoRepo.get(id));

        Todo todo = todoRepo.get(id);
        model.addAttribute(todo);
        log.info("Todo text is: " + todo.getText());
        return new ModelAndView("todo", "item", model);
    }

    @RequestMapping(path = "/index/json", method = RequestMethod.GET)
    public @ResponseBody List<Todo> json() {

        return todoRepo;
    }


}

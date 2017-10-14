package no.qvidahl.springtodo;

import no.qvidahl.springtodo.model.Todo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

//@EnableWebMvc
@Controller
public final class TodoController {

    private static final Logger log = Logger.getLogger(TodoController.class);

    // Todolist, non-persistent.. ;)

    private ArrayList<Todo> todoRepo = new ArrayList<>();

    @RequestMapping(path="/", method = RequestMethod.GET)
    public ModelAndView index(Model model) {

        // Init todolist with a few items:
        if (todoRepo.size() == 0 ) {
            todoRepo.add(new Todo("Kjøp brød", "2017-09-29", "2017-10-02"));
            todoRepo.add(new Todo("Kjøp melk", "2017-10-01", "2017-10-05"));
        }

        model.addAttribute("newItem", new Todo());
        model.addAttribute("todoList", todoRepo);
        return new ModelAndView("index", "todos", model);
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String addTodo(@ModelAttribute Todo item) {

        Todo todo = new Todo(item.getText(), item.getStart(), item.getEnd());
        log.info(String.format("Ny todo: %s, %s - %s", item.getText(), item.getStart(), item.getEnd()));
        todoRepo.add(todo);

        // Redirect to root view where we show the updated list
        return "redirect:/";
    }

    @RequestMapping(path = "/update/", method = RequestMethod.PUT)
    public String updateTodo(@RequestParam("id") int id, @ModelAttribute Todo item) {

        todoRepo.set(id, item);
        return "redirect:/";
    }
    @RequestMapping(path = "/delete/", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam("id") int id) {

        log.info("Delete object " + todoRepo.get(id).getText() + "ID: " + id);
        todoRepo.remove(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/todo/", method = RequestMethod.GET)
    public ModelAndView todo(@RequestParam("id") int id, Model model) {

        model.addAttribute(todoRepo.get(id));
        Todo todo = todoRepo.get(id);
        model.addAttribute(todo);
        return new ModelAndView("todo", "item", model);
    }


    @RequestMapping(path = "/index/json", method = RequestMethod.GET)
    public @ResponseBody List<Todo> json() {

        return todoRepo;
    }


}

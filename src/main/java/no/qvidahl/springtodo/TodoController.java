package no.qvidahl.springtodo;

import no.qvidahl.springtodo.model.Todo;
import no.qvidahl.springtodo.model.TodoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public final class TodoController {

    private static final Logger log = Logger.getLogger(TodoController.class);

    List<Todo> todos;

    @Autowired
    private TodoRepository todoRepo;

    @RequestMapping(path="/", method = RequestMethod.GET)
    public ModelAndView index(Model model) {

        todos = todoRepo.findAllByOrderBySortIdxAsc();

        updateSortIdx();

        model.addAttribute("newItem", new Todo());
        model.addAttribute("todoList", todos);
        return new ModelAndView("index", "todos", model);
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String addTodo(@ModelAttribute Todo item) {

        Todo todo = new Todo(item.getText(), item.getStart(), item.getEnd());
        log.info(String.format("Ny todo: %s, %s - %s", item.getText(), item.getStart(), item.getEnd()));
        todoRepo.save(todo);
        todos.add(todo);

        // Redirect to root view where we show the updated list
        return "redirect:/";
    }

    @RequestMapping(path = "/update/", method = RequestMethod.POST)
    public String updateTodo(
            @RequestParam("id") int id,
            @ModelAttribute Todo item) {


        Todo itemToUpdate = todoRepo.findOne(todos.get(id).getId());

        if (itemToUpdate != null) {

            log.info("Update object, db ID: " + itemToUpdate.getId());

            itemToUpdate.setText(item.getText());
            itemToUpdate.setStart(item.getStart());
            itemToUpdate.setEnd(item.getEnd());
            itemToUpdate.setDone(item.isDone());
            todoRepo.save(itemToUpdate);

            todos.set(id, item);
        }

        return "redirect:/";
    }
    @RequestMapping(path = "/delete/", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam("id") int id) {

        log.info("Delete object " + todos.get(id).getText() + "ID: " + id);
        //Slett fra databasen først:
        todoRepo.delete(todos.get(id).getId());
        //Slett fra listen etterpå:
        todos.remove(id);

        return "redirect:/";
    }

    @RequestMapping(path = "/todo/", method = RequestMethod.GET)
    public ModelAndView todo(@RequestParam("id") int id, Model model) {

        model.addAttribute(todos.get(id));
        Todo todo = todos.get(id);
        log.info("Todo start: " + todo.getStart());
        //model.addAttribute(todo);
        return new ModelAndView("todo", "item", model);
    }


    @RequestMapping(path = "/index/json", method = RequestMethod.GET)
    public @ResponseBody List<Todo> json() {

        return todos;
    }

    @RequestMapping(path = "/sort/{idx}", method = RequestMethod.GET)
    public String saveOrder(HttpServletRequest request,
                                  @RequestParam("idx[]") String data) throws Exception {

        updateSortIdx();

        return "redirect:/";
        }

    private void updateSortIdx() {

        for (int i = 0; i < todos.size(); i++) {
            log.info("Updating index for " + todos.get(i).getId());

            todos.get(i).setSortIdx(i);
        }

        todoRepo.save(todos);
    }

}

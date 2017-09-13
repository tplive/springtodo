package no.qvidahl.springtodo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoController {

    @RequestMapping(path="/", method = RequestMethod.GET)
    public ModelAndView listItems() {


    }
}

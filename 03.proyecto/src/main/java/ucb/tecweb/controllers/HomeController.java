package ucb.tecweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String saludo(Model model) {
        model.addAttribute("nombre", "Juan PErez");
        return "home/saludo";
    }
}

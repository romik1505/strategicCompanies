package com.example.demo.controllers;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("title", "Main page");
        return "greeting";
    }
}

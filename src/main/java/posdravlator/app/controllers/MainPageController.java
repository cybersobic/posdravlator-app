package posdravlator.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import posdravlator.app.services.MainPageService;

@Controller
public class MainPageController {
    @Autowired
    private MainPageService mainPageService;

    @GetMapping
    public String getMainPage(Model model) {
        model.addAttribute("title", "Поздравлятор - Главная страница");
        model.addAttribute("localDate", "Сегодня: " + mainPageService.getDate());
        return "main";
    }
}
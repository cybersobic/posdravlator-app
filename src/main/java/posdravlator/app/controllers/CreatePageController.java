package posdravlator.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import posdravlator.app.services.BirthdayService;
import posdravlator.app.services.MainPageService;

@Controller
@RequestMapping("/create")
public class CreatePageController {
    @Autowired
    private BirthdayService birthdayService;

    @Autowired
    private MainPageService mainPageService;

    @GetMapping
    public String getCreatePage(Model model) {
        model.addAttribute("title", "Поздравлятор - Создание записи");
        model.addAttribute("localDate", "Сегодня: " + mainPageService.getDate());
        return "create";
    }
}
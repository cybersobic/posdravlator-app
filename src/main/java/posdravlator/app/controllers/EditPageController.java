package posdravlator.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import posdravlator.app.services.BirthdayService;

@Controller
@RequestMapping("/edit")
public class EditPageController {
    @Autowired
    private BirthdayService birthdayService;

    @GetMapping
    public String getEditPage(Model model) {
        model.addAttribute("title", "Поздравлятор - Редактирование записи");
        return "edit";
    }
}

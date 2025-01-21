package posdravlator.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import posdravlator.app.models.Birthday;
import posdravlator.app.services.BirthdayService;

@Controller
@RequestMapping("/create")
public class CreateController {
    @Autowired
    private BirthdayService birthdayService;

    @GetMapping
    public String getCreatePage(Model model) {
        model.addAttribute("title", "Поздравлятор - Создание записи");
        model.addAttribute("birthday", new Birthday());
        return "create";
    }

    @PostMapping
    public String create(@ModelAttribute Birthday birthday) {
        birthdayService.createBirthday(birthday);
        return "redirect:/login";
    }
}

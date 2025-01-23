package posdravlator.app.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import posdravlator.app.models.Birthday;
import posdravlator.app.services.BirthdayService;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/birthdays")
public class BirthdayDataController {
    @Autowired
    private BirthdayService birthdayService;

    @GetMapping
    public ResponseEntity<List<Birthday>> getAllBirthdays(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) LocalDate date) {
        List<Birthday> birthdays = birthdayService.getAllBirthdays();
        return ResponseEntity.ok(birthdays);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Birthday> getBirthdayById(@PathVariable Long id) {
        Birthday birthday = birthdayService.getBirthdayById(id);
        return ResponseEntity.ok(birthday);
    }

    @PostMapping
    public ResponseEntity<Birthday> createBirthday(@Valid @RequestBody Birthday birthday) {
        Birthday createdBirthday = birthdayService.createBirthday(birthday);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBirthday);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Birthday> updateBirthday(@PathVariable Long id, @Valid @RequestBody Birthday birthdayInfo) {
        Birthday updatedBirthday = birthdayService.updateBirthday(id, birthdayInfo);
        return ResponseEntity.ok(updatedBirthday);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Birthday> deleteBirthday(@PathVariable Long id) {
        birthdayService.deleteBirthday(id);
        return ResponseEntity.noContent().build();
    }
}

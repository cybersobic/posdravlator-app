package posdravlator.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import posdravlator.app.models.Birthday;
import posdravlator.app.services.FeedService;
import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedDataController {
    @Autowired
    private FeedService feedService;

    @GetMapping("/today")
    public ResponseEntity<List<Birthday>> getTodayBirthdays() {
        return ResponseEntity.ok(feedService.getTodayBirthdays());
    }

    @GetMapping("/soon")
    public ResponseEntity<List<Birthday>> getSoonBirthdays() {
        return ResponseEntity.ok(feedService.getSoonBirthdays());
    }

    @GetMapping("/future")
    public ResponseEntity<List<Birthday>> getFutureBirthdays() {
        return ResponseEntity.ok(feedService.getFutureBirthdays());
    }
}

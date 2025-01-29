package posdravlator.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import posdravlator.app.models.Birthday;
import posdravlator.app.services.BirthdayService;
import posdravlator.app.services.ImageService;
import org.springframework.http.HttpStatus;
import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private BirthdayService birthdayService;

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload/{id}")
    public ResponseEntity<String> uploadPhoto(@PathVariable Long id, @RequestParam("photo") MultipartFile image) {
        try {
            String imageId = imageService.uploadImage(image);
            Birthday birthday = birthdayService.getBirthdayById(id);
            birthday.setImage(imageId);
            birthdayService.updateBirthday(id, birthday);

            return ResponseEntity.ok("Фото успешно загружено");
        }
        catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Не удалось загрузить фото.");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long id) throws IOException {
        Birthday birthday = birthdayService.getBirthdayById(id);

        if (birthday.getImage() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        byte[] photoData = imageService.getImage(birthday.getImage());

        if (photoData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        String contentType = imageService.getImageType(birthday.getImage());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(photoData);
    }
}
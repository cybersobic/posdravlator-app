package posdravlator.app.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import posdravlator.app.models.Birthday;
import posdravlator.app.repos.BirthdayRepository;
import java.util.List;

@Service
public class BirthdayService {
    @Autowired
    private BirthdayRepository birthdayRepository;

    public List<Birthday> getAllBirthdays() {
        return birthdayRepository.findAll();
    }

    public Birthday getBirthdayById(Long id) {
        return birthdayRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Запись не найдена :("));
    }

    public Birthday createBirthday(Birthday birthday) {
        return birthdayRepository.save(birthday);
    }

    public Birthday updateBirthday(Long id, Birthday birthdayInfo) {
        Birthday birthday = birthdayRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Запись не найдена :("));

        birthday.setName(birthdayInfo.getName());
        birthday.setDescription(birthdayInfo.getDescription());
        birthday.setDate(birthdayInfo.getDate());

        return birthdayRepository.save(birthday);
    }

    public void deleteBirthday(Long id) {
        Birthday birthday = birthdayRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Запись не найдена :("));
        birthdayRepository.delete(birthday);
    }
}

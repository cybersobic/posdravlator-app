package posdravlator.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import posdravlator.app.models.Birthday;
import posdravlator.app.repos.BirthdayRepository;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedService {
    @Autowired
    private BirthdayRepository birthdayRepository;

    private final int DAYS = 7;

    private List<Birthday> getAllBirthdays() {
        return birthdayRepository.findAll();
    }

    public List<Birthday> getTodayBirthdays() {
        List<Birthday> dates = getAllBirthdays();
        LocalDate today = LocalDate.now();
        String currentMonthDay = String.format("%02d%02d", today.getMonthValue(), today.getDayOfMonth());

        return dates.stream().filter(birthday -> {
            LocalDate birthdayDate = birthday.getDate();

            String birthdayMonthDay = String.format("%02d%02d",
                birthdayDate.getMonthValue(),
                birthdayDate.getDayOfMonth());

            return birthdayMonthDay.equals(currentMonthDay);
        }).collect(Collectors.toList());
    }

    public List<Birthday> getSoonBirthdays() {
        List<Birthday> dates = getAllBirthdays();
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(DAYS);

        return dates.stream()
            .filter(birthday -> {
                LocalDate birthdayDate = birthday.getDate().withYear(today.getYear());
                if (birthdayDate.isBefore(today)) {
                    birthdayDate = birthdayDate.plusYears(1);
                }
                return birthdayDate.isAfter(today) && !birthdayDate.isAfter(nextWeek);
            })
            .sorted(Comparator.comparing(birthday -> {
                LocalDate birthdayDate = birthday.getDate().withYear(today.getYear());
                return birthdayDate.isBefore(today) ? birthdayDate.plusYears(1) : birthdayDate;
            }))
            .collect(Collectors.toList());
    }

    public List<Birthday> getFutureBirthdays() {
        List<Birthday> dates = getAllBirthdays();
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(DAYS);

        return dates.stream()
            .filter(birthday -> {
                LocalDate birthdayDate = birthday.getDate().withYear(today.getYear());

                if (birthdayDate.isBefore(today)) {
                    birthdayDate = birthdayDate.plusYears(1);
                }

                return birthdayDate.isAfter(nextWeek);
            })
            .sorted(Comparator.comparing(birthday -> {
                LocalDate birthdayDate = birthday.getDate().withYear(today.getYear());

                return birthdayDate.isBefore(today) ? birthdayDate.plusYears(1) : birthdayDate;
            }))
            .collect(Collectors.toList());
    }
}
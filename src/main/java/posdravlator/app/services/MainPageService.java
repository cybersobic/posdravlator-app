package posdravlator.app.services;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;

@Service
public class MainPageService {
    public String getDate() {
        LocalDate today = LocalDate.now();

        String[] numbers = String.valueOf(today).split("-");

        return String.format("%s %s %s года", numbers[2], getMonthWord(numbers[1]), numbers[0]);
    }

    public String getMonthWord(String number) {
        HashMap<String, String> monthWords = new HashMap<>();
        monthWords.put("01", "января");
        monthWords.put("02", "февраля");
        monthWords.put("03", "марта");
        monthWords.put("04", "апреля");
        monthWords.put("05", "мая");
        monthWords.put("06", "июня");
        monthWords.put("07", "июля");
        monthWords.put("08", "августа");
        monthWords.put("09", "сентября");
        monthWords.put("10", "октября");
        monthWords.put("11", "ноября");
        monthWords.put("12", "декабря");

        return monthWords.getOrDefault(number, "Некорректный месяц");
    }
}

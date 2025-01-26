package posdravlator.app.services;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class MainPageService {
    public String getDate() {
        LocalDate today = LocalDate.now();
        String[] numbers = String.valueOf(today).split("-");

        return String.format("%s %s %s года", numbers[2], getMonthWord(numbers[1]), numbers[0]);
    }

    public String getMonthWord(String number) {
        return switch (number) {
            case "01" -> "января";
            case "02" -> "февраля";
            case "03" -> "марта";
            case "04" -> "апреля";
            case "05" -> "мая";
            case "06" -> "июня";
            case "07" -> "июля";
            case "08" -> "августа";
            case "09" -> "сентября";
            case "10" -> "октября";
            case "11" -> "ноября";
            case "12" -> "декабря";
            default -> "Некорректный месяц";
        };
    }
}
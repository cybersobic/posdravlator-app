package posdravlator.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import posdravlator.app.models.Birthday;

public interface BirthdayRepository extends JpaRepository<Birthday, Long> { }

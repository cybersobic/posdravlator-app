package posdravlator.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "birthdays")
public class Birthday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя человека не может быть пустым")
    @Size(max = 64, message = "Имя человека не может содержать более 64 символов")
    @Column(name = "name", length = 64)
    private String name;

    @Size(max = 128, message = "Описание не может содержать более 128 символов")
    @Column(name = "description", length = 128)
    private String description;

    @NotNull(message = "Дата рождения не может быть пустой")
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "image")
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
package com.example.stocksystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Имя должно быть заполнено")
    private String name;

    @NotBlank(message = "email должен быть заполнен")
    @Email(message = "Некорректный email")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Укажите дату рождения")
    @Past(message = "Дата рождения должна быть в прошлом")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirthday;


    @NotBlank(message = "Заполните номер телефона")
    @Column(unique = true)
    @Pattern(regexp = "^(\\+7)[\\- ]?(\\d{3})[\\- ]?(\\d{3})[\\- ]?(\\d{2})[\\- ]?(\\d{2})$", message = "Некорректный номер телефона")
    private String phone_number;

    @OneToMany(mappedBy = "client")
   // @NotEmpty
    private List<Order> orders;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}


package com.example.stocksystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "client", uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "phonenumber"})})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Имя должно быть заполнено")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "email должен быть заполнен")
    @Email(message = "Введите email в формате example@mail.com")
    private String email;

    @NotBlank(message = "Укажите дату рождения")
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy" )
    @Pattern(regexp = "^\\d{2}\\.\\d{2}\\.\\d{4}$", message = "Введите дату в формате дд.мм.гггг")
    @Column(name = "dateofbirthday")
    private String dateOfBirthday;


    @NotBlank(message = "Заполните номер телефона")
    @Column(name = "phonenumber")
    @Pattern(regexp = "^(\\+7|8)[\\- ]?(\\d{3})[\\- ]?(\\d{3})[\\- ]?(\\d{2})[\\- ]?(\\d{2})$", message = "Номер телефона должен начнаться с +7 или 8 и содержать не более 12 символов")
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

    public void setDateOfBirthday(String dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}


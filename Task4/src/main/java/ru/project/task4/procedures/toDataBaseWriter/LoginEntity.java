package ru.project.task4.procedures.toDataBaseWriter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.SpringApplication;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "logins", indexes = {
        @Index(name = "IX_access_date", columnList = "access_date"),
        @Index(name = "IX_application", columnList = "application")})
public class LoginEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="access_date")
    @Setter
    private Timestamp accessDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Setter
    private UserEntity user;
    @Column(length = 150) @Nationalized
    @Setter
    private String application;


    @Override
    public String toString()
    {
        return "Login{" +
                "id=" + id +
                ", accessDate=" + accessDate +
                ", user=" + user +
                ", application='" + application + '\'' +
                '}';
    }
}


package ru.project.task4.procedures.toDataBaseWriter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "users", indexes = @Index(name = "IX_username", columnList = "user_name ASC", unique = true))

public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    private Long id;
    @Column(name = "user_name", length = 50) @Nationalized
    @Setter
    private String userName;
    @Column(length = 100) @Nationalized
    @Setter
    private String fio;


    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }
}



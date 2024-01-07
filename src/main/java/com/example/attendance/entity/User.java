package com.example.attendance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.id.IncrementGenerator;

@Getter
@Setter
@Entity
@Table(name = "user", indexes = {
        @Index(name = "UserUuid", columnList = "userUuid", unique = true)
})
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userUid;

    private String userUuid;

    private String id;

    private String pw;
}

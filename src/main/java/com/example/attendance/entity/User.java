package com.example.attendance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.id.IncrementGenerator;

@Getter
@Setter
@Entity
@Builder
@Table(name = "user", indexes = {
        @Index(name = "UserUuid", columnList = "userUuid", unique = true)
})
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userUid;

    private String userUuid;

    private String id;

    private String pw;
}

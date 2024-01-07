package com.example.attendance.entity;

import com.example.attendance.codegen.types.Attendance;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "attendance_log")
@NoArgsConstructor
public class AttendanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendanceLogUid;

    @ManyToOne
    private User user;

    private LocalDate workDate;

    private LocalTime timeStartWork;

    private LocalTime timeEndWork;

    private String url;

    public Attendance transformToAttendance(){
        return new Attendance.Builder()
                .endTime(this.timeEndWork.toString())
                .startTime(this.timeStartWork.toString())
                .url(this.url)
                .result(true)
                .build();
    }
}

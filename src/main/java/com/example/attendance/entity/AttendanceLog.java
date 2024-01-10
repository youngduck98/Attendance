package com.example.attendance.entity;

import com.example.attendance.codegen.types.Attendance;
import com.example.attendance.codegen.types.Result;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Builder
@Table(name = "attendance_log")
@NoArgsConstructor
@AllArgsConstructor
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
                .valid(new Result(true, ""))
                .build();
    }
}

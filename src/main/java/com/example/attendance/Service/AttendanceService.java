package com.example.attendance.Service;

import com.example.attendance.codegen.types.Attendance;
import com.example.attendance.entity.AttendanceLog;
import com.example.attendance.entity.User;
import com.example.attendance.repository.AttendanceLogRepository;
import com.example.attendance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    @Autowired
    AttendanceLogRepository ALR;
    @Autowired
    UserRepository UR;

    public Attendance getAttendanceLog(String userUuid, String date){
        System.out.println(date);
        Optional<User> user = UR.findUserByUserUuid(userUuid);
        if(user.isEmpty()) {
            return new Attendance.Builder().result(false)
                    .reason("failed to find user").build();
        }
        Optional<AttendanceLog> log = ALR.findAttendanceLogByUserAndWorkDate(user.get(), LocalDate.parse(date));
        if(log.isEmpty())
            return new Attendance.Builder().result(false)
                    .reason("failed to find log").build();
        return log.get().transformToAttendance();
    }
}

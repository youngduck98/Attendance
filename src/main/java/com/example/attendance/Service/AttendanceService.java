package com.example.attendance.Service;

import com.example.attendance.codegen.types.Attendance;
import com.example.attendance.codegen.types.Result;
import com.example.attendance.entity.AttendanceLog;
import com.example.attendance.entity.User;
import com.example.attendance.repository.AttendanceLogRepository;
import com.example.attendance.repository.UserRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    @Autowired
    AttendanceLogRepository ALR;
    @Autowired
    UserRepository UR;

    public Attendance getAttendanceLog(String userUuid, String date){
        Optional<User> user = UR.findUserByUserUuid(userUuid);
        if(user.isEmpty()) {
            return new Attendance.Builder().valid(
                    new Result(false, "failed to find user"))
                    .build();
        }
        Optional<AttendanceLog> log = ALR.findAttendanceLogByUserAndWorkDate(user.get(), LocalDate.parse(date));
        if(log.isEmpty())
            return new Attendance.Builder().valid(
                    new Result(false, "failed to find log")
            ).build();
        return log.get().transformToAttendance();
    }

    public Result writeTodayStartLog(String userUuid, String time){
        Optional<User> user = UR.findUserByUserUuid(userUuid);
        if(user.isEmpty())
            return new Result(false, "failed to find user");

        Optional<AttendanceLog> log = ALR.findAttendanceLogByUserAndWorkDate(user.get(), LocalDate.now());

        if(log.isPresent()){
            if(log.get().getTimeStartWork() != null)
                return new Result(false, "already checked");
            try {
                log.get().setTimeStartWork(LocalTime.parse(time));
                ALR.save(log.get());
            }
            catch (PersistenceException e){
                return new Result(false, e.toString());
            }
            return new Result(true, "");
        }

        try {
            ALR.save(AttendanceLog.builder()
                    .user(user.get())
                    .workDate(LocalDate.now())
                    .timeStartWork(LocalTime.parse(time))
                    .build());
        }
        catch (PersistenceException | DateTimeParseException e){
            return new Result(false, e.toString());
        }
        return new Result(true, "");
    }

    public Result updateTodayEndLog(String userUuid, String time){
        Optional<User> user = UR.findUserByUserUuid(userUuid);
        if(user.isEmpty())
            return new Result(false, "failed to find user");

        Optional<AttendanceLog> log = ALR.findAttendanceLogByUserAndWorkDate(user.get(), LocalDate.now());
        if(log.isEmpty())
            new Result(false, "failed to find log");

        try {
            log.get().setTimeEndWork(LocalTime.parse(time));
            ALR.save(log.get());
        }
        catch (PersistenceException | DateTimeParseException e){
            return new Result(false, e.toString());
        }
        return new Result(true, "");
    }

    public Result updateTodayUrl(String userUuid, String date, String url){
        Optional<User> user = UR.findUserByUserUuid(userUuid);
        if(user.isEmpty())
            return new Result(false, "failed to find user");

        try {
            Optional<AttendanceLog> log = ALR.findAttendanceLogByUserAndWorkDate(user.get(), LocalDate.parse(date));
            if (log.isEmpty())
                return new Result(false, "failed to find log");
            log.get().setUrl(url);
            ALR.save(log.get());
        }
        catch (PersistenceException | DateTimeParseException e){
            return new Result(false, e.toString());
        }
        return new Result(true, "");
    }
}

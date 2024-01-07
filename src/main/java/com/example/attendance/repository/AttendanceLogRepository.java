package com.example.attendance.repository;

import com.example.attendance.entity.AttendanceLog;
import com.example.attendance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Integer> {
    Optional<AttendanceLog> findAttendanceLogByUserAndWorkDate(User user, LocalDate workDate);
}

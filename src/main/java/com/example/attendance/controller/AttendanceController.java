package com.example.attendance.controller;

import com.example.attendance.Service.AttendanceService;
import com.example.attendance.codegen.types.Attendance;
import com.example.attendance.queryInput.AttendanceInputProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AttendanceController{
    @Autowired
    AttendanceService AS;
    @QueryMapping
    public Attendance findAttendanceByDateAndUser(@Arguments AttendanceInputProjection attendance){
        return AS.getAttendanceLog(attendance.getUserUid(), attendance.getDate());
    }
}
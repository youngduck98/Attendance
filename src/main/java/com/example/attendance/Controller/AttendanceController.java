package com.example.attendance.Controller;

import com.example.attendance.Service.AttendanceService;
import com.example.attendance.Service.UserService;
import com.example.attendance.codegen.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AttendanceController {
    @Autowired
    AttendanceService AS;
    @Autowired
    UserService US;
    @QueryMapping
    public Attendance findAttendanceByDateAndUser(@Argument String userUuid, @Argument String date){
        return AS.getAttendanceLog(userUuid, date);
    }

    @QueryMapping
    public Token login(@Argument LoginInfo info){
        return US.login(info);
    }

    @MutationMapping
    public Result joinMembership(@Argument MemberInfo info){
        return US.joinMember(info);
    }

    @MutationMapping
    public Result postTodayStartLog(@Argument String userUuid, @Argument String time){
        return AS.writeTodayStartLog(userUuid, time);
    }

    @MutationMapping
    public Result updateTodayEndLog(@Argument String userUuid, @Argument String time){
        return AS.updateTodayEndLog(userUuid, time);
    }

    @MutationMapping
    public Result updateDateUrl(@Argument String userUuid, @Argument String date, @Argument String url){
        return AS.updateTodayUrl(userUuid, date, url);
    }
}

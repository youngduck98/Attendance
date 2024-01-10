package com.example.attendance.Service;

import com.example.attendance.codegen.types.LoginInfo;
import com.example.attendance.codegen.types.MemberInfo;
import com.example.attendance.codegen.types.Result;
import com.example.attendance.codegen.types.Token;
import com.example.attendance.entity.User;
import com.example.attendance.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository UR;

    public Result joinMember(MemberInfo info){
        String uuid = UUID.randomUUID().toString();
        while(UR.findUserByUserUuid(uuid).isPresent())
            uuid = UUID.randomUUID().toString();

        try{
            UR.save(User.builder()
                    .userUuid(uuid)
                    .id(info.getId())
                    .pw(info.getPassword())
                    .build());
        }
        catch (PersistenceException e){
            return new Result(false, e.toString());
        }

        return new Result(true, "");
    }

    public Token login(@NotNull LoginInfo info){
        User user = UR.findUserByIdAndPw(info.getId(), info.getPassword());;
        if(user == null) {
            return Token.newBuilder()
                    .contents("")
                    .valid(new Result(false, "not found user"))
                    .build();
        }

        return  Token.newBuilder()
                .valid(new Result(true, ""))
                .contents(user.getUserUuid())
                .build();
    }
}

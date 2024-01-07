package com.example.attendance.queryInput;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.web.ProjectedPayload;

@ProjectedPayload
public interface AttendanceInputProjection {
    @NotNull
    String getUserUid();
    @NotNull
    String getDate();
}

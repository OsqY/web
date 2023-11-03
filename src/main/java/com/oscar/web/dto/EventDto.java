package com.oscar.web.dto;


import com.oscar.web.models.Club;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {

    private long id;
    @NotEmpty(message = "Event name should be not empty")
    private String eventName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @NotEmpty(message = "Event type should be not empty")
    private String eventType;
    @NotEmpty(message = "Event photo url should be not empty")
    private String photoUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Club club;
}

package com.oscar.web.dto;

import com.oscar.web.models.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClubDto {
    private Long id;
    @Size(max = 255, message = "Club title should be less than 255 characters")
    @NotEmpty(message = "Club title should be not empty")
    private String title;
    @Size(max = 255, message = "Club photo url should be less than 255 characters")
    @NotEmpty(message = "Club photo url should be not empty")
    private String photoUrl;
    @Size(max = 255, message = "Club content should be less than 255 characters")
    @NotEmpty(message = "Club content should be not empty")
    private String content;
    private UserEntity createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<EventDto> events;
}

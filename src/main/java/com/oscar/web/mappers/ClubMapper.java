package com.oscar.web.mappers;

import com.oscar.web.dto.ClubDto;
import com.oscar.web.models.Club;

import java.util.stream.Collectors;

public class ClubMapper {
    public static Club mapToClub(ClubDto clubDto) {
        return Club
                .builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .content(clubDto.getContent())
                .createdBy(clubDto.getCreatedBy())
                .createdAt(clubDto.getCreatedAt())
                .updatedAt(clubDto.getUpdatedAt())
                .build();
    }

    public static ClubDto mapToClubDto(Club club) {
        return ClubDto
                .builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdBy(club.getCreatedBy())
                .createdAt(club.getCreatedAt())
                .updatedAt(club.getUpdatedAt())
                .events(club.getEvents().stream()
                        .map(EventMapper::mapToEventDto)
                        .collect(Collectors.toList()))
                .build();
    }
}

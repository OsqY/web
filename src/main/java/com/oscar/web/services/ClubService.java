package com.oscar.web.services;

import com.oscar.web.dto.ClubDto;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    void saveClub(ClubDto clubDto);

    ClubDto findClubById(long clubId);

    void updateClub(ClubDto clubDto);

    void deleteClubById(long clubId);

    List<ClubDto> searchClubs(String query);
}

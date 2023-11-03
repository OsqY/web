package com.oscar.web.services.impl;

import com.oscar.web.dto.ClubDto;
import com.oscar.web.mappers.ClubMapper;
import com.oscar.web.models.Club;
import com.oscar.web.models.UserEntity;
import com.oscar.web.repositories.ClubRepository;
import com.oscar.web.repositories.UserRepository;
import com.oscar.web.security.SecurityUtil;
import com.oscar.web.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.oscar.web.mappers.ClubMapper.mapToClub;
import static com.oscar.web.mappers.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    @Autowired
    private ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }

    @Override
    public void saveClub(ClubDto clubDto) {
        String userEmail = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findFirstByEmail(userEmail);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        String userEmail = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findFirstByEmail(userEmail);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void deleteClubById(long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }

}

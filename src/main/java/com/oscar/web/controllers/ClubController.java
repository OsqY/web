package com.oscar.web.controllers;

import com.oscar.web.dto.ClubDto;
import com.oscar.web.models.Club;
import com.oscar.web.models.UserEntity;
import com.oscar.web.security.SecurityUtil;
import com.oscar.web.services.ClubService;
import com.oscar.web.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private final ClubService clubService;

    private final UserService userService;

    @Autowired
    public ClubController(ClubService clubService, UserService userService) {
        this.userService = userService;
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String getClubs(Model model) {
        UserEntity user = new UserEntity();
        List<ClubDto> clubs = clubService.findAllClubs();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByEmail(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId, Model model) {
        UserEntity user = new UserEntity();
        String userEmail = SecurityUtil.getSessionUser();
        if (userEmail != null) {
            user = userService.findByEmail(userEmail);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("clubId", clubId);
        model.addAttribute("club", clubService.findClubById(clubId));
        return "clubs-detail";
    }
    @GetMapping("/clubs/new")
    public String createClub(Model model) {
            Club club = new Club();
            model.addAttribute("club", club);
            return "clubs-create";
        }

        @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") long clubId) {
            clubService.deleteClubById(clubId);
            return "redirect:/clubs";
        }

        @GetMapping("/clubs/search")
    public String searchClubs(@RequestParam("query") String query, Model model) {
            UserEntity user = new UserEntity();
            List<ClubDto> clubs = clubService.searchClubs(query);
            String username = SecurityUtil.getSessionUser();
            if (username != null) {
                user = userService.findByEmail(username);
                model.addAttribute("user", user);
            }
            model.addAttribute("user", user);
            model.addAttribute("clubs", clubs);
            return "clubs-list";
        }

        @PostMapping("/clubs/new")
        public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result,
                               Model model) {
        String userEmail = SecurityUtil.getSessionUser();
        UserEntity user = userService.findByEmail(userEmail);
        if (user == null) {
            return "redirect:/clubs?fail";
        }
        if (result.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }
            clubService.saveClub(clubDto);
            return "redirect:/clubs";
        }

        @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId, Model model) {
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
        }

        @PostMapping("/clubs/{clubId}/edit")
        public String editClub(@PathVariable("clubId") long clubId, @Valid @ModelAttribute("club") ClubDto clubDto,
                               BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("club", clubDto);
            return "clubs-edit";
        }
            clubDto.setId(clubId);
            clubService.updateClub(clubDto);
            return "redirect:/clubs";
        }
    }

package com.st.authlight.controller;

import com.st.authlight.dto.LoginDTO;
import com.st.authlight.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping(path = "/login")
    public @ResponseBody com.st.authlight.data.User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        User user = (principal instanceof User)
                ? (User) principal
                : null;

        return Objects.nonNull(user)
                ? userService.getByLogin(user.getUsername())
                : null;
    }
}

package com.st.authlight.repository;

import com.st.authlight.data.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private List<User> users;

    public UserRepository() {
        var u1 = new User();
        u1.setId(0L);
        u1.setRole("ROLE_ADMINISTRATOR");
        u1.setLogin("anton");
        u1.setPassword("1234");
        u1.setPasswordExpired(null);

        var u2 = new User();
        u2.setId(1L);
        u2.setRole("ROLE_ADMINISTRATOR");
        u2.setLogin("ivan");
        u2.setPassword("12345");
        u2.setPasswordExpired(null);

        var u3 = new User();
        u3.setId(10011225L);
        u3.setRole("ADMINISTRATOR");
        u3.setLogin("olazokchuk@bo.dev");
        u3.setPassword("lHCL3ZWcZPNKol6lQXDtPw==");
        u3.setPasswordExpired(null);

        this.users = List.of(u1, u2, u3);
    }

    public User findByLoginIgnoreCase(String login) {
        return this.users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst()
                .orElse(null);
    }

    public List<User> findAll() {
        return this.users;
    }
}

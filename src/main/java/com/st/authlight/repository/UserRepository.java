package com.st.authlight.repository;

import com.st.authlight.data.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private List<User> users;

    public UserRepository() {
        this.users = List.of(
                new User(0L, "ROLE_ADMINISTRATOR", "anton", "1234", false),
                new User(1L, "ROLE_TRAIDER", "ivan", "12345", false));
    }

    public User getByLogin(String login) {
        return this.users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst()
                .orElse(null);
    }

    public List<User> getAll() {
        return this.users;
    }
}

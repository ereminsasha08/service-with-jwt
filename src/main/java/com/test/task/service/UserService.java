package com.test.task.service;

import com.test.task.domain.Role;
import com.test.task.domain.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final List<User> users;

    public UserService() {
        this.users = List.of(
                new User("user", "1234", "Иван", "Иванов", Collections.singleton(Role.USER)),
                new User("admin", "12345", "Сергей", "Петров", Collections.singleton(Role.ADMIN)),
                new User("1", "1", "1", "1", Collections.singleton(Role.UNAUTHORIZED))

        );
    }

    public Optional<User> getByLogin(@NonNull String login) {
        return users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }

}
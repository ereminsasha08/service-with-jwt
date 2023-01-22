package com.test.task.service;

import com.test.task.domain.auth.User;
import lombok.NonNull;

import java.util.Optional;

public interface UserService {

    Optional<User> getByLogin(@NonNull String login);

}

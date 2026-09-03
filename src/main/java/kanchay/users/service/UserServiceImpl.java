package kanchay.users.service;

import kanchay.users.dto.CreateUserRequest;
import kanchay.users.dto.LoginRequest;
import kanchay.users.dto.UserResponse;

public interface UserService {
    UserResponse register(CreateUserRequest request);
    UserResponse login(LoginRequest request);
}
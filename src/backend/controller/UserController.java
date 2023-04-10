package backend.controller;

import backend.model.User;
import backend.payload.UserRecord;

import java.util.List;

public interface UserController {
    UserRecord add(User user);

    List<UserRecord> getAllUsers();

    boolean checkPassword(String id, String password);
}

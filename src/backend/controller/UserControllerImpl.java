package backend.controller;

import backend.database.DataBase;
import backend.model.User;
import backend.payload.UserRecord;

import java.util.ArrayList;
import java.util.List;

public class UserControllerImpl implements UserController{
    @Override
    public UserRecord add(User user) {
        // todo checking
        DataBase.userList.add(user);
        return new UserRecord(user.getId(), user.getFirsname(), user.getUsername());
    }

    @Override
    public List<UserRecord> getAllUsers() {
        ArrayList<UserRecord> userRecords = new ArrayList<>();
        for (User user : DataBase.userList) {
            userRecords.add(new UserRecord(user.getId(), user.getFirsname(), user.getUsername()));
        }
        return userRecords;
    }

    @Override
    public boolean checkPassword(String id, String password) {
        User user = DataBase.findUserById(id);
        if (user == null){
            return false;
        }
        if (user.getPassword().equals(password)){
            return true;
        }
        return false;
    }
}

package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    User getUserById(int id);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}


package web.Service;

import web.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    void saveUser(User user);
    void updateUser(int id, User updatedUser);
    void deleteUser(int id);
}

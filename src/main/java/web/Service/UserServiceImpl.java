package web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.models.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.index();
    }

    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return userDao.show(id);
    }

    @Transactional
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Transactional
    public void updateUser(int id, User updatedUser) {
        userDao.update(id, updatedUser);
    }

    @Transactional
    public void deleteUser(int id) {
        userDao.delete(id);
    }
}

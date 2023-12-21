package web.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> index() {
        return entityManager.createQuery("select user from User user", User.class)
                .getResultList();

    }

    public User show(int id) {
        return entityManager.find(User.class, id);
    }


    public void save(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }


    public void update(int id, User updatedUser) {
        User userToBeUpdated = entityManager.find(User.class, id);
        if (userToBeUpdated != null) {
            userToBeUpdated.setName(updatedUser.getName());
            userToBeUpdated.setAge(updatedUser.getAge());
        }
    }


    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}

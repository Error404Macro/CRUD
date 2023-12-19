package web.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDAO {


    //private final SessionFactory sessionFactory;
    @PersistenceContext
    private EntityManager entityManager;

    /*@Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }*/

    @Transactional(readOnly = true)
    public List<User> index() {
        return entityManager.createQuery("select user from User user", User.class)
                .getResultList();

    }

    @Transactional(readOnly = true)
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        User userToBeUpdated = entityManager.find(User.class, id);
        if (userToBeUpdated != null) {
            userToBeUpdated.setName(updatedUser.getName());
            userToBeUpdated.setAge(updatedUser.getAge());
        }
    }

    @Transactional
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}

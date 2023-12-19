package web.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.models.User;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
public class UserDAO {


    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<User> index() {
        Session session = sessionFactory.getCurrentSession();

        List<User> users = session.createQuery("select user from User user", User.class)
                .getResultList();

        return users;
    }

    public User show(int id) {
        return null;
    }

    public void save(User user) {

    }

    public void update(int id, User updatedUser) {

    }

    public void delete(int id) {

    }
}

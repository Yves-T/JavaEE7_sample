package services;

import models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * UserService: Yves-T
 * Date: 10/11/13
 * Time: 16:54
 */
@Stateless
public class UserService {
    @PersistenceContext
    private EntityManager em;

    public List<User> getAll() {
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        return query.getResultList();
    }
}

/*
package no.hvl.PollApp.repos;

import jakarta.persistence.EntityManager;
import no.hvl.PollApp.entities.User;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class JpaUserRepository {

    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    public Optional<User> findUserByEmail(String email) {
        if (email == null) return Optional.empty();
        String norm = email.toLowerCase(Locale.ROOT).trim();
        return em.createQuery(
                        "select u from User u where u.email = :email", User.class)
                .setParameter("email", norm)
                .getResultStream()
                .findFirst();
    }

    public Optional<User> findUserByUsername(String username) {
        if (username == null) return Optional.empty();
        return em.createQuery(
                        "select u from User u where u.username = :un", User.class)
                .setParameter("un", username)
                .getResultStream()
                .findFirst();
    }

    public List<User> findAllUsers() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    public User saveUser(User user) {
        if (user.getId() == null) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    public boolean deleteUserById(Long id) {
        User u = em.find(User.class, id); // PK lookup
        if (u == null) return false;
        em.remove(u);
        return true;
    }
}

*/

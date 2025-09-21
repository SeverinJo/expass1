/*
package no.hvl.PollApp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import no.hvl.PollApp.entities.Poll;
import no.hvl.PollApp.entities.User;
import no.hvl.PollApp.repos.JpaPollRepository;
import no.hvl.PollApp.repos.JpaUserRepository;
import no.hvl.PollApp.repos.JpaVoteRepository;

import java.util.List;
import java.util.Optional;

public class PollService {
    private final EntityManagerFactory emf;
    public PollService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public User createUser(String username, String email) {
        return emf.callInTransaction(em -> {
            JpaUserRepository users = new JpaUserRepository(em);
            users.findUserByEmail(email).ifPresent(u -> { throw new IllegalArgumentException("email in use"); });
            return users.saveUser(new User(username, email));
        });
    }

    public void deleteUser(long id) {
        emf.runInTransaction(em -> {
            new JpaUserRepository(em).deleteUserById(id);
        });
    }

    public Poll createPoll(String question) {
        return emf.callInTransaction(em -> {
            JpaPollRepository polls = new JpaPollRepository(em);
            JpaUserRepository users = new JpaUserRepository(em);
            User createdBy = users.findUserById()

        })
    }

}
*/

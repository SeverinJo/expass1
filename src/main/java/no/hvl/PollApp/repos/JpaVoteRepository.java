/*
package no.hvl.PollApp.repos;

import jakarta.persistence.EntityManager;
import no.hvl.PollApp.entities.Vote;

import java.util.List;
import java.util.Optional;

public class JpaVoteRepository {
    EntityManager em;
    public JpaVoteRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<Vote> findVoteById(Long id) {
        return Optional.ofNullable(em.find(Vote.class, id));
    }

    public List<Vote> findAllVotes() {
        return em.createQuery("from Vote", Vote.class).getResultList();
    }

    public Vote saveVote(Vote vote) {
        if (vote.getId() == null) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    public boolean deleteVoteById(Long id) {
        Vote v = em.find(Vote.class, id);
        if (v == null) return false;
        em.remove(v);
        return true;
    }

}
*/

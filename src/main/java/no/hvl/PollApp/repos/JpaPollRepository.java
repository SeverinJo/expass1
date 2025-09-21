/*
package no.hvl.PollApp.repos;

import jakarta.persistence.EntityManager;
import no.hvl.PollApp.entities.Poll;

import java.util.List;
import java.util.Optional;

public class JpaPollRepository {
    private final EntityManager em;
    public JpaPollRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<Poll> findPollById(Long id) {
        return Optional.ofNullable(em.find(Poll.class, id));
    }

    public List<Poll> findAllPolls() {
        return em.createQuery("from Poll", Poll.class).getResultList();
    }

    public Poll savePoll(Poll poll) {
        if (poll.getId() == null) {
            em.persist(poll);
            return poll;
        } else {
            return em.merge(poll);
        }
    }

    public boolean deletePollById(Long id) {
        Poll p = em.find(Poll.class, id);
        if (p == null) return false;
        em.remove(p);
        return true;
    }
}
*/

package pl.altkomsoftware.eventsdemo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.altkomsoftware.eventsdemo.utils.Status;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findFirstByUserEmail(String userEmail);
    List<User> findFirstByUserEmailAndStatus(String userEmail, Status status);
    List<User> findByStatus(Status status);
}

package ac.su.springmvcinclass.repository;

import ac.su.springmvcinclass.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// User 와 그 키의 타입(long)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

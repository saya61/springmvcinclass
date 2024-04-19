package ac.su.springmvcinclass.repository;

import ac.su.springmvcinclass.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// User 와 그 키의 타입(long)
@Repository     // DAO 를 다루며 영속성을 관리 : DB접근
public interface UserRepository extends JpaRepository<User, Long> {

}

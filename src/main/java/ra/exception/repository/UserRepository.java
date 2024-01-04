package ra.exception.repository;

import ra.exception.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    Boolean existsByUserName(String username);
}

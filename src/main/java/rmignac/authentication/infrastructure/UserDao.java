package rmignac.authentication.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<UserEntity, UUID> {

    UserEntity findByUsername(String username);

    @Query(nativeQuery = true, value = "select authority from authorities a where a.id=?")
    List<String> findAuthorityById(UUID id);
}

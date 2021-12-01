package rmignac.owner.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OwnerDAO extends JpaRepository<OwnerEntity, UUID> {
}

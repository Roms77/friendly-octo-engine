package rmignac.repotting.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepottingDAO extends JpaRepository<RepottingEntity, UUID> {
}

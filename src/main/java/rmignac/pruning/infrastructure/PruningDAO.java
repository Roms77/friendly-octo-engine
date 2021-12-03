package rmignac.pruning.infrastructure;

import org.hibernate.annotations.SortComparator;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PruningDAO extends JpaRepository<PruningEntity, UUID> {

}

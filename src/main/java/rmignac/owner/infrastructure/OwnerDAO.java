package rmignac.owner.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OwnerDAO extends JpaRepository<OwnerEntity, UUID> {

    @Query("SELECT o FROM owner o, bonsai b WHERE (:hasMore=0 OR b.ownerEntity = o.id) group by o having count(b)>:hasMore")
    List<OwnerEntity> findAllHasMore(@Param("hasMore") int hasMore);

    @Query("SELECT o FROM owner o, bonsai b WHERE b.ownerEntity = o.id and b.id=:id")
    List<OwnerEntity> findOwnerByBonsaiID(@Param("id") UUID id);
}

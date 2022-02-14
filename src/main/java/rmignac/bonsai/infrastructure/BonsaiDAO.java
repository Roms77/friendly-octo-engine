package rmignac.bonsai.infrastructure;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rmignac.bonsai.domain.Status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BonsaiDAO extends JpaRepository<BonsaiEntity, UUID> {



    @Query("SELECT b FROM bonsai b WHERE b.status = :status and b.acquisition_age >= :older_than")
    List<BonsaiEntity> findAllWithFilter(@Param("status") Status status, @Param("older_than") int older_than, Sort sort);

    @Query("SELECT b FROM bonsai b WHERE b.acquisition_age >= :older_than")
    List<BonsaiEntity> findAllWithFilterWithoutStatus(@Param("older_than") int older_than, Sort sort);

    Optional<BonsaiEntity> findByNom(@Param("nom") String nom);

}

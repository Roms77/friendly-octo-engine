package rmignac.owner.infrastructure;

import org.springframework.stereotype.Component;
import rmignac.bonsai.domain.Bonsai;
import rmignac.owner.OwnerMapper;
import rmignac.owner.domain.Owner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OwnerRepository {

    private OwnerDAO ownerDAO;

    public OwnerRepository(OwnerDAO ownerDAO){
        this.ownerDAO=ownerDAO;
    }

    public List<Owner> getOwners(int hasMore){
        //return ownerDAO.findAll().stream().map(OwnerMapper::ownerEntityToOwner).collect(Collectors.toList());
        return ownerDAO.findAllHasMore(hasMore).stream().map(OwnerMapper::ownerEntityToOwner).collect(Collectors.toList());
    }

    public Owner create(Owner owner){
        return OwnerMapper.ownerEntityToOwner(ownerDAO.save(OwnerMapper.ownerToOwnerEntity(owner)));
    }

    public Optional<Owner> findById(UUID id){
        return ownerDAO.findById(id).map(OwnerMapper::ownerEntityToOwner);
    }

    public Owner update(Owner owner){
        return OwnerMapper.ownerEntityToOwner(ownerDAO.save(OwnerMapper.ownerToOwnerEntity(owner)));
    }

    public boolean haveOwner(UUID bonsaiId){
        return !ownerDAO.findOwnerByBonsaiID(bonsaiId).isEmpty();
    }
}

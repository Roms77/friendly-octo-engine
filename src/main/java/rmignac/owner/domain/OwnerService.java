package rmignac.owner.domain;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rmignac.authentication.domain.AppUser;
import rmignac.bonsai.domain.Bonsai;
import rmignac.bonsai.infrastructure.BonsaiRepository;
import rmignac.owner.exceptions.BonsaiNotExistException;
import rmignac.owner.exceptions.OwnerNotExistException;
import rmignac.owner.exceptions.UnautorizedException;
import rmignac.owner.infrastructure.OwnerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OwnerService {

    private OwnerRepository ownerRepository;
    private BonsaiRepository bonsaiRepository;
    public OwnerService(OwnerRepository ownerRepository, BonsaiRepository bonsaiRepository){
        this.ownerRepository=ownerRepository;
        this.bonsaiRepository=bonsaiRepository;
    }

    public List<Owner> getOwners(int hasMore){
        return ownerRepository.getOwners(hasMore);
    }

    public Owner create(Owner owner){
        if(owner.getName()==null)  return null;

        return ownerRepository.create(owner);
    }

    public Optional<Owner> findById(UUID id){
        return ownerRepository.findById(id);
    }

    public Optional<List<BonsaiSimplifie>> getBonsaisByOwnerID(UUID id){

        if(!ownerRepository.findById(id).isPresent()) return Optional.empty();

        return ownerRepository.findById(id).map(Owner::getBonsais);
    }

    public Bonsai transferBonsai(UUID currentOwnerId, UUID bonsaiId, UUID newOwnerId) throws OwnerNotExistException, UnautorizedException, BonsaiNotExistException{
        Optional<Owner> optNewOwner = ownerRepository.findById(newOwnerId);
        Optional<Owner> optCurrentOwner = ownerRepository.findById(currentOwnerId);
        Optional<Bonsai> optBonsai;
        Bonsai bonsai;
        Owner newOwner;
        Owner currentOwner;
        AppUser credentials = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if(!(optNewOwner.isPresent() && optCurrentOwner.isPresent())) throw new OwnerNotExistException();

        newOwner = optNewOwner.get();
        currentOwner = optCurrentOwner.get();

        optBonsai = bonsaiRepository.findByID(bonsaiId);
        if(!optBonsai.isPresent()) throw new BonsaiNotExistException();

        bonsai = optBonsai.get();

        if((!bonsai.getOwner().equals(currentOwner)) || (credentials !=null && !credentials.getAuthorities().contains("ADMIN"))) throw new UnautorizedException();

        bonsai.setOwner(newOwner);

        return bonsaiRepository.update(bonsai);
    }


    public Bonsai addBonsai(UUID newOwnerId, UUID bonsaiId) throws OwnerNotExistException, UnautorizedException, BonsaiNotExistException{
        Optional<Owner> optOwner = ownerRepository.findById(newOwnerId);
        AppUser credentials = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Bonsai> optBonsai;
        Owner owner;
        Bonsai bonsai;

        if(!optOwner.isPresent()) throw new OwnerNotExistException();

        owner= optOwner.get();

        optBonsai = bonsaiRepository.findByID(bonsaiId);
        if(!optBonsai.isPresent()) throw new BonsaiNotExistException();

        bonsai = optBonsai.get();

        if((bonsai.getOwner()!=null) || (credentials !=null && !credentials.getAuthorities().contains("ADMIN"))) throw new UnautorizedException();

        bonsai.setOwner(owner);

        return bonsaiRepository.update(bonsai);
    }

    public void deleteOwner(UUID ownerId) {
        Optional<Owner> owner = ownerRepository.findById(ownerId);
        if (owner.isPresent()) ownerRepository.delete(owner.get());
    }
}

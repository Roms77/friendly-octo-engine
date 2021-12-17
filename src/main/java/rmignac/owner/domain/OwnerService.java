package rmignac.owner.domain;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import rmignac.bonsai.domain.Bonsai;
import rmignac.owner.OwnerMapper;
import rmignac.owner.exceptions.OwnerNotExistException;
import rmignac.owner.exceptions.UnautorizedException;
import rmignac.owner.infrastructure.OwnerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OwnerService {

    private OwnerRepository ownerRepository;
    public OwnerService(OwnerRepository ownerRepository){
        this.ownerRepository=ownerRepository;
    }

    public List<Owner> getOwners(int hasMore){

        List<Owner> owners = ownerRepository.getOwners(hasMore);

        return owners;
    }

    public Owner create(Owner owner){
        if(owner.getName()==null){
            return null;
        }
        return ownerRepository.create(owner);
    }

    public Optional<Owner> findById(UUID id){
        return ownerRepository.findById(id);
    }

    public Optional<List<Bonsai>> getBonsaisByOwnerID(UUID id){

        if(!ownerRepository.findById(id).isPresent()){
            return null;
        }
        return ownerRepository.findById(id).map(Owner::getBonsais);
    }

    public Bonsai transferBonsai(UUID currentOwnerId, UUID bonsaiId, UUID newOwnerID) throws OwnerNotExistException, UnautorizedException{
        Optional<Owner> currentOwner = ownerRepository.findById(currentOwnerId);
        if(!currentOwner.isPresent()){
             throw new OwnerNotExistException();
        }
        Bonsai bonsai = currentOwner.get().getBonsais().stream().filter(myBonsai -> myBonsai.getId()==bonsaiId).findFirst().orElse(null);
        if(bonsai==null){
            throw new UnautorizedException();
        }
        Optional<Owner> newOwner = ownerRepository.findById(currentOwnerId);
        if(!newOwner.isPresent()){
            throw new OwnerNotExistException();
        }
        List<Bonsai> bonsaisNewOwner = newOwner.get().getBonsais();
        List<Bonsai> bonsaisCurrentOwner = newOwner.get().getBonsais();
        bonsaisNewOwner.add(bonsai);
        newOwner.get().setBonsais(bonsaisNewOwner);
        bonsaisCurrentOwner.remove(bonsai);
        currentOwner.get().setBonsais(bonsaisCurrentOwner);
        ownerRepository.update(newOwner.get());
        ownerRepository.update(currentOwner.get());

        return bonsai;
    }


    public Bonsai addBonsai(UUID currentOwnerId, UUID bonsaiId, UUID newOwnerID) throws OwnerNotExistException, UnautorizedException{
        Optional<Owner> currentOwner = ownerRepository.findById(currentOwnerId);
        if(!currentOwner.isPresent()){
            throw new OwnerNotExistException();
        }
        Bonsai bonsai = currentOwner.get().getBonsais().stream().filter(myBonsai -> myBonsai.getId()==bonsaiId).findFirst().orElse(null);
        if(bonsai==null){
            throw new UnautorizedException();
        }

        List<Bonsai> bonsaisNewOwner = newOwner.get().getBonsais();
        List<Bonsai> bonsaisCurrentOwner = newOwner.get().getBonsais();
        bonsaisNewOwner.add(bonsai);
        newOwner.get().setBonsais(bonsaisNewOwner);
        bonsaisCurrentOwner.remove(bonsai);
        currentOwner.get().setBonsais(bonsaisCurrentOwner);
        ownerRepository.update(newOwner.get());
        ownerRepository.update(currentOwner.get());

        return bonsai;
    }
}

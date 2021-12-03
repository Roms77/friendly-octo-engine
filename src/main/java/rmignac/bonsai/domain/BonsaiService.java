package rmignac.bonsai.domain;

import org.springframework.stereotype.Service;
import rmignac.bonsai.infrastructure.BonsaiRepository;
import rmignac.pruning.domain.Pruning;
import rmignac.repotting.domain.Repotting;
import rmignac.watering.domain.Watering;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BonsaiService {

    private BonsaiRepository bonsaiRepository;
    public BonsaiService(BonsaiRepository bonsaiRepository){
        this.bonsaiRepository=bonsaiRepository;
    }

    public Bonsai save(Bonsai bonsai){
        return bonsaiRepository.save(bonsai);
    }

    public Bonsai update(Bonsai bonsai){
        return bonsaiRepository.update(bonsai);
    }

    public Optional<Bonsai> findById(UUID id){
        return bonsaiRepository.findByID(id);
    }

    public List<Bonsai> findAll(){
        return bonsaiRepository.findAll();
    }

    public Optional<Bonsai> findByName(String nom){
        return bonsaiRepository.findByName(nom);
    }

    public void delete(UUID id){
        bonsaiRepository.delete(id);
    }

    public boolean existsById(UUID id){
        return bonsaiRepository.existsById(id);
    }

    public List<Pruning> getAllPruning(UUID id){
        return bonsaiRepository.getAllPruning(id);
    }
    public List<Watering> getAllWatering(UUID id){
        return bonsaiRepository.getAllWatering(id);
    }
    public List<Repotting> getAllRepotting(UUID id){
        return bonsaiRepository.getAllRepotting(id);
    }
}

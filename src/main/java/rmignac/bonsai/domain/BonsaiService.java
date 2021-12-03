package rmignac.bonsai.domain;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rmignac.bonsai.BonsaiMapper;
import rmignac.bonsai.exposition.BonsaiDTO;
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


    public Bonsai changeStatus(UUID id, Status status){

        Optional<Bonsai> optBonsai = findById(id);
        if(!optBonsai.isPresent()){
            return null;
        }else {
            optBonsai.get().setStatus(status);
            return bonsaiRepository.save(optBonsai.get());
        }
    }
    public Bonsai save(Bonsai bonsai){
        if(bonsai.getNom() == null){
            return null;
        }
        return bonsaiRepository.save(bonsai);
    }

    public Bonsai update(Bonsai bonsai){
        Bonsai bonsaiUpdate = new Bonsai();
        Optional<Bonsai> optBonsai = findById(bonsai.getId());
        if(!optBonsai.isPresent()){
            return null;
        }else {
            bonsaiUpdate.setId(optBonsai.get().getId());
            bonsaiUpdate.setNom(optBonsai.get().getNom());
            bonsaiUpdate.setSpecies(optBonsai.get().getSpecies());
            bonsaiUpdate.setAcquisition_age(optBonsai.get().getAcquisition_age());
            bonsaiUpdate.setAcquisition_date(optBonsai.get().getAcquisition_date());
        }
        return bonsaiRepository.update(bonsai);
    }
    public Optional<Bonsai> findByIdOrName(String id){
        Optional<Bonsai> optBonsai;
        try{
            optBonsai = findById(UUID.fromString(id));
            if(optBonsai.isPresent()){
                return optBonsai;
            }else{
                optBonsai = findByName(id);
                if(optBonsai.isPresent()){
                    return optBonsai;
                }
            }
        }catch (IllegalArgumentException e){
        }
        return findByName(id);
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

    public boolean delete(UUID id){
        if(!bonsaiRepository.existsById(id)){
            return false;
        }else{
            bonsaiRepository.delete(id);
        }
        bonsaiRepository.delete(id);
        return true;
    }

    public boolean existsById(UUID id){
        return bonsaiRepository.existsById(id);
    }

    public List<Pruning> getAllPruning(UUID id){
        if(!existsById(id)){
            return null;
        }
        return bonsaiRepository.getAllPruning(id);
    }
    public List<Watering> getAllWatering(UUID id){
        if(!existsById(id)){
            return null;
        }
        return bonsaiRepository.getAllWatering(id);
    }
    public List<Repotting> getAllRepotting(UUID id){
        if(!existsById(id)){
            return null;
        }
        return bonsaiRepository.getAllRepotting(id);
    }

    public List<Bonsai> findAllWithFilter(String status, int older_than, String sort, String direction){
        Status stat;
        if(status==null){
            stat=null;
        }else{
            try{
                stat = Status.valueOf(status);
            }catch (IllegalArgumentException e){
                stat=null;
            }
        }

        return bonsaiRepository.findAllWithFilter(stat, older_than, sort, direction);
    }
}

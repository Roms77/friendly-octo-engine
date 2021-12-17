package rmignac.bonsai.domain;

import org.springframework.stereotype.Service;
import rmignac.bonsai.infrastructure.BonsaiRepository;

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

    public Bonsai update(UUID id, Bonsai bonsai){
        Optional<Bonsai> optBonsai = findById(id);
        if(!optBonsai.isPresent()){
            return null;
        }else {
            if(bonsai.getNom()!=null)
            optBonsai.get().setNom(bonsai.getNom());
            if(bonsai.getSpecies()!=null)
            optBonsai.get().setSpecies(bonsai.getSpecies());
            if(bonsai.getAcquisition_age()!=0)
            optBonsai.get().setAcquisition_age(bonsai.getAcquisition_age());
            if(bonsai.getAcquisition_date()!=null)
            optBonsai.get().setAcquisition_date(bonsai.getAcquisition_date());
        }
        return bonsaiRepository.update(optBonsai.get());
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

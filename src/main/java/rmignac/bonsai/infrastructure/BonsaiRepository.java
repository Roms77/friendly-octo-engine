package rmignac.bonsai.infrastructure;

import org.springframework.stereotype.Component;
import rmignac.bonsai.domain.Bonsai;
import rmignac.pruning.domain.Pruning;
import rmignac.pruning.infrastructure.PruningRepository;
import rmignac.repotting.domain.Repotting;
import rmignac.repotting.infrastructure.RepottingRepository;
import rmignac.watering.domain.Watering;
import rmignac.watering.infrastructure.WateringRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BonsaiRepository {

    private BonsaiDAO bonsaiDao;

    public BonsaiRepository(BonsaiDAO bonsaiDao){
        this.bonsaiDao=bonsaiDao;
    }

    public Bonsai bonsaiEntityToBonsai(BonsaiEntity bonsaiEntity) {
        Bonsai bonsai = new Bonsai();
        bonsai.setId(bonsaiEntity.getId());
        bonsai.setNom(bonsaiEntity.getNom());
        bonsai.setAcquisition_date(bonsaiEntity.getAcquisition_date());
        bonsai.setAcquisition_age(bonsaiEntity.getAcquisition_age());
        bonsai.setSpecies(bonsaiEntity.getSpecies());
        bonsai.setStatus(bonsaiEntity.getStatus());
        bonsai.setPruning(bonsaiEntity.getPruning().stream().map(PruningRepository::PruningEntityToPruning).collect(Collectors.toList()));
        bonsai.setWatering(bonsaiEntity.getWatering().stream().map(WateringRepository::WateringEntityToWatering).collect(Collectors.toList()));
        bonsai.setRepotting(bonsaiEntity.getRepotting().stream().map(RepottingRepository::RepottingEntityToRepotting).collect(Collectors.toList()));
        return bonsai;
    }
    public BonsaiEntity bonsaiToBonsaiEntity(Bonsai bonsai){
        BonsaiEntity bonsaiEntity = new BonsaiEntity();
        bonsaiEntity.setId(bonsai.getId());
        bonsaiEntity.setNom(bonsai.getNom());
        bonsaiEntity.setAcquisition_date(bonsai.getAcquisition_date());
        bonsaiEntity.setAcquisition_age(bonsai.getAcquisition_age());
        bonsaiEntity.setSpecies(bonsai.getSpecies());
        bonsaiEntity.setStatus(bonsai.getStatus());
//        bonsaiEntity.setPruning(bonsai.getPruning().stream().map(PruningRepository::PruningToPruningEntity).collect(Collectors.toList()));
//        bonsaiEntity.setWatering(bonsai.getWatering().stream().map(WateringRepository::WateringToWateringEntity).collect(Collectors.toList()));
//        bonsaiEntity.setRepotting(bonsai.getRepotting().stream().map(RepottingRepository::RepottingToRepottingEntity).collect(Collectors.toList()));
        return bonsaiEntity;
    }

    public Bonsai save(Bonsai bonsai){
       return bonsaiEntityToBonsai(bonsaiDao.save(bonsaiToBonsaiEntity(bonsai)));
    }

    public Bonsai update(Bonsai bonsai){
        Bonsai bonsaiUpdate = new Bonsai();

        bonsaiUpdate.setId(bonsai.getId());
        bonsaiUpdate.setNom(bonsai.getNom());
        bonsaiUpdate.setSpecies(bonsai.getSpecies());
        bonsaiUpdate.setAcquisition_age(bonsai.getAcquisition_age());
        bonsaiUpdate.setAcquisition_date(bonsai.getAcquisition_date());

        return bonsaiEntityToBonsai(bonsaiDao.save(bonsaiToBonsaiEntity(bonsaiUpdate)));

    }
    public Optional<Bonsai> findByID(UUID id){
        return bonsaiDao.findById(id).map(this::bonsaiEntityToBonsai);
    }

    public List<Bonsai> findAll(){
        List<Bonsai> listeBonsai = new ArrayList<>();
        bonsaiDao.findAll().forEach(b -> listeBonsai.add(bonsaiEntityToBonsai(b)));
        return listeBonsai;
    }

    public Optional<Bonsai> findByName(String nom){
        return bonsaiDao.findByNom(nom).map(this::bonsaiEntityToBonsai);
    }

    public void delete(UUID id){
        bonsaiDao.deleteById(id);
    }

    public boolean existsById(UUID id){
        return bonsaiDao.existsById(id);
    }

    public List<Pruning> getAllPruning(UUID id){

        return bonsaiDao.getById(id).getPruning().stream().map(PruningRepository::PruningEntityToPruning).collect(Collectors.toList());
    }
    public List<Repotting> getAllRepotting(UUID id){

        return bonsaiDao.getById(id).getRepotting().stream().map(RepottingRepository::RepottingEntityToRepotting).collect(Collectors.toList());
    }
    public List<Watering> getAllWatering(UUID id){

        return bonsaiDao.getById(id).getWatering().stream().map(WateringRepository::WateringEntityToWatering).collect(Collectors.toList());
    }

}

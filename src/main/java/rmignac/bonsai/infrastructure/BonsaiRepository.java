package rmignac.bonsai.infrastructure;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import rmignac.bonsai.BonsaiMapper;
import rmignac.bonsai.domain.Bonsai;
import rmignac.bonsai.domain.Status;
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

    public Bonsai save(Bonsai bonsai){
       return BonsaiMapper.bonsaiEntityToBonsai(bonsaiDao.save(BonsaiMapper.bonsaiToBonsaiEntity(bonsai)));
    }

    public Bonsai update(Bonsai bonsai){

        return BonsaiMapper.bonsaiEntityToBonsai(bonsaiDao.save(BonsaiMapper.bonsaiToBonsaiEntity(bonsai)));

    }
    public Optional<Bonsai> findByID(UUID id){
        return bonsaiDao.findById(id).map(BonsaiMapper::bonsaiEntityToBonsai);
    }

    public List<Bonsai> findAll(){
        List<Bonsai> listeBonsai = new ArrayList<>();
        bonsaiDao.findAll().forEach(b -> listeBonsai.add(BonsaiMapper.bonsaiEntityToBonsai(b)));
        return listeBonsai;
    }

    public Optional<Bonsai> findByName(String nom){
        return bonsaiDao.findByNom(nom).map(BonsaiMapper::bonsaiEntityToBonsai);
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


    public List<Bonsai> findAllWithFilter(Status status, int older_than, String sort, String direction){
        Sort mysort = null;
        if(sort!=null) {
            if(!(sort.equalsIgnoreCase("status") || sort.equalsIgnoreCase("acquisition_age"))){
                sort = null;
            }
            if (direction == null ) {
                mysort = Sort.by(Sort.Direction.DESC, sort);
            } else {
                if(direction.equalsIgnoreCase("asc")){
                    mysort = Sort.by(Sort.Direction.ASC, sort);
                }else{
                    mysort = Sort.by(Sort.Direction.DESC, sort);

                }
            }
        }
        if(status == null){
            return bonsaiDao.findAllWithFilterWithoutStatus(older_than, mysort).stream().map(BonsaiMapper::bonsaiEntityToBonsai).collect(Collectors.toList());

        }
        return bonsaiDao.findAllWithFilter(status, older_than, mysort).stream().map(BonsaiMapper::bonsaiEntityToBonsai).collect(Collectors.toList());
    }
}

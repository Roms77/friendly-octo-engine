package rmignac.bonsai.infrastructure;

import org.springframework.stereotype.Component;
import rmignac.bonsai.domain.Bonsai;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        return bonsaiEntity;
    }

    public Bonsai save(Bonsai bonsai){
       return bonsaiEntityToBonsai(bonsaiDao.save(bonsaiToBonsaiEntity(bonsai)));
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
        return bonsaiDao.findByName(nom).map(this::bonsaiEntityToBonsai);
    }

    public void delete(UUID id){
        bonsaiDao.deleteById(id);
    }

    public boolean existsById(UUID id){
        return bonsaiDao.existsById(id);
    }
}

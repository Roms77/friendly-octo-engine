package rmignac.bonsai;

import rmignac.bonsai.domain.Bonsai;
import rmignac.bonsai.exposition.BonsaiDTO;
import rmignac.bonsai.infrastructure.BonsaiEntity;
import rmignac.pruning.domain.Pruning;
import rmignac.pruning.infrastructure.PruningRepository;
import rmignac.repotting.domain.Repotting;
import rmignac.repotting.infrastructure.RepottingRepository;
import rmignac.watering.domain.Watering;
import rmignac.watering.infrastructure.WateringRepository;

import java.util.Comparator;
import java.util.stream.Collectors;

public class BonsaiMapper {

    public static Bonsai bonsaiDTOtoBonsai(BonsaiDTO bonsaiDTO){
        if(bonsaiDTO==null){
            return null;
        }
        Bonsai b = new Bonsai();
        b.setId(bonsaiDTO.getId());
        b.setNom(bonsaiDTO.getNom());
        b.setSpecies(bonsaiDTO.getSpecies());
        b.setAcquisition_age(bonsaiDTO.getAcquisition_age());
        b.setAcquisition_date(bonsaiDTO.getAcquisition_date());
        b.setStatus(bonsaiDTO.getStatus());
        return b;
    }

    public static BonsaiDTO BonsaiToBonsaiDTO(Bonsai bonsai){
        if(bonsai == null){
            return null;
        }

        BonsaiDTO bonsaiDto = new BonsaiDTO();
        bonsaiDto.setId(bonsai.getId());
        bonsaiDto.setNom(bonsai.getNom());
        bonsaiDto.setSpecies(bonsai.getSpecies());
        bonsaiDto.setAcquisition_age(bonsai.getAcquisition_age());
        bonsaiDto.setAcquisition_date(bonsai.getAcquisition_date());
        bonsaiDto.setStatus(bonsai.getStatus());
        bonsaiDto.setLast_pruning(bonsai.getPruning().stream().sorted(Comparator.comparing(Pruning::getDatetime)).map(Pruning::getDatetime).findFirst().orElse(null));
        bonsaiDto.setLast_watering(bonsai.getWatering().stream().sorted(Comparator.comparing(Watering::getDatetime)).map(Watering::getDatetime).findFirst().orElse(null));
        bonsaiDto.setLast_repotting(bonsai.getRepotting().stream().sorted(Comparator.comparing(Repotting::getDatetime)).map(Repotting::getDatetime).findFirst().orElse(null));

        return bonsaiDto;
    }

    public static Bonsai bonsaiEntityToBonsai(BonsaiEntity bonsaiEntity) {
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
    public static BonsaiEntity bonsaiToBonsaiEntity(Bonsai bonsai){
        BonsaiEntity bonsaiEntity = new BonsaiEntity();
        bonsaiEntity.setId(bonsai.getId());
        bonsaiEntity.setNom(bonsai.getNom());
        bonsaiEntity.setAcquisition_date(bonsai.getAcquisition_date());
        bonsaiEntity.setAcquisition_age(bonsai.getAcquisition_age());
        bonsaiEntity.setSpecies(bonsai.getSpecies());
        bonsaiEntity.setStatus(bonsai.getStatus());
        return bonsaiEntity;
    }
}

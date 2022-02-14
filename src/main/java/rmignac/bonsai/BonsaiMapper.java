package rmignac.bonsai;

import rmignac.bonsai.domain.Bonsai;
import rmignac.bonsai.exposition.BonsaiDTO;
import rmignac.bonsai.exposition.PruningDTO;
import rmignac.bonsai.exposition.RepottingDTO;
import rmignac.bonsai.exposition.WateringDTO;
import rmignac.bonsai.infrastructure.BonsaiEntity;
import rmignac.bonsai.domain.Pruning;
import rmignac.bonsai.infrastructure.PruningEntity;
import rmignac.bonsai.infrastructure.RepottingEntity;
import rmignac.bonsai.infrastructure.WateringEntity;
import rmignac.bonsai.domain.Repotting;
import rmignac.bonsai.domain.Watering;
import rmignac.owner.OwnerMapper;

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
        //b.setOwner(OwnerMapper.ownerDTOToOwner(bonsaiDTO.getOwner()));
        return b;
    }

    public static BonsaiDTO bonsaiToBonsaiDTO(Bonsai bonsai){
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
        if(bonsai.getPruning()!=null)
        bonsaiDto.setLast_pruning(bonsai.getPruning().stream().sorted(Comparator.comparing(Pruning::getDatetime)).map(Pruning::getDatetime).findFirst().orElse(null));
        if(bonsai.getWatering()!=null)
        bonsaiDto.setLast_watering(bonsai.getWatering().stream().sorted(Comparator.comparing(Watering::getDatetime)).map(Watering::getDatetime).findFirst().orElse(null));
        if(bonsai.getRepotting()!=null)
        bonsaiDto.setLast_repotting(bonsai.getRepotting().stream().sorted(Comparator.comparing(Repotting::getDatetime)).map(Repotting::getDatetime).findFirst().orElse(null));
        //bonsaiDto.setOwner(OwnerMapper.ownerToOwnerDTO(bonsai.getOwner()));
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
        if(bonsaiEntity.getPruning() !=null)
        bonsai.setPruning(bonsaiEntity.getPruning().stream().map(BonsaiMapper::pruningEntityToPruning).collect(Collectors.toList()));
        if(bonsaiEntity.getWatering() !=null)
        bonsai.setWatering(bonsaiEntity.getWatering().stream().map(BonsaiMapper::wateringEntityToWatering).collect(Collectors.toList()));
        if(bonsaiEntity.getRepotting() !=null)
        bonsai.setRepotting(bonsaiEntity.getRepotting().stream().map(BonsaiMapper::repottingEntityToRepotting).collect(Collectors.toList()));
        bonsai.setOwner(OwnerMapper.ownerEntityToOwner(bonsaiEntity.getOwnerEntity()));
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
        bonsaiEntity.setOwnerEntity(OwnerMapper.ownerToOwnerEntity(bonsai.getOwner()));

        return bonsaiEntity;
    }

    public static Watering wateringEntityToWatering(WateringEntity wateringEntity){
        Watering watering = new Watering();
        watering.setId(wateringEntity.getId());
        watering.setDatetime(wateringEntity.getDatetime());
        return watering;
    }

    public static WateringEntity WateringToWateringEntity(Watering watering){
        WateringEntity wateringEntity = new WateringEntity();
        wateringEntity.setId(watering.getId());
        wateringEntity.setDatetime(watering.getDatetime());
        return wateringEntity;
    }

    public static Repotting repottingEntityToRepotting(RepottingEntity repottingEntity){
        Repotting repotting = new Repotting();
        repotting.setId(repottingEntity.getId());
        repotting.setDatetime(repottingEntity.getDatetime());
        return repotting;
    }

//    public static RepottingEntity RepottingToRepottingEntity(Repotting repotting){
//        RepottingEntity repottingEntity = new RepottingEntity();
//        repottingEntity.setId(repotting.getId());
//        repottingEntity.setDatetime(repotting.getDatetime());
//        return repottingEntity;
//    }
    public static Pruning pruningEntityToPruning(PruningEntity pruningEntity){
        Pruning pruning = new Pruning();
        pruning.setId(pruningEntity.getId());
        pruning.setDatetime(pruningEntity.getDatetime());
        return pruning;
    }

//    public static PruningEntity PruningToPruningEntity(Pruning pruning){
//        PruningEntity pruningEntity = new PruningEntity();
//        pruningEntity.setId(pruning.getId());
//        pruningEntity.setDatetime(pruning.getDatetime());
//        return pruningEntity;
//    }
//
//    public static Watering WateringDTOToWatering(WateringDTO wateringDTO){
//        Watering watering = new Watering();
//        watering.setId(wateringDTO.getId());
//        watering.setDatetime(wateringDTO.getDatetime());
//        return watering;
//    }

    public static WateringDTO wateringToWateringDTO(Watering watering){
        WateringDTO wateringDTO = new WateringDTO();
        wateringDTO.setId(watering.getId());
        wateringDTO.setDatetime(watering.getDatetime());
        return wateringDTO;
    }

//    public static Repotting RepottingDTOToRepotting(RepottingDTO repottingDTO){
//        Repotting repotting = new Repotting();
//        repotting.setId(repottingDTO.getId());
//        repotting.setDatetime(repottingDTO.getDatetime());
//        return repotting;
//    }

    public static RepottingDTO repottingToRepottingDTO(Repotting repotting){
        RepottingDTO repottingDTO = new RepottingDTO();
        repottingDTO.setId(repotting.getId());
        repottingDTO.setDatetime(repotting.getDatetime());
        return repottingDTO;
    }

//    public static Pruning PruningDTOToPruning(PruningDTO pruningDTO){
//        Pruning pruning = new Pruning();
//        pruning.setId(pruningDTO.getId());
//        pruning.setDatetime(pruningDTO.getDatetime());
//        return pruning;
//    }

    public static PruningDTO pruningToPruningDTO(Pruning pruning){
        PruningDTO pruningDTO = new PruningDTO();
        pruningDTO.setId(pruning.getId());
        pruningDTO.setDatetime(pruning.getDatetime());
        return pruningDTO;
    }

}

package rmignac.owner;

import rmignac.bonsai.BonsaiMapper;
import rmignac.bonsai.infrastructure.BonsaiEntity;
import rmignac.owner.domain.BonsaiSimplifie;
import rmignac.owner.domain.Owner;
import rmignac.owner.exposition.BonsaiSimplifieDTO;
import rmignac.owner.exposition.OwnerDTO;
import rmignac.owner.infrastructure.OwnerEntity;

import java.util.stream.Collectors;

public class OwnerMapper {

    public static Owner ownerEntityToOwner(OwnerEntity ownerEntity){
        Owner owner = new Owner();
        if(ownerEntity==null){
            return null;
        }
        owner.setId(ownerEntity.getId());
        owner.setName(ownerEntity.getName());
        if(ownerEntity.getBonsais()!=null)
        owner.setBonsais(ownerEntity.getBonsais().stream().map(OwnerMapper::bonsaiEntityToBonsaiSimplifie).collect(Collectors.toList()));

        return owner;
    }
    public static OwnerEntity ownerToOwnerEntity(Owner owner){
        OwnerEntity ownerEntity = new OwnerEntity();

        if(owner==null){
            return null;
        }
        ownerEntity.setId(owner.getId());
        ownerEntity.setName(owner.getName());
        return ownerEntity;
    }


    public static Owner ownerDTOToOwner(OwnerDTO ownerDTO){
        Owner owner = new Owner();
        if(ownerDTO==null){
            return null;
        }
        owner.setId(ownerDTO.getId());
        owner.setName(ownerDTO.getName());

        return owner;
    }
    public static OwnerDTO ownerToOwnerDTO(Owner owner){
        OwnerDTO ownerDTO = new OwnerDTO();

        if(owner==null){
            return null;
        }
        ownerDTO.setId(owner.getId());
        ownerDTO.setName(owner.getName());
        if(owner.getBonsais()!=null)
        ownerDTO.setBonsais(owner.getBonsais().stream().map(OwnerMapper::bonsaiSimplifieToBonsaiSimplifieDTO).collect(Collectors.toList()));
        return ownerDTO;
    }

    public static BonsaiSimplifieDTO bonsaiSimplifieToBonsaiSimplifieDTO(BonsaiSimplifie bonsaiSimplifie){

        BonsaiSimplifieDTO bonsaiSimplifieDTO = new BonsaiSimplifieDTO();
        if(bonsaiSimplifie==null) return null;

        bonsaiSimplifieDTO.setAge(bonsaiSimplifie.getAge());
        bonsaiSimplifieDTO.setId(bonsaiSimplifie.getId());
        bonsaiSimplifieDTO.setName(bonsaiSimplifie.getName());
        bonsaiSimplifieDTO.setSpecies(bonsaiSimplifie.getSpecies());

        return bonsaiSimplifieDTO;
    }


    public static BonsaiSimplifie bonsaiEntityToBonsaiSimplifie(BonsaiEntity bonsaiEntity){

        BonsaiSimplifie bonsaiSimplifie = new BonsaiSimplifie();
        if(bonsaiEntity==null) return null;

        bonsaiSimplifie.setAge(bonsaiEntity.getAcquisition_age());
        bonsaiSimplifie.setId(bonsaiEntity.getId());
        bonsaiSimplifie.setName(bonsaiEntity.getNom());
        bonsaiSimplifie.setSpecies(bonsaiEntity.getSpecies());

        return bonsaiSimplifie;
    }
}

package rmignac.owner;

import rmignac.bonsai.BonsaiMapper;
import rmignac.owner.domain.Owner;
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
        owner.setBonsais(ownerEntity.getBonsais().stream().map(BonsaiMapper::bonsaiEntityToBonsai).collect(Collectors.toList()));

        return owner;
    }
    public static OwnerEntity ownerToOwnerEntity(Owner owner){
        OwnerEntity ownerEntity = new OwnerEntity();

        if(owner==null){
            return null;
        }
        ownerEntity.setId(owner.getId());
        ownerEntity.setName(owner.getName());
        if(owner.getBonsais()!=null)
        ownerEntity.setBonsais(owner.getBonsais().stream().map(BonsaiMapper::bonsaiToBonsaiEntity).collect(Collectors.toList()));
        return ownerEntity;
    }


    public static Owner ownerDTOToOwner(OwnerDTO ownerDTO){
        Owner owner = new Owner();
        if(ownerDTO==null){
            return null;
        }
        owner.setId(ownerDTO.getId());
        owner.setName(ownerDTO.getName());
        if(ownerDTO.getBonsais()!=null)
        owner.setBonsais(ownerDTO.getBonsais().stream().map(BonsaiMapper::bonsaiDTOtoBonsai).collect(Collectors.toList()));

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
        ownerDTO.setBonsais(owner.getBonsais().stream().map(BonsaiMapper::BonsaiToBonsaiDTO).collect(Collectors.toList()));
        return ownerDTO;
    }
}

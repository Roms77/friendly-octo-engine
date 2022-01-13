package rmignac.owner.exposition;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmignac.bonsai.BonsaiMapper;
import rmignac.bonsai.exposition.BonsaiDTO;
import rmignac.owner.OwnerMapper;
import rmignac.owner.domain.OwnerService;
import rmignac.owner.exceptions.BonsaiNotExistException;
import rmignac.owner.exceptions.OwnerNotExistException;
import rmignac.owner.exceptions.UnautorizedException;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService){
        this.ownerService=ownerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<OwnerDTO>> getOwners(@RequestParam(name = "has_more", required = false, defaultValue = "0") int hasMore){

        List<OwnerDTO> ownerDTO = ownerService.getOwners(hasMore).stream().map(OwnerMapper::ownerToOwnerDTO).collect(Collectors.toList());
        return ResponseEntity.ok(ownerDTO);
    }

    @PostMapping
    public ResponseEntity<OwnerDTO> create(@RequestBody OwnerDTO ownerDTO){
        OwnerDTO resDto = OwnerMapper.ownerToOwnerDTO(ownerService.create(OwnerMapper.ownerDTOToOwner(ownerDTO)));
        if(resDto==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("/owners/"+resDto.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> findById(@PathVariable UUID id){

        Optional<OwnerDTO> ownerDTO = ownerService.findById(id).map(OwnerMapper::ownerToOwnerDTO);
        if(!ownerDTO.isPresent()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(ownerDTO.get());
        }
    }

    @GetMapping("/{id}/bonsais")
    public ResponseEntity<List<BonsaiSimplifieDTO>> getBonsaisByOwnerID(@PathVariable UUID id){

        Optional<List<BonsaiSimplifieDTO>> bonsais = ownerService.getBonsaisByOwnerID(id).map(l -> l.stream().map(OwnerMapper::bonsaiSimplifieToBonsaiSimplifieDTO).collect(Collectors.toList()));

        if(!bonsais.isPresent()){
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok(bonsais.get());
    }

    @PostMapping("/{currentOwnerId}/bonsais/{bonsaiId}/transfer")
    public ResponseEntity<BonsaiDTO> transferBonsai(@PathVariable UUID currentOwnerId, @PathVariable UUID bonsaiId, @RequestParam UUID newOwnerId){
        BonsaiDTO bonsaitransfer = null;
        try{
            bonsaitransfer = BonsaiMapper.bonsaiToBonsaiDTO(ownerService.transferBonsai(currentOwnerId, bonsaiId, newOwnerId));

        }catch (OwnerNotExistException e){
            return ResponseEntity.notFound().build();
        }catch (UnautorizedException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (BonsaiNotExistException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bonsaitransfer);
    }

    @PostMapping(path = "/{newOwnerId}/bonsais")
    public ResponseEntity<BonsaiDTO> addBonsai(@PathVariable(value="newOwnerId") UUID newOwnerId, @RequestParam UUID bonsaiId){
        BonsaiDTO bonsaiAdd = null;
        try{
            bonsaiAdd = BonsaiMapper.bonsaiToBonsaiDTO(ownerService.addBonsai(newOwnerId, bonsaiId));

        }catch (OwnerNotExistException e){
            return ResponseEntity.notFound().build();
        }catch (UnautorizedException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (BonsaiNotExistException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bonsaiAdd);
    }

}

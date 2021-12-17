package rmignac.owner.exposition;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
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

    @GetMapping
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
    public ResponseEntity<List<BonsaiDTO>> getBonsaisByOwnerID(@PathVariable UUID id){

        Optional<List<BonsaiDTO>> bonsais = ownerService.getBonsaisByOwnerID(id).map(l -> l.stream().map(BonsaiMapper::BonsaiToBonsaiDTO).collect(Collectors.toList()));

        if(!bonsais.isPresent()){
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok(bonsais.get());
    }

    @PostMapping("/owner/{currentOwnerId}/bonsais/{bonsaiId}/transfer")
    public ResponseEntity<BonsaiDTO> transferBonsai(@PathVariable UUID currentOwnerId, @PathVariable UUID bonsaiId, @RequestBody UUID newOwnerId){
        BonsaiDTO bonsaitransfer = null;
        try{
            bonsaitransfer = BonsaiMapper.BonsaiToBonsaiDTO(ownerService.transferBonsai(currentOwnerId, bonsaiId, newOwnerId));

        }catch (OwnerNotExistException e){
            return ResponseEntity.notFound().build();
        }catch (UnautorizedException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(bonsaitransfer);
    }

    @PostMapping("/owner/{currentOwnerId}/bonsais")
    public ResponseEntity<BonsaiDTO> addBonsai(@PathVariable UUID currentOwnerId, @RequestBody UUID bonsaiId){
        BonsaiDTO bonsaitransfer = null;
        try{
            bonsaitransfer = BonsaiMapper.BonsaiToBonsaiDTO(ownerService.addBonsai(currentOwnerId, bonsaiId));

        }catch (OwnerNotExistException e){
            return ResponseEntity.notFound().build();
        }catch (UnautorizedException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (BonsaiNotExistException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bonsaitransfer);
    }

}

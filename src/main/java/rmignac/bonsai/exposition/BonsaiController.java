package rmignac.bonsai.exposition;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmignac.bonsai.domain.BonsaiService;
import rmignac.pruning.exposition.PruningDTO;
import rmignac.repotting.domain.Repotting;
import rmignac.repotting.exposition.RepottingDTO;
import rmignac.watering.exposition.WateringDTO;


import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bonsai")
public class BonsaiController {


    private BonsaiService bonsaiService;
    public BonsaiController(BonsaiService bonsaiService){
        this.bonsaiService=bonsaiService;
    }


    @GetMapping("/")
    public List<BonsaiDTO> getAllBonsai(){
        List<BonsaiDTO> listeBonsaiDTO = new ArrayList<>();
        bonsaiService.findAll().forEach(b-> listeBonsaiDTO.add(BonsaiDTO.fromBonsai(b)));
        return listeBonsaiDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonsaiDTO> getBonsaiById(@PathVariable String id){
        Optional<BonsaiDTO> bonsaiDto;
        try{
             bonsaiDto = bonsaiService.findById(UUID.fromString(id)).map(BonsaiDTO::fromBonsai);
            if(bonsaiDto.isPresent()){
                return ResponseEntity.ok(bonsaiDto.get());
            }else{
                bonsaiDto = bonsaiService.findByName(id).map(BonsaiDTO::fromBonsai);
                if(bonsaiDto.isPresent()){
                    return ResponseEntity.ok(bonsaiDto.get());
                }
            }
        }catch (IllegalArgumentException e){
        }
        bonsaiDto = bonsaiService.findByName(id).map(BonsaiDTO::fromBonsai);
        if(bonsaiDto.isPresent()){
            return ResponseEntity.ok(bonsaiDto.get());
        }

        return ResponseEntity.notFound().build();
    }



    @PostMapping
    public ResponseEntity<BonsaiDTO> create(@RequestBody BonsaiDTO bonsaiDto){
        BonsaiDTO newbonsaiDTO = BonsaiDTO.fromBonsai(bonsaiService.save(bonsaiDto.toBonsai()));
        if(bonsaiDto.getNom() == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("/bonsai/"+bonsaiDto.getId())).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BonsaiDTO> update(@RequestBody BonsaiDTO bonsaiDto){

        BonsaiDTO newBonsaiDTO;

        if(!bonsaiService.existsById(bonsaiDto.getId())){
            return ResponseEntity.noContent().build();
        }else{
            newBonsaiDTO = BonsaiDTO.fromBonsai(bonsaiService.update(bonsaiDto.toBonsai()));
        }

        return ResponseEntity.ok(newBonsaiDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BonsaiDTO> delete(@PathVariable UUID id){

        if(!bonsaiService.existsById(id)){
            return ResponseEntity.notFound().build();
        }else{
            bonsaiService.delete(id);
        }
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<BonsaiDTO> changeStatus(@PathVariable UUID id, @RequestBody String status){

        BonsaiDTO newBonsaiDTO;
        if(!bonsaiService.existsById(id)){
            return ResponseEntity.notFound().build();
        }else{
            if(!(status.equals("alive") ||
               status.equals("dead") ||
               status.equals("unknown"))){
                return ResponseEntity.badRequest().build();
            }else{

                newBonsaiDTO = bonsaiService.findById(id).map(BonsaiDTO::fromBonsai).get();
                newBonsaiDTO.setStatus(status);
                bonsaiService.save(newBonsaiDTO.toBonsai());
                return ResponseEntity.ok(newBonsaiDTO);
            }
        }
    }

    @GetMapping("/{id}/pruning")
    public ResponseEntity<List<PruningDTO>> getAllPruning(@PathVariable UUID id){
        if(!bonsaiService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        List<PruningDTO> pruningDto = bonsaiService.getAllPruning(id).stream().map(PruningDTO::PruningToPruningDTO).collect(Collectors.toList());

        return ResponseEntity.ok(pruningDto);
    }
    @GetMapping("/{id}/repotting")
    public ResponseEntity<List<RepottingDTO>> getAllRepotting(@PathVariable UUID id){
        if(!bonsaiService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        List<RepottingDTO> repottingDto = bonsaiService.getAllRepotting(id).stream()
                .map(RepottingDTO::RepottingToRepottingDTO)
                .collect(Collectors.toList());


        return ResponseEntity.ok(repottingDto);
    }
    @GetMapping("/{id}/watering")
    public ResponseEntity<List<WateringDTO>> getAllWatering(@PathVariable UUID id){
        if(!bonsaiService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        List<WateringDTO> wateringDto = bonsaiService.getAllWatering(id).stream().map(WateringDTO::WateringToWateringDTO).collect(Collectors.toList());

        return ResponseEntity.ok(wateringDto);
    }



}

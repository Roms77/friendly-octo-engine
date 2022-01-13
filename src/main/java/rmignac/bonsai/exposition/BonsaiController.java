package rmignac.bonsai.exposition;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmignac.bonsai.BonsaiMapper;
import rmignac.bonsai.domain.BonsaiService;
import rmignac.bonsai.domain.Status;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bonsais")
public class BonsaiController {


    private BonsaiService bonsaiService;
    public BonsaiController(BonsaiService bonsaiService){
        this.bonsaiService=bonsaiService;
    }


    @GetMapping("/")
    public ResponseEntity<List<BonsaiDTO>> getAllBonsais(@RequestParam(name = "status", required = false) String status,
                                       @RequestParam(name = "older_than", required=false, defaultValue = "0") int older_than,
                                        @RequestParam(name = "sort", required = false) String sort,
                                        @RequestParam(name = "direction", required = false) String direction){
        List<BonsaiDTO> resBonsai ;
        try{
            resBonsai = bonsaiService.findAllWithFilter(status, older_than, sort, direction)
                    .stream()
                    .map(BonsaiMapper::bonsaiToBonsaiDTO)
                    .collect(Collectors.toList());
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(resBonsai);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BonsaiDTO> getBonsaiById(@PathVariable String id){
        Optional<BonsaiDTO> bonsaiDto;
        bonsaiDto = bonsaiService.findByIdOrName(id).map(BonsaiMapper::bonsaiToBonsaiDTO);
        if(bonsaiDto.isPresent()){
            return ResponseEntity.ok(bonsaiDto.get());
        }
        return ResponseEntity.notFound().build();
    }



    @PostMapping
    public ResponseEntity<BonsaiDTO> create(@RequestBody BonsaiDTO bonsaiDto){

        BonsaiDTO resBonsaiDTO = BonsaiMapper.bonsaiToBonsaiDTO(bonsaiService.save(BonsaiMapper.bonsaiDTOtoBonsai(bonsaiDto)));

        if(resBonsaiDTO == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("/bonsai/"+resBonsaiDTO.getId())).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BonsaiDTO> update(@PathVariable UUID id, @RequestBody BonsaiDTO bonsaiDto){
        BonsaiDTO resBonsai = BonsaiMapper.bonsaiToBonsaiDTO(bonsaiService.update(id, BonsaiMapper.bonsaiDTOtoBonsai(bonsaiDto)));
        if(resBonsai==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resBonsai);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BonsaiDTO> delete(@PathVariable UUID id){

        if(!bonsaiService.delete(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<BonsaiDTO> changeStatus(@PathVariable UUID id, @RequestBody String status){
        BonsaiDTO resBonsai;
        try{
            resBonsai = BonsaiMapper.bonsaiToBonsaiDTO(bonsaiService.changeStatus(id, Status.valueOf(status)));

        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
        if(resBonsai==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}/pruning")
    public ResponseEntity<List<PruningDTO>> getAllPruning(@PathVariable UUID id){
        List<PruningDTO> pruningDto = bonsaiService.getAllPruning(id).stream().map(BonsaiMapper::pruningToPruningDTO).collect(Collectors.toList());

        if(pruningDto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pruningDto);
    }
    @GetMapping("/{id}/repotting")
    public ResponseEntity<List<RepottingDTO>> getAllRepotting(@PathVariable UUID id){
        List<RepottingDTO> repottingDto = bonsaiService.getAllRepotting(id).stream()
                .map(BonsaiMapper::repottingToRepottingDTO)
                .collect(Collectors.toList());

        if(repottingDto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repottingDto);
    }
    @GetMapping("/{id}/watering")
    public ResponseEntity<List<WateringDTO>> getAllWatering(@PathVariable UUID id){

        List<WateringDTO> wateringDto = bonsaiService.getAllWatering(id).stream().map(BonsaiMapper::wateringToWateringDTO).collect(Collectors.toList());

        if(wateringDto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wateringDto);
    }



}

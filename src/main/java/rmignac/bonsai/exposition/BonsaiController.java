package rmignac.bonsai.exposition;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmignac.bonsai.domain.BonsaiService;


import java.util.*;

@RestController
@RequestMapping("/bonsai")
public class BonsaiController {


    private BonsaiService bonsaiService;
    public BonsaiController(BonsaiService bonsaiService){
        this.bonsaiService=bonsaiService;
    }


    //@RequestMapping(method = RequestMethod.GET, path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
//    @GetMapping("/")
//    //public List<BonsaiEntity> getBonsai(@RequestParam(required = false) String status, @RequestParam(required = false) String acquisition_age, @RequestParam(required = false) String direction){
//    public List<BonsaiDto> getBonsai(@RequestParam(required = false, name="status") String status, @RequestParam(required=false, name="acquisition_age") String acquisition_age){
//
//        List<BonsaiDto> liste_bonsai = null;
//        //BonsaiEntity bonsai = new BonsaiEntity();
//
//        if(status != null){
//            liste_bonsai = bonsaiDao.findAllWithStatus(status);
//            //bonsaiEntityStream = bonsaiEntityStream.filter(BonsaiEntity -> BonsaiEntity.getStatus().equals(status));
//        }else{
//            liste_bonsai=bonsaiDao.findAll();
//        }
////        if(acquisition_age != null){
////            try{
////                int age = Integer.valueOf(acquisition_age);
////                //liste_bonsai = bonsaiDao.findAllOlderByAge(age);
////                //bonsaiEntityStream= bonsaiEntityStream.filter(BonsaiEntity -> BonsaiEntity.getAcquisition_age()>=age);
////            }catch (NumberFormatException e){
////
////            }
////        }
//        //liste_bonsai= bonsaiDao.findAll();
//
//        return liste_bonsai;//bonsaiEntityStream.collect(Collectors.toList());
//    }
    //@RequestMapping(method = RequestMethod.GET, path = "/", produces = MediaType.APPLICATION_JSON_VALUE)


    @GetMapping("/")
    public List<BonsaiDto> getAllBonsai(){
        List<BonsaiDto> listeBonsaiDto = new ArrayList<>();
        bonsaiService.findAll().forEach(b-> listeBonsaiDto.add(BonsaiDto.fromBonsai(b)));
        return listeBonsaiDto;
    }
    @GetMapping("/{id}")
    public ResponseEntity<BonsaiDto> getBonsaiById(@PathVariable String id){
        Optional<BonsaiDto> bonsaiDto;
        try{
             bonsaiDto = bonsaiService.findById(UUID.fromString(id)).map(b-> BonsaiDto.fromBonsai(b));
            if(bonsaiDto.isPresent()){
                return ResponseEntity.ok(bonsaiDto.get());
            }else{
                bonsaiDto = bonsaiService.findByName(id).map(b-> BonsaiDto.fromBonsai(b));
                if(bonsaiDto.isPresent()){
                    return ResponseEntity.ok(bonsaiDto.get());
                }
            }
        }catch (IllegalArgumentException e){
            bonsaiDto = bonsaiService.findByName(id).map(b-> BonsaiDto.fromBonsai(b));
            if(bonsaiDto.isPresent()){
                return ResponseEntity.ok(bonsaiDto.get());
            }
        }
        if(bonsaiDto.isPresent()){
            return ResponseEntity.ok(bonsaiDto.get());
        }else{
            bonsaiDto = bonsaiService.findByName(id).map(b-> BonsaiDto.fromBonsai(b));
            if(bonsaiDto.isPresent()){
                return ResponseEntity.ok(bonsaiDto.get());
            }
        }
        return ResponseEntity.notFound().build();
    }



    @PostMapping
    public ResponseEntity<BonsaiDto> create(@RequestBody BonsaiDto bonsaiDto){
        BonsaiDto newbonsaiDto = BonsaiDto.fromBonsai(bonsaiService.save(bonsaiDto.toBonsai()));
        if(bonsaiDto.getNom() == null){
            return ResponseEntity.badRequest().build();
        }
         //   return ResponseEntity.created(new URI("/bonsai"));
        return new ResponseEntity<>(newbonsaiDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BonsaiDto> update(@RequestBody BonsaiDto bonsaiDto){

        BonsaiDto newBonsaiDto;

        if(!bonsaiService.existsById(bonsaiDto.getId())){
            return ResponseEntity.noContent().build();
        }else{
            newBonsaiDto = BonsaiDto.fromBonsai(bonsaiService.save(bonsaiDto.toBonsai()));
        }

        return ResponseEntity.ok(newBonsaiDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BonsaiDto> delete(@PathVariable UUID id){

        if(!bonsaiService.existsById(id)){
            return ResponseEntity.notFound().build();
        }else{
            bonsaiService.delete(id);
        }
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<BonsaiDto> changeStatus(@PathVariable UUID id, @RequestBody String status){

        BonsaiDto newBonsaiDto;
        if(!bonsaiService.existsById(id)){
            return ResponseEntity.notFound().build();
        }else{
            if(!(status.equals("alive") ||
               status.equals("dead") ||
               status.equals("unknown"))){
                return ResponseEntity.badRequest().build();
            }else{

                newBonsaiDto = bonsaiService.findById(id).map(b-> BonsaiDto.fromBonsai(b)).get();
                newBonsaiDto.setStatus(status);
                bonsaiService.save(newBonsaiDto.toBonsai());
                return ResponseEntity.ok(newBonsaiDto);
            }
        }
    }


}

package rmignac.bonsai;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/bonsai")
public class BonsaiController {


    private BonsaiDao bonsaiDao;
    public BonsaiController(BonsaiDao bonsaiDao){
        this.bonsaiDao=bonsaiDao;
    }


    //@RequestMapping(method = RequestMethod.GET, path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    //public List<BonsaiEntity> getBonsai(@PathVariable("status") String status, @PathVariable("acquisition_age") String acquisition_age, @PathVariable("direction") String direction){
    public List<BonsaiEntity> getBonsai(@PathVariable Map<String, String> pathVarsMap){
        String status = pathVarsMap.get("status");
        String acquisition_age = pathVarsMap.get("acquisition_age");
        Stream<BonsaiEntity> bonsaiEntityStream = bonsaiDao.findAll().stream();
        if(status != null){
            bonsaiEntityStream.filter(BonsaiEntity -> BonsaiEntity.getStatus().equals(status));
        }
        if(acquisition_age != null){
            try{
                int age = Integer.valueOf(acquisition_age);
                bonsaiEntityStream.filter(BonsaiEntity -> BonsaiEntity.getAcquisition_age()>=age);
            }catch (NumberFormatException e){

            }
        }

        return bonsaiEntityStream.collect(Collectors.toList());
    }
    //@RequestMapping(method = RequestMethod.GET, path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/{id}")
    public BonsaiEntity getBonsaiById(@PathVariable UUID id){
        //return bonsaiDao.getById(id)
        Optional<BonsaiEntity> bonsai = bonsaiDao.findById(id);
        if(bonsai.isPresent()){
            return bonsai.get();
        }
        return null;
    }

//    @GetMapping("/older_than?={acquisition_age}")
//    public List<BonsaiEntity> getBonsaiOlderThan(@PathVariable int acquisition_age){
//        return getBonsai().stream().filter(bonsaiEntity -> bonsaiEntity.getAcquisition_age()>=acquisition_age).collect(Collectors.toList());
//    }
//    @GetMapping("/sortby?={sort}+{direction}")
//    public List<BonsaiEntity> getBonsaiSortBy(@PathVariable String sort, String direction){
//        List<BonsaiEntity> liste_bonsai = getBonsai();
//        switch(sort){
//            case "status":
//                liste_bonsai = getBonsai().stream().sorted(Comparator.comparing(BonsaiEntity::getStatus)).collect(Collectors.toList());
//                break;
//            case "last_watering":
//                break;
//            case "last_repotting":
//                break;
//            case "last_pruning":
//                break;
//            case "age":
//                liste_bonsai = getBonsai().stream().sorted(Comparator.comparingInt(BonsaiEntity::getAcquisition_age)).collect(Collectors.toList());
//                break;
//        }
//        return liste_bonsai;
//    }


    @PostMapping
    public ResponseEntity<BonsaiEntity> create(@RequestBody BonsaiEntity bonsai){
        BonsaiEntity myBonsai = bonsaiDao.save(bonsai);
        if(myBonsai==null){
            return null;
        }else{
            return new ResponseEntity<>(myBonsai, HttpStatus.CREATED);
        }
    }


}

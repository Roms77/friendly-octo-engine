package rmignac.repotting.infrastructure;

import org.springframework.stereotype.Component;
import rmignac.repotting.domain.Repotting;
import rmignac.repotting.exposition.RepottingDTO;


@Component
public class RepottingRepository {

    public static Repotting RepottingDTOToRepotting(RepottingDTO repottingDTO){
        Repotting repotting = new Repotting();
        repotting.setId(repottingDTO.getId());
        repotting.setDatetime(repottingDTO.getDatetime());
        return repotting;
    }

    public static RepottingDTO RepottingToRepottingDTO(Repotting repotting){
        RepottingDTO repottingDTO = new RepottingDTO();
        repottingDTO.setId(repotting.getId());
        repottingDTO.setDatetime(repotting.getDatetime());
        return repottingDTO;
    }
}

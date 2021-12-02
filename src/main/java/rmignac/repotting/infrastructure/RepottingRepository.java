package rmignac.repotting.infrastructure;

import org.springframework.stereotype.Component;
import rmignac.repotting.domain.Repotting;
import rmignac.repotting.exposition.RepottingDTO;


@Component
public class RepottingRepository {

    public static Repotting RepottingEntityToRepotting(RepottingEntity repottingEntity){
        Repotting repotting = new Repotting();
        repotting.setId(repottingEntity.getId());
        repotting.setDatetime(repottingEntity.getDatetime());
        return repotting;
    }

    public static RepottingEntity RepottingToRepottingEntity(Repotting repotting){
        RepottingEntity repottingEntity = new RepottingEntity();
        repottingEntity.setId(repotting.getId());
        repottingEntity.setDatetime(repotting.getDatetime());
        return repottingEntity;
    }
}

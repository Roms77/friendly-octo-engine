package rmignac.pruning.infrastructure;

import org.springframework.stereotype.Component;
import rmignac.pruning.domain.Pruning;
import rmignac.pruning.exposition.PruningDTO;
import rmignac.repotting.domain.Repotting;
import rmignac.repotting.exposition.RepottingDTO;

@Component
public class PruningRepository {
    public static Pruning PruningDTOToPruning(PruningDTO pruningDTO){
        Pruning pruning = new Pruning();
        pruning.setId(pruningDTO.getId());
        pruning.setDatetime(pruningDTO.getDatetime());
        return pruning;
    }

    public static PruningDTO PruningToPruningDTO(Pruning pruning){
        PruningDTO pruningDTO = new PruningDTO();
        pruningDTO.setId(pruning.getId());
        pruningDTO.setDatetime(pruning.getDatetime());
        return pruningDTO;
    }
}

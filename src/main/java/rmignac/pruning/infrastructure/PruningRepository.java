package rmignac.pruning.infrastructure;

import org.springframework.stereotype.Component;
import rmignac.pruning.domain.Pruning;
import rmignac.pruning.exposition.PruningDTO;
import rmignac.repotting.domain.Repotting;
import rmignac.repotting.exposition.RepottingDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PruningRepository {

    private PruningDAO pruningDAO;

    public PruningRepository(PruningDAO pruningDAO){
        this.pruningDAO=pruningDAO;
    }
    public static Pruning PruningEntityToPruning(PruningEntity pruningEntity){
        Pruning pruning = new Pruning();
        pruning.setId(pruningEntity.getId());
        pruning.setDatetime(pruningEntity.getDatetime());
        return pruning;
    }

    public static PruningEntity PruningToPruningEntity(Pruning pruning){
        PruningEntity pruningEntity = new PruningEntity();
        pruningEntity.setId(pruning.getId());
        pruningEntity.setDatetime(pruning.getDatetime());
        return pruningEntity;
    }

}

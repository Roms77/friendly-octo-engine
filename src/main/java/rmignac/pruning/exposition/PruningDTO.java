package rmignac.pruning.exposition;

import rmignac.pruning.domain.Pruning;

import java.util.Date;
import java.util.UUID;

public class PruningDTO {

    private UUID id;
    private Date datetime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

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

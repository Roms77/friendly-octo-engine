package rmignac.repotting.exposition;

import rmignac.repotting.domain.Repotting;

import java.util.Date;
import java.util.UUID;

public class RepottingDTO {

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

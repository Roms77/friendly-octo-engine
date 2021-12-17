package rmignac.bonsai.exposition;

import rmignac.bonsai.domain.Watering;

import java.util.Date;
import java.util.UUID;

public class WateringDTO {

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


}
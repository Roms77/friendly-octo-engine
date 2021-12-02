package rmignac.watering.exposition;

import rmignac.watering.domain.Watering;

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

    public static Watering WateringDTOToWatering(WateringDTO wateringDTO){
        Watering watering = new Watering();
        watering.setId(wateringDTO.getId());
        watering.setDatetime(wateringDTO.getDatetime());
        return watering;
    }

    public static WateringDTO WateringToWateringDTO(Watering watering){
        WateringDTO wateringDTO = new WateringDTO();
        wateringDTO.setId(watering.getId());
        wateringDTO.setDatetime(watering.getDatetime());
        return wateringDTO;
    }
}

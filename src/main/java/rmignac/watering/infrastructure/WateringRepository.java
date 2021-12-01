package rmignac.watering.infrastructure;

import org.springframework.stereotype.Component;
import rmignac.watering.domain.Watering;
import rmignac.watering.exposition.WateringDTO;

@Component
public class WateringRepository {

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

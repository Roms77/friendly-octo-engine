package rmignac.watering.infrastructure;

import org.springframework.stereotype.Component;
import rmignac.watering.domain.Watering;
import rmignac.watering.exposition.WateringDTO;

@Component
public class WateringRepository {

    public static Watering WateringEntityToWatering(WateringEntity wateringEntity){
        Watering watering = new Watering();
        watering.setId(wateringEntity.getId());
        watering.setDatetime(wateringEntity.getDatetime());
        return watering;
    }

    public static WateringEntity WateringToWateringEntity(Watering watering){
        WateringEntity wateringEntity = new WateringEntity();
        wateringEntity.setId(watering.getId());
        wateringEntity.setDatetime(watering.getDatetime());
        return wateringEntity;
    }
}

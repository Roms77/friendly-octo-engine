package rmignac.watering.infrastructure;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity(name="watering")
@Table(name="watering")
public class WateringEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id")
    private UUID id;
    @Column(name="datetime")
    private String datetime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDate() {
        return datetime;
    }

    public void setDate(String datetime) {
        this.datetime = datetime;
    }
}

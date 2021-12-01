package rmignac.repotting.infrastructure;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity(name="repotting")
@Table(name="repotting")
public class RepottingEntity {

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

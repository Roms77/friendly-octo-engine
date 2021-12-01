package rmignac.pruning.domain;

import java.util.UUID;

public class Pruning {

    private UUID id;
    private String datetime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}

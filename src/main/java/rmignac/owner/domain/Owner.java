package rmignac.owner.domain;

import rmignac.bonsai.domain.Bonsai;
import rmignac.bonsai.exposition.BonsaiDTO;

import java.util.List;
import java.util.UUID;

public class Owner {

    private UUID id;
    private String name;
    private List<Bonsai> bonsais;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bonsai> getBonsais() {
        return bonsais;
    }

    public void setBonsais(List<Bonsai> bonsais) {
        this.bonsais = bonsais;
    }
}

package rmignac.owner.infrastructure;

import org.hibernate.annotations.GenericGenerator;
import rmignac.bonsai.domain.Bonsai;
import rmignac.bonsai.infrastructure.BonsaiEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name="owner")
@Table(name="owner")
public class OwnerEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id")
    private UUID id;
    @Column(name="name")
    private String name;
    @OneToMany(targetEntity= BonsaiEntity.class, mappedBy="ownerEntity" )
    private List<BonsaiEntity> bonsais;

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

    public List<BonsaiEntity> getBonsais() {
        return bonsais;
    }

    public void setBonsais(List<BonsaiEntity> bonsais) {
        this.bonsais = bonsais;
    }
}

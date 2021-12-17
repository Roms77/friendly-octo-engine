package rmignac.bonsai.infrastructure;

import org.hibernate.annotations.GenericGenerator;
import rmignac.bonsai.domain.Status;
import rmignac.owner.infrastructure.OwnerEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity(name="bonsai")
@Table(name="bonsai")
public class BonsaiEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id")
    private UUID id;
    @Column(name="name")
    private String nom;
    @Column(name="species")
    private String species;
    @Column(name="acquisition_date")
    private Date acquisition_date;
    @Column(name="acquisition_age")
    private int acquisition_age;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;
    @OneToMany(targetEntity=WateringEntity.class, mappedBy="bonsaiEntity")
    private List<WateringEntity> watering;
    @OneToMany(targetEntity=PruningEntity.class, mappedBy="bonsaiEntity" )
    private List<PruningEntity> pruning;
    @OneToMany(targetEntity= RepottingEntity.class, mappedBy="bonsaiEntity" )
    private List<RepottingEntity> repotting;
    @ManyToOne(targetEntity = OwnerEntity.class) @JoinColumn(name="owner_id")
    private OwnerEntity ownerEntity;


    public BonsaiEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Date getAcquisition_date() {
        return acquisition_date;
    }

    public void setAcquisition_date(Date acquisition_date) {
        this.acquisition_date = acquisition_date;
    }

    public int getAcquisition_age() {
        return acquisition_age;
    }

    public void setAcquisition_age(int acquisition_age) {
        this.acquisition_age = acquisition_age;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<WateringEntity> getWatering() {
        return watering;
    }

    public void setWatering(List<WateringEntity> watering) {
        this.watering = watering;
    }

    public List<PruningEntity> getPruning() {
        return pruning;
    }

    public void setPruning(List<PruningEntity> pruning) {
        this.pruning = pruning;
    }

    public List<RepottingEntity> getRepotting() {
        return repotting;
    }

    public void setRepotting(List<RepottingEntity> repotting) {
        this.repotting = repotting;
    }

    public OwnerEntity getOwnerEntity() {
        return ownerEntity;
    }

    public void setOwnerEntity(OwnerEntity ownerEntity) {
        this.ownerEntity = ownerEntity;
    }
}

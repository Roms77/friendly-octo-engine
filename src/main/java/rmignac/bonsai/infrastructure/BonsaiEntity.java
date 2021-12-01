package rmignac.bonsai.infrastructure;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
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
    @Column(name="status")
    private String status;
    @Column(name="last_watering_id")
    private String last_watering_id;
    @Column(name="last_pruning_id")
    private String last_pruning_id;
    @Column(name="last_repotting_id")
    private String last_repotting_id;
    @Column(name="owner_id")
    private String owner_id;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLast_watering_id() {
        return last_watering_id;
    }

    public void setLast_watering_id(String last_watering_id) {
        this.last_watering_id = last_watering_id;
    }

    public String getLast_pruning_id() {
        return last_pruning_id;
    }

    public void setLast_pruning_id(String last_pruning_id) {
        this.last_pruning_id = last_pruning_id;
    }

    public String getLast_repotting_id() {
        return last_repotting_id;
    }

    public void setLast_repotting_id(String last_repotting_id) {
        this.last_repotting_id = last_repotting_id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }
}

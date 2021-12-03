package rmignac.bonsai.domain;

import rmignac.pruning.domain.Pruning;
import rmignac.repotting.domain.Repotting;
import rmignac.watering.domain.Watering;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Bonsai {

    private UUID id;
    private String nom;
    private String species;
    private Date acquisition_date;
    private int acquisition_age;
    private Status status;
    private List<Watering> watering;
    private List<Pruning> pruning;
    private List<Repotting> repotting;
    //private UUID owner_id;

    public Bonsai() {
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

    public List<Watering> getWatering() {
        return watering;
    }

    public void setWatering(List<Watering> watering) {
        this.watering = watering;
    }

    public List<Pruning> getPruning() {
        return pruning;
    }

    public void setPruning(List<Pruning> pruning) {
        this.pruning = pruning;
    }

    public List<Repotting> getRepotting() {
        return repotting;
    }

    public void setRepotting(List<Repotting> repotting) {
        this.repotting = repotting;
    }
}

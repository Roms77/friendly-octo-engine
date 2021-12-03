package rmignac.bonsai.exposition;

import rmignac.bonsai.domain.Bonsai;
import rmignac.bonsai.domain.Status;
import rmignac.pruning.domain.Pruning;
import rmignac.pruning.exposition.PruningDTO;
import rmignac.pruning.infrastructure.PruningRepository;
import rmignac.repotting.domain.Repotting;
import rmignac.repotting.exposition.RepottingDTO;
import rmignac.repotting.infrastructure.RepottingRepository;
import rmignac.watering.domain.Watering;
import rmignac.watering.exposition.WateringDTO;
import rmignac.watering.infrastructure.WateringRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BonsaiDTO {


    private UUID id;
    private String nom;
    private String species;
    private Date acquisition_date;
    private int acquisition_age;
    private Status status;
    private Date last_watering;
    private Date last_pruning;
    private Date last_repotting;
    //private UUID owner_id;

    public BonsaiDTO() {
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

    public Date getLast_watering() {
        return last_watering;
    }

    public void setLast_watering(Date last_watering) {
        this.last_watering = last_watering;
    }

    public Date getLast_pruning() {
        return last_pruning;
    }

    public void setLast_pruning(Date last_pruning) {
        this.last_pruning = last_pruning;
    }

    public Date getLast_repotting() {
        return last_repotting;
    }

    public void setLast_repotting(Date last_repotting) {
        this.last_repotting = last_repotting;
    }

}

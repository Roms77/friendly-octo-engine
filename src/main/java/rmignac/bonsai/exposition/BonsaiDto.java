package rmignac.bonsai.exposition;

import org.hibernate.annotations.GenericGenerator;
import rmignac.bonsai.domain.Bonsai;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

public class BonsaiDto {


    private UUID id;
    private String nom;
    private String species;
    private Date acquisition_date;
    private int acquisition_age;
    private String status;


    public BonsaiDto() {
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

    public Bonsai toBonsai(){
        Bonsai b = new Bonsai();
        b.setId(this.id);
        b.setNom(this.nom);
        b.setSpecies(this.species);
        b.setAcquisition_age(this.acquisition_age);
        b.setAcquisition_date(this.acquisition_date);
        b.setStatus(this.status);
        return b;
    }

    public static BonsaiDto fromBonsai(Bonsai bonsai){
        if(bonsai == null){
            return null;
        }

        BonsaiDto bonsaiDto = new BonsaiDto();
        bonsaiDto.setId(bonsai.getId());
        bonsaiDto.setNom(bonsai.getNom());
        bonsaiDto.setSpecies(bonsai.getSpecies());
        bonsaiDto.setAcquisition_age(bonsai.getAcquisition_age());
        bonsaiDto.setAcquisition_date(bonsai.getAcquisition_date());
        bonsaiDto.setStatus(bonsai.getStatus());

        return bonsaiDto;
    }
}

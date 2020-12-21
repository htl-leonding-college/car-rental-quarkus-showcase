package at.htl.vehicle.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
public class Vehicle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    //@JsonProperty("license-plate-no") // wegen REST-assured-Tests
    @JsonbProperty("license-plate-no") // da jsonb verwendet wird
    private String licensePlateNo;

    public Vehicle() {
    }


    public Vehicle(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public Vehicle(String brand, String model, String licensePlateNo) {
        this.brand = brand;
        this.model = model;
        this.licensePlateNo = licensePlateNo;
    }

    //region getter and setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlateNo() {
        return licensePlateNo;
    }

    public void setLicensePlateNo(String licensePlateNo) {
        this.licensePlateNo = licensePlateNo;
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s", licensePlateNo, brand, model);
    }

    //endregion
}

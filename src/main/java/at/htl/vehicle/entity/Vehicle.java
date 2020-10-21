package at.htl.vehicle.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.json.bind.annotation.JsonbProperty;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vehicle {

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

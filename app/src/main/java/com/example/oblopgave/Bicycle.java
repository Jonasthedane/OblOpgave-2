package com.example.oblopgave;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



import java.io.Serializable;


public class Bicycle implements Serializable{


    public Bicycle(){
    }
    public Bicycle(String frameNumber, String kindOfBicycle, String brand, String colors, String place, String missingFound, String firebaseUserId){
        setId(0);
        setFrameNumber(frameNumber);
        setKindOfBicycle(kindOfBicycle);
        setBrand(brand);
        setColors(colors);
        setPlace(place);
        setDate("");
        setUserId(-1);
        setFirebaseUserId(firebaseUserId);
        setMissingFound(missingFound);
    }


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("frameNumber")
    @Expose
    private String frameNumber;
    @SerializedName("kindOfBicycle")
    @Expose
    private String kindOfBicycle;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("colors")
    @Expose
    private String colors;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("missingFound")
    @Expose
    private String missingFound;
    @SerializedName("firebaseUserId")
    @Expose
    private String firebaseUserId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getKindOfBicycle() {
        return kindOfBicycle;
    }

    public void setKindOfBicycle(String kindOfBicycle) {
        this.kindOfBicycle = kindOfBicycle;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMissingFound() {
        return missingFound;
    }

    public void setMissingFound(String missingFound) {
        this.missingFound = missingFound;
    }

    public String getFirebaseUserId() {
        return firebaseUserId;
    }

    public void setFirebaseUserId(String firebaseUserId) {
        this.firebaseUserId = firebaseUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Bike{" +
                "frameNumber='" + frameNumber + '\'' +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                ", missingFound='" + missingFound + '\'' +
                '}';
        //return new ToStringBuilder(this).append("id", id).append("frameNumber", frameNumber).append("kindOfBicycle", kindOfBicycle).append("brand", brand).append("colors", colors).append("place", place).append("date", date).append("userId", userId).append("missingFound", missingFound).append("firebaseUserId", firebaseUserId).append("name", name).append("phone", phone).toString();
    }

}


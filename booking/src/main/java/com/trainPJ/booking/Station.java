package com.trainPJ.booking;

public class Station {
    private String id;
    private String status;
    private String name;


    public Station(String id, String status, String name){
        this.id=id;
        this.status = status;
        this.name = name;
    }




    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String type) {
        this.status = type;
    }


    public void setname(String name) {
        this.name=name;
    }

    public String getname() {
        return name;
    }








}

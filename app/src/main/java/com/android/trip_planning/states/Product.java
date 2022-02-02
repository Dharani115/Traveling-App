package com.android.trip_planning.states;
public class Product {
    private String place_name, state_name,map,description,photo;
    public Product(String place_name, String state_name, String map, String description, String photo) {
        this.place_name=place_name;
        this.state_name=state_name;
        this.map=map;
        this.description=description;
        this.photo=photo;
    }
    public String getplace_name() {
        return place_name;
    }
    public void setplace_name(String place_name) {
        this.place_name = place_name;
    }
    public String getstate_name() {
        return state_name;
    }
    public void setstate_name(String state_name) {
        this.state_name = state_name;
    }
    public String getmap() {
        return map;
    }
    public void setmap(String map) {
        this.map = map;
    }
    public String getdescription() {
        return description;
    }
    public void setdescription(String description) {
        this.description = description;
    }
    public String getPhoto() { return photo;
    }
    public void setphoto(String photo) {
        this.photo = photo;
    }
}
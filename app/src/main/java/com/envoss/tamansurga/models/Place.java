package com.envoss.tamansurga.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("id_region")
    @Expose
    private Integer idRegion;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("description")
    @Expose
    private String description;

    public Place() {

    }

    public Integer getId() {
        return id;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }
}

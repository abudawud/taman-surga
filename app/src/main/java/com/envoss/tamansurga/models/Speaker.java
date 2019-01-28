package com.envoss.tamansurga.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Speaker {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("rank")
    @Expose
    private String rank;

    public Speaker() {

    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRank() {
        return rank;
    }
}

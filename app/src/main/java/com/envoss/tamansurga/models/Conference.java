package com.envoss.tamansurga.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Conference {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("start_at")
    @Expose
    private String startAt;

    @SerializedName("rank")
    @Expose
    private Float rank;

    @SerializedName("speaker")
    @Expose
    private Speaker speaker;

    @SerializedName("place")
    @Expose
    private Place place;

    public Conference() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getStartAt() {
        return startAt;
    }

    public Float getRank() {
        return rank;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public Place getPlace() {
        return place;
    }
}

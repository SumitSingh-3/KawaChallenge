package com.app.modal.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("seed")
    @Expose
    public String seed;
    @SerializedName("results")
    @Expose
    public Integer results;
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("version")
    @Expose
    public String version;

}
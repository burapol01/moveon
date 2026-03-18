package com.example.MoveOn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Datapersonal implements Serializable {

    @SerializedName("datapn_id")
    private String datapn_id;
    @SerializedName("titlename_id")
    private int titlename_id;
    @SerializedName("descript_title")
    private String descript_title;
    @SerializedName("name")
    private String name;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("sex_id")
    private int sex_id;
    @SerializedName("descript_sex")
    private String descript_sex;
    @SerializedName("stat_lev_id")
    private int stat_lev_id;
    @SerializedName("descript_stat")
    private String descript_stat;
    @SerializedName("weight")
    private String weight;
    @SerializedName("height")
    private String height;
    @SerializedName("birth")
    private String birth;
    @SerializedName("address")
    private String address;
    @SerializedName("user_id")
    private int user_id;

    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;


    @SerializedName("picture")
    private String picture;

    public String getDescript_title() { return descript_title; }

    public void setDescript_title(String descript_title) { this.descript_title = descript_title; }

    public String getDescript_sex() { return descript_sex; }

    public void setDescript_sex(String descript_sex) { this.descript_sex = descript_sex; }

    public String getDescript_stat() { return descript_stat; }

    public void setDescript_stat(String descript_stat) { this.descript_stat = descript_stat; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getDatapn_id() { return datapn_id; }

    public void setDatapn_id(String datapn_id) { this.datapn_id = datapn_id; }

    public int getTitlename_id() { return titlename_id; }

    public void setTitlename_id(int titlename_id) { this.titlename_id = titlename_id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public int getSex_id() { return sex_id; }

    public void setSex_id(int sex_id) { this.sex_id = sex_id; }

    public int getStat_lev_id() { return stat_lev_id; }

    public void setStat_lev_id(int stat_lev_id) { this.stat_lev_id = stat_lev_id; }

    public String getWeight() { return weight; }

    public void setWeight(String weight) { this.weight = weight; }

    public String getHeight() { return height; }

    public void setHeight(String height) { this.height = height; }

    public String getBirth() { return birth; }

    public void setBirth(String birth) { this.birth = birth; }




    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

    public String getMassage() { return massage; }

    public void setMassage(String massage) { this.massage = massage; }

    public String getPicture() { return picture; }

    public void setPicture(String picture) { this.picture = picture; }
}

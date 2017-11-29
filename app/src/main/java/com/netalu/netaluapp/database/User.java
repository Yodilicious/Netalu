package com.netalu.netaluapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user",
indices =  { @Index(value = "id")}
)

public class User {

    @PrimaryKey(autoGenerate = true)
    public final int id;
    public String username;
    public String first_name;
    public String last_name;
    public String street_address;
    public String city;
    public String province;
    public String postal_code;
    public String email;
    public String password;

    public User(int id, String username, String first_name, String last_name, String street_address,
                String city, String province, String postal_code, String email, String password) {
        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.street_address = street_address;
        this.city = city;
        this.province = province;
        this.postal_code = postal_code;
        this.email = email;
        this.password = password;
    }
}

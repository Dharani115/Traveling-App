package com.android.trip_planning.Activity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Maycol Meza on 14/11/2017.
 */

public class UserProfile implements Parcelable {
    private int id;
    private String email;
    private String pass;
    private String nombre;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserProfile() {
    }

    public UserProfile(int id, String email, String username, String nombre) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
    }

    public UserProfile(int id, String correo, String username, String pass, String nombre) {
        this.id = id;
        this.email = correo;
        this.pass = pass;
        this.nombre = nombre;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    protected UserProfile(Parcel in) {
        id = in.readInt();
        email = in.readString();
        pass = in.readString();
        nombre = in.readString();
        image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(email);
        dest.writeString(pass);
        dest.writeString(nombre);
        dest.writeString(image);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserProfile> CREATOR = new Parcelable.Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };
}
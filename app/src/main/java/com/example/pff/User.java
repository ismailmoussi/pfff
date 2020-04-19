package com.example.pff;

public class User {
    private int id_user;
   private String Name;
   private String Adress_user;
   private String Tel_user;
   private String Image_user;
   private String Password_user;

    public User(int id_user, String name, String adress_user, String tel_user, String image_user, String password_user) {//avec Id
        this.id_user = id_user;
        Name = name;
        Adress_user = adress_user;
        Tel_user = tel_user;
        Image_user = image_user;
        Password_user = password_user;
    }
    public User( String name, String adress_user, String tel_user, String image_user, String password_user) {
        this.id_user = id_user;
        Name = name;
        Adress_user = adress_user;
        Tel_user = tel_user;
        Image_user = image_user;
        Password_user = password_user;
    }
    public int getId_user()
{
    return id_user;
}
public void setId_user()
{
    this.id_user=id_user;
}
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdress_user() {
        return Adress_user;
    }

    public void setAdress_user(String adress_user) {
        Adress_user = adress_user;
    }

    public String getTel_user() {
        return Tel_user;
    }

    public void setTel_user(String tel_user) {
        Tel_user = tel_user;
    }

    public String getImage_user() {
        return Image_user;
    }

    public void setImage_user(String image_user) {
        Image_user = image_user;
    }

    public String getPassword_user() {
        return Password_user;
    }

    public void setPassword_user(String password_user) {
        Password_user = password_user;
    }
}

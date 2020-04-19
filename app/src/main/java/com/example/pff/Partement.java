package com.example.pff;
public class Partement {
   private int id;
   private String image;
    private String superficier;
     private String Adresse;
     private int Nombre_de_chambre;
     private double prix_part;
    public Partement(int partId, Object adresse, int nombre_de_chambre, int prix_part, Object image)
    {

    }



    public String getImage()
    {
        return image;
    }


    public Partement(int id, String superficier, String adresse, int nombre_de_chambre,double prix_part,String image) {
        this.id = id;
        this.superficier = superficier;
        this.prix_part=prix_part;
        Adresse = adresse;
        Nombre_de_chambre = nombre_de_chambre;
        this.image=image;
    }


    public Partement( String superficier, String adresse, int nombre_de_chambre,double prix_part,String image) {

        this.superficier = superficier;
        this.prix_part=prix_part;
        Adresse = adresse;
        Nombre_de_chambre = nombre_de_chambre;
        this.image=image;
    }
    public double getPrix_part()
    {
     return prix_part;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrix_part(int prix_part) {
        this.prix_part = prix_part;
    }

    public Partement(String superficier, String adresse, int nombre_de_chambre) {
        this.superficier = superficier;
        this.Adresse = adresse;
        this.Nombre_de_chambre = nombre_de_chambre;
    }

    public Partement(int nombre_de_chambre,String superficier, String image) {
        this.superficier = superficier;
        this.image=image;
       this. Nombre_de_chambre = nombre_de_chambre;
    }

    public Partement(String superficier, String adresse) {
        this.superficier = superficier;
        Adresse = adresse;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSuperficier() {
        return superficier;
    }

    public void setSuperficier(String superficier) {
        this.superficier = superficier;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public int getNombre_de_chambre() {
        return Nombre_de_chambre;
    }

    public void setNombre_de_chambre(int nombre_de_chambre) {
        Nombre_de_chambre = nombre_de_chambre;
    }
}

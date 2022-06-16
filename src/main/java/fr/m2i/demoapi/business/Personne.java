package fr.m2i.demoapi.business;

public class Personne {
    
    private long id;
    private String prenom;
    private String nom;

    public Personne() {
    }
    public Personne(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", prenom=" + prenom + ", nom=" + nom + '}';
    }
}

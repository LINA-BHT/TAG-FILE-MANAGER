/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.ArrayList;
//rendre la class file Serializable
import java.io.*;

/**
 *
 * @author linah
 */
public class file implements Serializable{
    private  int id;
    private String titre;
    private String auteur;
    private String resumer;
    private String commentaire;
    ArrayList tags = new ArrayList();
    private String nom_fichier; 
    private String emplacement; 
    private String list_tags;
    private String type;



    public file() {
    }

    public file(String titre, String auteur, String resumer, String commentaire, String nom_fichier, String emplacement, String list_tags, String type) {
        this.titre = titre;
        this.auteur = auteur;
        this.resumer = resumer;
        this.commentaire = commentaire;
        this.nom_fichier = nom_fichier;
        this.emplacement = emplacement;
        this.list_tags = list_tags;
        this.type = type;
    }

    public file(int id, String titre, String auteur, String resumer, String commentaire, String nom_fichier, String emplacement, String list_tags, String type) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.resumer = resumer;
        this.commentaire = commentaire;
        this.nom_fichier = nom_fichier;
        this.emplacement = emplacement;
        this.list_tags = list_tags;
        this.type = type;
    }
    
    

    public file(String titre, String auteur, String resumer, String commentaire, String nom_fichier, String emplacement ,ArrayList tags) {
        this.titre = titre;
        this.auteur = auteur;
        this.resumer = resumer;
        this.commentaire = commentaire;
        this.nom_fichier = nom_fichier;
        this.emplacement = emplacement;
        this.tags=new ArrayList();
    }

    public file(int id, String titre, String auteur, String resumer, String commentaire, String nom_fichier, String emplacement,String list_tags) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.resumer = resumer;
        this.commentaire = commentaire;
        this.nom_fichier = nom_fichier;
        this.emplacement = emplacement;
        this.list_tags=list_tags;
    }

    public String getList_tags() {
        return list_tags;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setList_tags(String list_tags) {
        this.list_tags = list_tags;
    }
    
  

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getResumer() {
        return resumer;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public ArrayList getTags() {
        return tags;
    }

    public String getNom_fichier() {
        return nom_fichier;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setResumer(String resumer) {
        this.resumer = resumer;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setTags(ArrayList tags) {
        this.tags = tags;
    }

    public void setNom_fichier(String nom_fichier) {
        this.nom_fichier = nom_fichier;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public int getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    

    @Override
    public String toString() {
        return "fichier " + nom_fichier + " :\n " + "\t -titre=" + "\t "+ titre + "\n"+ "\t - auteur=" + "\t "+ auteur + "\n"+ "\t -resumer=" + "\t "+ resumer + "\n"+ "\t -commentaire=" + "\t "+ commentaire+ "\n" + "\t -emplacement=" + "\t "+ emplacement+ "\n" + "\t -list_tags=" + "\t "+ list_tags + "\n"+  "\n";
    }
    

    
}

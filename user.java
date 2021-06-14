/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author linah
 */
public class user {
    
    /**
     *
     */
     int  id ;
    String nom;
    String prenom;
    String login;
    String mdp;
    String email;
    String status ;

    public user() {
    }

    public user(int id, String nom, String prenom, String login, String mdp, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.email = email;
    }

    public user(String nom, String prenom, String login, String mdp, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.email = email;
        this.status = "simple user" ;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "user : \n" + "\t -id="+"\t" + id + "\n"+" \t -nom=" +"\t"+ nom + "\n"+ "\t -prenom=" +"\t"+ prenom + "\n"+ "\t -login=" + login + "\n"+ "\t -email=" +"\t"+ email + "\n"+ '}';
    }
    
    
}

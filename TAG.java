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
public class TAG {
    String nom;
    int nb_users;

    public TAG(String nom, int nb_users) {
        this.nom = nom;
        this.nb_users = nb_users;
    }

    public TAG() {
    }

    public String getNom() {
        return nom;
    }

    public int getNb_users() {
        return nb_users;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNb_users(int nb_users) {
        this.nb_users = nb_users;
    }
    
    
}

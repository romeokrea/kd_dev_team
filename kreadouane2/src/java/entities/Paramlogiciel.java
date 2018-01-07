/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KBC
 */
@Entity
@Table(name = "paramlogiciel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paramlogiciel.findAll", query = "SELECT p FROM Paramlogiciel p"),
    @NamedQuery(name = "Paramlogiciel.findById", query = "SELECT p FROM Paramlogiciel p WHERE p.id = :id"),
    @NamedQuery(name = "Paramlogiciel.findByLien", query = "SELECT p FROM Paramlogiciel p WHERE p.lien = :lien"),
    @NamedQuery(name = "Paramlogiciel.findByServeur", query = "SELECT p FROM Paramlogiciel p WHERE p.serveur = :serveur"),
    @NamedQuery(name = "Paramlogiciel.findByNombd", query = "SELECT p FROM Paramlogiciel p WHERE p.nombd = :nombd"),
    @NamedQuery(name = "Paramlogiciel.findByUser", query = "SELECT p FROM Paramlogiciel p WHERE p.user = :user"),
    @NamedQuery(name = "Paramlogiciel.findByPassword", query = "SELECT p FROM Paramlogiciel p WHERE p.password = :password"),
    @NamedQuery(name = "Paramlogiciel.findByLiendesauvegarde", query = "SELECT p FROM Paramlogiciel p WHERE p.liendesauvegarde = :liendesauvegarde")})
public class Paramlogiciel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 254)
    @Column(name = "lien")
    private String lien;
    @Size(max = 254)
    @Column(name = "serveur")
    private String serveur;
    @Size(max = 254)
    @Column(name = "nombd")
    private String nombd;
    @Size(max = 254)
    @Column(name = "user")
    private String user;
    @Size(max = 254)
    @Column(name = "password")
    private String password;
    @Size(max = 254)
    @Column(name = "liendesauvegarde")
    private String liendesauvegarde;

    public Paramlogiciel() {
    }

    public Paramlogiciel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getServeur() {
        return serveur;
    }

    public void setServeur(String serveur) {
        this.serveur = serveur;
    }

    public String getNombd() {
        return nombd;
    }

    public void setNombd(String nombd) {
        this.nombd = nombd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLiendesauvegarde() {
        return liendesauvegarde;
    }

    public void setLiendesauvegarde(String liendesauvegarde) {
        this.liendesauvegarde = liendesauvegarde;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paramlogiciel)) {
            return false;
        }
        Paramlogiciel other = (Paramlogiciel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Paramlogiciel[ id=" + id + " ]";
    }
    
}

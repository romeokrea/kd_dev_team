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
@Table(name = "paramsociete")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paramsociete.findAll", query = "SELECT p FROM Paramsociete p"),
    @NamedQuery(name = "Paramsociete.findById", query = "SELECT p FROM Paramsociete p WHERE p.id = :id"),
    @NamedQuery(name = "Paramsociete.findByNom", query = "SELECT p FROM Paramsociete p WHERE p.nom = :nom"),
    @NamedQuery(name = "Paramsociete.findByAdresse", query = "SELECT p FROM Paramsociete p WHERE p.adresse = :adresse"),
    @NamedQuery(name = "Paramsociete.findByEmail", query = "SELECT p FROM Paramsociete p WHERE p.email = :email"),
    @NamedQuery(name = "Paramsociete.findByNumerocontribuable", query = "SELECT p FROM Paramsociete p WHERE p.numerocontribuable = :numerocontribuable"),
    @NamedQuery(name = "Paramsociete.findByRegimesociete", query = "SELECT p FROM Paramsociete p WHERE p.regimesociete = :regimesociete"),
    @NamedQuery(name = "Paramsociete.findByValeurdroitdaccise", query = "SELECT p FROM Paramsociete p WHERE p.valeurdroitdaccise = :valeurdroitdaccise")})
public class Paramsociete implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "adresse")
    private String adresse;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 254)
    @Column(name = "email")
    private String email;
    @Size(max = 254)
    @Column(name = "numerocontribuable")
    private String numerocontribuable;
    @Size(max = 254)
    @Column(name = "regimesociete")
    private String regimesociete;
    @Size(max = 254)
    @Column(name = "valeurdroitdaccise")
    private String valeurdroitdaccise;

    public Paramsociete() {
    }

    public Paramsociete(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumerocontribuable() {
        return numerocontribuable;
    }

    public void setNumerocontribuable(String numerocontribuable) {
        this.numerocontribuable = numerocontribuable;
    }

    public String getRegimesociete() {
        return regimesociete;
    }

    public void setRegimesociete(String regimesociete) {
        this.regimesociete = regimesociete;
    }

    public String getValeurdroitdaccise() {
        return valeurdroitdaccise;
    }

    public void setValeurdroitdaccise(String valeurdroitdaccise) {
        this.valeurdroitdaccise = valeurdroitdaccise;
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
        if (!(object instanceof Paramsociete)) {
            return false;
        }
        Paramsociete other = (Paramsociete) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Paramsociete[ id=" + id + " ]";
    }
    
}

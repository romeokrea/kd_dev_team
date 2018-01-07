/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "assureur", catalog = "kreadouane2_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assureur.findAll", query = "SELECT a FROM Assureur a"),
    @NamedQuery(name = "Assureur.findById", query = "SELECT a FROM Assureur a WHERE a.id = :id"),
    @NamedQuery(name = "Assureur.findByNamecompany", query = "SELECT a FROM Assureur a WHERE a.namecompany = :namecompany"),
    @NamedQuery(name = "Assureur.findByAdressecompany", query = "SELECT a FROM Assureur a WHERE a.adressecompany = :adressecompany"),
    @NamedQuery(name = "Assureur.findByPhonecontribuable", query = "SELECT a FROM Assureur a WHERE a.phonecontribuable = :phonecontribuable"),
    @NamedQuery(name = "Assureur.findByEmailcompany", query = "SELECT a FROM Assureur a WHERE a.emailcompany = :emailcompany"),
    @NamedQuery(name = "Assureur.findByStatus", query = "SELECT a FROM Assureur a WHERE a.status = :status")})
public class Assureur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 254)
    @Column(name = "namecompany", length = 254)
    private String namecompany;
    @Size(max = 254)
    @Column(name = "adressecompany", length = 254)
    private String adressecompany;
    @Size(max = 254)
    @Column(name = "phonecontribuable", length = 254)
    private String phonecontribuable;
    @Size(max = 254)
    @Column(name = "emailcompany", length = 254)
    private String emailcompany;
    @Size(max = 254)
    @Column(name = "status", length = 254)
    private String status;
    @OneToMany(mappedBy = "assId")
    private Collection<Contact> contactCollection;

    public Assureur() {
    }

    public Assureur(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamecompany() {
        return namecompany;
    }

    public void setNamecompany(String namecompany) {
        this.namecompany = namecompany;
    }

    public String getAdressecompany() {
        return adressecompany;
    }

    public void setAdressecompany(String adressecompany) {
        this.adressecompany = adressecompany;
    }

    public String getPhonecontribuable() {
        return phonecontribuable;
    }

    public void setPhonecontribuable(String phonecontribuable) {
        this.phonecontribuable = phonecontribuable;
    }

    public String getEmailcompany() {
        return emailcompany;
    }

    public void setEmailcompany(String emailcompany) {
        this.emailcompany = emailcompany;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Contact> getContactCollection() {
        return contactCollection;
    }

    public void setContactCollection(Collection<Contact> contactCollection) {
        this.contactCollection = contactCollection;
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
        if (!(object instanceof Assureur)) {
            return false;
        }
        Assureur other = (Assureur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Assureur[ id=" + id + " ]";
    }
    
}

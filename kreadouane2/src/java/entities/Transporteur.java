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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "transporteur", catalog = "kreadouane2_db", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transporteur.findAll", query = "SELECT t FROM Transporteur t"),
    @NamedQuery(name = "Transporteur.findById", query = "SELECT t FROM Transporteur t WHERE t.id = :id"),
    @NamedQuery(name = "Transporteur.findByNamecompany", query = "SELECT t FROM Transporteur t WHERE t.namecompany = :namecompany"),
    @NamedQuery(name = "Transporteur.findByAdressecompany", query = "SELECT t FROM Transporteur t WHERE t.adressecompany = :adressecompany"),
    @NamedQuery(name = "Transporteur.findByPhonecompany", query = "SELECT t FROM Transporteur t WHERE t.phonecompany = :phonecompany"),
    @NamedQuery(name = "Transporteur.findByEmailcompany", query = "SELECT t FROM Transporteur t WHERE t.emailcompany = :emailcompany"),
    @NamedQuery(name = "Transporteur.findByStatus", query = "SELECT t FROM Transporteur t WHERE t.status = :status")})
public class Transporteur implements Serializable {
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
    @Column(name = "phonecompany", length = 254)
    private String phonecompany;
    @Size(max = 254)
    @Column(name = "emailcompany", length = 254)
    private String emailcompany;
    @Size(max = 254)
    @Column(name = "status", length = 254)
    private String status;
    @OneToMany(mappedBy = "traId")
    private Collection<Contact> contactCollection;

    public Transporteur() {
    }

    public Transporteur(Integer id) {
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

    public String getPhonecompany() {
        return phonecompany;
    }

    public void setPhonecompany(String phonecompany) {
        this.phonecompany = phonecompany;
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
        if (!(object instanceof Transporteur)) {
            return false;
        }
        Transporteur other = (Transporteur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Transporteur[ id=" + id + " ]";
    }
    
}

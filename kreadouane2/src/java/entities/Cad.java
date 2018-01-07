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
@Table(name = "cad", catalog = "kreadouane2_db", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cad.findAll", query = "SELECT c FROM Cad c"),
    @NamedQuery(name = "Cad.findById", query = "SELECT c FROM Cad c WHERE c.id = :id"),
    @NamedQuery(name = "Cad.findByNamecompany", query = "SELECT c FROM Cad c WHERE c.namecompany = :namecompany"),
    @NamedQuery(name = "Cad.findByAdressecompany", query = "SELECT c FROM Cad c WHERE c.adressecompany = :adressecompany"),
    @NamedQuery(name = "Cad.findByPhonecontribluable", query = "SELECT c FROM Cad c WHERE c.phonecontribluable = :phonecontribluable"),
    @NamedQuery(name = "Cad.findByEmailcompany", query = "SELECT c FROM Cad c WHERE c.emailcompany = :emailcompany"),
    @NamedQuery(name = "Cad.findByStatus", query = "SELECT c FROM Cad c WHERE c.status = :status")})
public class Cad implements Serializable {
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
    @Column(name = "phonecontribluable", length = 254)
    private String phonecontribluable;
    @Size(max = 254)
    @Column(name = "emailcompany", length = 254)
    private String emailcompany;
    @Size(max = 254)
    @Column(name = "status", length = 254)
    private String status;
    @OneToMany(mappedBy = "cadId")
    private Collection<Contact> contactCollection;

    public Cad() {
    }

    public Cad(Integer id) {
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

    public String getPhonecontribluable() {
        return phonecontribluable;
    }

    public void setPhonecontribluable(String phonecontribluable) {
        this.phonecontribluable = phonecontribluable;
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
        if (!(object instanceof Cad)) {
            return false;
        }
        Cad other = (Cad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cad[ id=" + id + " ]";
    }
    
}

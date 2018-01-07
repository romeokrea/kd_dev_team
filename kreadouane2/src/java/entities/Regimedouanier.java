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
@Table(name = "regimedouanier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Regimedouanier.findAll", query = "SELECT r FROM Regimedouanier r"),
    @NamedQuery(name = "Regimedouanier.findById", query = "SELECT r FROM Regimedouanier r WHERE r.id = :id"),
    @NamedQuery(name = "Regimedouanier.findByCode", query = "SELECT r FROM Regimedouanier r WHERE r.code = :code"),
    @NamedQuery(name = "Regimedouanier.findByLibelle", query = "SELECT r FROM Regimedouanier r WHERE r.libelle = :libelle"),
    @NamedQuery(name = "Regimedouanier.findByStatus", query = "SELECT r FROM Regimedouanier r WHERE r.status = :status")})
public class Regimedouanier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 254)
    @Column(name = "code")
    private String code;
    @Size(max = 254)
    @Column(name = "libelle")
    private String libelle;
    @Size(max = 254)
    @Column(name = "status")
    private String status;

    public Regimedouanier() {
    }

    public Regimedouanier(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof Regimedouanier)) {
            return false;
        }
        Regimedouanier other = (Regimedouanier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Regimedouanier[ id=" + id + " ]";
    }
    
}

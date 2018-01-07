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
@Table(name = "modelledeclaration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelledeclaration.findAll", query = "SELECT m FROM Modelledeclaration m"),
    @NamedQuery(name = "Modelledeclaration.findById", query = "SELECT m FROM Modelledeclaration m WHERE m.id = :id"),
    @NamedQuery(name = "Modelledeclaration.findByCode", query = "SELECT m FROM Modelledeclaration m WHERE m.code = :code"),
    @NamedQuery(name = "Modelledeclaration.findByLibelle", query = "SELECT m FROM Modelledeclaration m WHERE m.libelle = :libelle"),
    @NamedQuery(name = "Modelledeclaration.findByStatus", query = "SELECT m FROM Modelledeclaration m WHERE m.status = :status")})
public class Modelledeclaration implements Serializable {
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

    public Modelledeclaration() {
    }

    public Modelledeclaration(Integer id) {
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
        if (!(object instanceof Modelledeclaration)) {
            return false;
        }
        Modelledeclaration other = (Modelledeclaration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Modelledeclaration[ id=" + id + " ]";
    }
    
}

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
@Table(name = "codeadditionnels")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Codeadditionnels.findAll", query = "SELECT c FROM Codeadditionnels c"),
    @NamedQuery(name = "Codeadditionnels.findById", query = "SELECT c FROM Codeadditionnels c WHERE c.id = :id"),
    @NamedQuery(name = "Codeadditionnels.findByCategorie", query = "SELECT c FROM Codeadditionnels c WHERE c.categorie = :categorie"),
    @NamedQuery(name = "Codeadditionnels.findByDetails", query = "SELECT c FROM Codeadditionnels c WHERE c.details = :details"),
    @NamedQuery(name = "Codeadditionnels.findByStatus", query = "SELECT c FROM Codeadditionnels c WHERE c.status = :status")})
public class Codeadditionnels implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 254)
    @Column(name = "categorie")
    private String categorie;
    @Size(max = 254)
    @Column(name = "details")
    private String details;
    @Size(max = 254)
    @Column(name = "status")
    private String status;

    public Codeadditionnels() {
    }

    public Codeadditionnels(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
        if (!(object instanceof Codeadditionnels)) {
            return false;
        }
        Codeadditionnels other = (Codeadditionnels) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Codeadditionnels[ id=" + id + " ]";
    }
    
}

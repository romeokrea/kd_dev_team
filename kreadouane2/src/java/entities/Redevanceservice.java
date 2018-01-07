/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "redevanceservice", catalog = "kreadouane2_db", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Redevanceservice.findAll", query = "SELECT r FROM Redevanceservice r"),
    @NamedQuery(name = "Redevanceservice.findById", query = "SELECT r FROM Redevanceservice r WHERE r.id = :id"),
    @NamedQuery(name = "Redevanceservice.findByScanning", query = "SELECT r FROM Redevanceservice r WHERE r.scanning = :scanning"),
    @NamedQuery(name = "Redevanceservice.findByGuce", query = "SELECT r FROM Redevanceservice r WHERE r.guce = :guce"),
    @NamedQuery(name = "Redevanceservice.findByPad", query = "SELECT r FROM Redevanceservice r WHERE r.pad = :pad"),
    @NamedQuery(name = "Redevanceservice.findByTimbresurbl", query = "SELECT r FROM Redevanceservice r WHERE r.timbresurbl = :timbresurbl"),
    @NamedQuery(name = "Redevanceservice.findByImpressionsurbl", query = "SELECT r FROM Redevanceservice r WHERE r.impressionsurbl = :impressionsurbl"),
    @NamedQuery(name = "Redevanceservice.findByAcconage", query = "SELECT r FROM Redevanceservice r WHERE r.acconage = :acconage"),
    @NamedQuery(name = "Redevanceservice.findByAssurance", query = "SELECT r FROM Redevanceservice r WHERE r.assurance = :assurance"),
    @NamedQuery(name = "Redevanceservice.findByPhytosanitaire", query = "SELECT r FROM Redevanceservice r WHERE r.phytosanitaire = :phytosanitaire"),
    @NamedQuery(name = "Redevanceservice.findByControlsanitaire", query = "SELECT r FROM Redevanceservice r WHERE r.controlsanitaire = :controlsanitaire"),
    @NamedQuery(name = "Redevanceservice.findByCommisionsurtransit", query = "SELECT r FROM Redevanceservice r WHERE r.commisionsurtransit = :commisionsurtransit"),
    @NamedQuery(name = "Redevanceservice.findByCommisionsurdebourse", query = "SELECT r FROM Redevanceservice r WHERE r.commisionsurdebourse = :commisionsurdebourse"),
    @NamedQuery(name = "Redevanceservice.findByHad", query = "SELECT r FROM Redevanceservice r WHERE r.had = :had"),
    @NamedQuery(name = "Redevanceservice.findByOuverturedossier", query = "SELECT r FROM Redevanceservice r WHERE r.ouverturedossier = :ouverturedossier"),
    @NamedQuery(name = "Redevanceservice.findByTravailextralegal", query = "SELECT r FROM Redevanceservice r WHERE r.travailextralegal = :travailextralegal"),
    @NamedQuery(name = "Redevanceservice.findByCaution", query = "SELECT r FROM Redevanceservice r WHERE r.caution = :caution"),
    @NamedQuery(name = "Redevanceservice.findByTransport", query = "SELECT r FROM Redevanceservice r WHERE r.transport = :transport")})
public class Redevanceservice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "scanning")
    private Integer scanning;
    @Column(name = "guce")
    private Integer guce;
    @Column(name = "pad")
    private Integer pad;
    @Column(name = "timbresurbl")
    private Integer timbresurbl;
    @Column(name = "impressionsurbl")
    private Integer impressionsurbl;
    @Column(name = "acconage")
    private Integer acconage;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "assurance", precision = 17, scale = 17)
    private Double assurance;
    @Column(name = "phytosanitaire")
    private Integer phytosanitaire;
    @Column(name = "controlsanitaire")
    private Integer controlsanitaire;
    @Column(name = "commisionsurtransit")
    private Integer commisionsurtransit;
    @Column(name = "commisionsurdebourse")
    private Integer commisionsurdebourse;
    @Column(name = "had")
    private Integer had;
    @Column(name = "ouverturedossier")
    private Integer ouverturedossier;
    @Column(name = "travailextralegal")
    private Integer travailextralegal;
    @Column(name = "caution")
    private Integer caution;
    @Column(name = "transport", precision = 17, scale = 17)
    private Double transport;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "redId")
    private Collection<Cotation> cotationCollection;

    public Redevanceservice() {
    }

    public Redevanceservice(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScanning() {
        return scanning;
    }

    public void setScanning(Integer scanning) {
        this.scanning = scanning;
    }

    public Integer getGuce() {
        return guce;
    }

    public void setGuce(Integer guce) {
        this.guce = guce;
    }

    public Integer getPad() {
        return pad;
    }

    public void setPad(Integer pad) {
        this.pad = pad;
    }

    public Integer getTimbresurbl() {
        return timbresurbl;
    }

    public void setTimbresurbl(Integer timbresurbl) {
        this.timbresurbl = timbresurbl;
    }

    public Integer getImpressionsurbl() {
        return impressionsurbl;
    }

    public void setImpressionsurbl(Integer impressionsurbl) {
        this.impressionsurbl = impressionsurbl;
    }

    public Integer getAcconage() {
        return acconage;
    }

    public void setAcconage(Integer acconage) {
        this.acconage = acconage;
    }

    public Double getAssurance() {
        return assurance;
    }

    public void setAssurance(Double assurance) {
        this.assurance = assurance;
    }

    public Integer getPhytosanitaire() {
        return phytosanitaire;
    }

    public void setPhytosanitaire(Integer phytosanitaire) {
        this.phytosanitaire = phytosanitaire;
    }

    public Integer getControlsanitaire() {
        return controlsanitaire;
    }

    public void setControlsanitaire(Integer controlsanitaire) {
        this.controlsanitaire = controlsanitaire;
    }

    public Integer getCommisionsurtransit() {
        return commisionsurtransit;
    }

    public void setCommisionsurtransit(Integer commisionsurtransit) {
        this.commisionsurtransit = commisionsurtransit;
    }

    public Integer getCommisionsurdebourse() {
        return commisionsurdebourse;
    }

    public void setCommisionsurdebourse(Integer commisionsurdebourse) {
        this.commisionsurdebourse = commisionsurdebourse;
    }

    public Integer getHad() {
        return had;
    }

    public void setHad(Integer had) {
        this.had = had;
    }

    public Integer getOuverturedossier() {
        return ouverturedossier;
    }

    public void setOuverturedossier(Integer ouverturedossier) {
        this.ouverturedossier = ouverturedossier;
    }

    public Integer getTravailextralegal() {
        return travailextralegal;
    }

    public void setTravailextralegal(Integer travailextralegal) {
        this.travailextralegal = travailextralegal;
    }

    public Integer getCaution() {
        return caution;
    }

    public void setCaution(Integer caution) {
        this.caution = caution;
    }

    public Double getTransport() {
        return transport;
    }

    public void setTransport(Double transport) {
        this.transport = transport;
    }

    @XmlTransient
    public Collection<Cotation> getCotationCollection() {
        return cotationCollection;
    }

    public void setCotationCollection(Collection<Cotation> cotationCollection) {
        this.cotationCollection = cotationCollection;
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
        if (!(object instanceof Redevanceservice)) {
            return false;
        }
        Redevanceservice other = (Redevanceservice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Redevanceservice[ id=" + id + " ]";
    }
    
}

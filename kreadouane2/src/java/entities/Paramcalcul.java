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
@Table(name = "paramcalcul")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paramcalcul.findAll", query = "SELECT p FROM Paramcalcul p"),
    @NamedQuery(name = "Paramcalcul.findById", query = "SELECT p FROM Paramcalcul p WHERE p.id = :id"),
    @NamedQuery(name = "Paramcalcul.findByGuce", query = "SELECT p FROM Paramcalcul p WHERE p.guce = :guce"),
    @NamedQuery(name = "Paramcalcul.findByPhytosanitaire", query = "SELECT p FROM Paramcalcul p WHERE p.phytosanitaire = :phytosanitaire"),
    @NamedQuery(name = "Paramcalcul.findByTimbrebl", query = "SELECT p FROM Paramcalcul p WHERE p.timbrebl = :timbrebl"),
    @NamedQuery(name = "Paramcalcul.findByFraisouverturedossier", query = "SELECT p FROM Paramcalcul p WHERE p.fraisouverturedossier = :fraisouverturedossier"),
    @NamedQuery(name = "Paramcalcul.findByTravailextralegal", query = "SELECT p FROM Paramcalcul p WHERE p.travailextralegal = :travailextralegal"),
    @NamedQuery(name = "Paramcalcul.findByCaution", query = "SELECT p FROM Paramcalcul p WHERE p.caution = :caution"),
    @NamedQuery(name = "Paramcalcul.findByControlsanitaire", query = "SELECT p FROM Paramcalcul p WHERE p.controlsanitaire = :controlsanitaire"),
    @NamedQuery(name = "Paramcalcul.findByCommissiondebource", query = "SELECT p FROM Paramcalcul p WHERE p.commissiondebource = :commissiondebource"),
    @NamedQuery(name = "Paramcalcul.findByCommissiontransit", query = "SELECT p FROM Paramcalcul p WHERE p.commissiontransit = :commissiontransit"),
    @NamedQuery(name = "Paramcalcul.findByImpressionbl", query = "SELECT p FROM Paramcalcul p WHERE p.impressionbl = :impressionbl"),
    @NamedQuery(name = "Paramcalcul.findByPad", query = "SELECT p FROM Paramcalcul p WHERE p.pad = :pad"),
    @NamedQuery(name = "Paramcalcul.findByScanning", query = "SELECT p FROM Paramcalcul p WHERE p.scanning = :scanning")})
public class Paramcalcul implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 254)
    @Column(name = "guce")
    private String guce;
    @Size(max = 254)
    @Column(name = "phytosanitaire")
    private String phytosanitaire;
    @Size(max = 254)
    @Column(name = "timbrebl")
    private String timbrebl;
    @Size(max = 254)
    @Column(name = "fraisouverturedossier")
    private String fraisouverturedossier;
    @Size(max = 254)
    @Column(name = "travailextralegal")
    private String travailextralegal;
    @Size(max = 254)
    @Column(name = "caution")
    private String caution;
    @Size(max = 254)
    @Column(name = "controlsanitaire")
    private String controlsanitaire;
    @Size(max = 254)
    @Column(name = "commissiondebource")
    private String commissiondebource;
    @Size(max = 254)
    @Column(name = "commissiontransit")
    private String commissiontransit;
    @Size(max = 254)
    @Column(name = "impressionbl")
    private String impressionbl;
    @Size(max = 254)
    @Column(name = "pad")
    private String pad;
    @Size(max = 254)
    @Column(name = "scanning")
    private String scanning;

    public Paramcalcul() {
    }

    public Paramcalcul(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuce() {
        return guce;
    }

    public void setGuce(String guce) {
        this.guce = guce;
    }

    public String getPhytosanitaire() {
        return phytosanitaire;
    }

    public void setPhytosanitaire(String phytosanitaire) {
        this.phytosanitaire = phytosanitaire;
    }

    public String getTimbrebl() {
        return timbrebl;
    }

    public void setTimbrebl(String timbrebl) {
        this.timbrebl = timbrebl;
    }

    public String getFraisouverturedossier() {
        return fraisouverturedossier;
    }

    public void setFraisouverturedossier(String fraisouverturedossier) {
        this.fraisouverturedossier = fraisouverturedossier;
    }

    public String getTravailextralegal() {
        return travailextralegal;
    }

    public void setTravailextralegal(String travailextralegal) {
        this.travailextralegal = travailextralegal;
    }

    public String getCaution() {
        return caution;
    }

    public void setCaution(String caution) {
        this.caution = caution;
    }

    public String getControlsanitaire() {
        return controlsanitaire;
    }

    public void setControlsanitaire(String controlsanitaire) {
        this.controlsanitaire = controlsanitaire;
    }

    public String getCommissiondebource() {
        return commissiondebource;
    }

    public void setCommissiondebource(String commissiondebource) {
        this.commissiondebource = commissiondebource;
    }

    public String getCommissiontransit() {
        return commissiontransit;
    }

    public void setCommissiontransit(String commissiontransit) {
        this.commissiontransit = commissiontransit;
    }

    public String getImpressionbl() {
        return impressionbl;
    }

    public void setImpressionbl(String impressionbl) {
        this.impressionbl = impressionbl;
    }

    public String getPad() {
        return pad;
    }

    public void setPad(String pad) {
        this.pad = pad;
    }

    public String getScanning() {
        return scanning;
    }

    public void setScanning(String scanning) {
        this.scanning = scanning;
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
        if (!(object instanceof Paramcalcul)) {
            return false;
        }
        Paramcalcul other = (Paramcalcul) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Paramcalcul[ id=" + id + " ]";
    }
    
}

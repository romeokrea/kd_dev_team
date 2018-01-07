/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "cotation", catalog = "kreadouane2_db", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotation.findMax", query = "SELECT MAX(c.id) FROM Cotation c"),
    @NamedQuery(name = "Cotation.findAll", query = "SELECT c FROM Cotation c ORDER BY c.id DESC"),
    @NamedQuery(name = "Cotation.findById", query = "SELECT c FROM Cotation c WHERE c.id = :id"),
    @NamedQuery(name = "Cotation.findByMontemp", query = "SELECT c FROM Cotation c WHERE c.montemp = :montemp"),
    @NamedQuery(name = "Cotation.findByConteneur", query = "SELECT c FROM Cotation c WHERE c.conteneur = :conteneur"),
    @NamedQuery(name = "Cotation.findByReferance", query = "SELECT c FROM Cotation c WHERE c.referance = :referance"),
    @NamedQuery(name = "Cotation.findByPosition", query = "SELECT c FROM Cotation c WHERE c.position = :position"),
    @NamedQuery(name = "Cotation.findByType", query = "SELECT c FROM Cotation c WHERE c.type = :type"),
    @NamedQuery(name = "Cotation.findByDatecreation", query = "SELECT c FROM Cotation c WHERE c.datecreation = :datecreation"),
    @NamedQuery(name = "Cotation.findByStatus", query = "SELECT c FROM Cotation c WHERE c.status = :status"),
    @NamedQuery(name = "Cotation.findByValeurimposable", query = "SELECT c FROM Cotation c WHERE c.valeurimposable = :valeurimposable"),
    @NamedQuery(name = "Cotation.findByDroitdedouane", query = "SELECT c FROM Cotation c WHERE c.droitdedouane = :droitdedouane"),
    @NamedQuery(name = "Cotation.findByDroitdaccise", query = "SELECT c FROM Cotation c WHERE c.droitdaccise = :droitdaccise"),
    @NamedQuery(name = "Cotation.findByTvacac", query = "SELECT c FROM Cotation c WHERE c.tvacac = :tvacac"),
    @NamedQuery(name = "Cotation.findByPrecomptesurachat", query = "SELECT c FROM Cotation c WHERE c.precomptesurachat = :precomptesurachat"),
    @NamedQuery(name = "Cotation.findByCci", query = "SELECT c FROM Cotation c WHERE c.cci = :cci"),
    @NamedQuery(name = "Cotation.findByTci", query = "SELECT c FROM Cotation c WHERE c.tci = :tci"),
    @NamedQuery(name = "Cotation.findByT0", query = "SELECT c FROM Cotation c WHERE c.t0 = :t0"),
    @NamedQuery(name = "Cotation.findByCia", query = "SELECT c FROM Cotation c WHERE c.cia = :cia"),
    @NamedQuery(name = "Cotation.findByRi", query = "SELECT c FROM Cotation c WHERE c.ri = :ri"),
    @NamedQuery(name = "Cotation.findByTaxesgs", query = "SELECT c FROM Cotation c WHERE c.taxesgs = :taxesgs"),
    @NamedQuery(name = "Cotation.findByTaxepecea", query = "SELECT c FROM Cotation c WHERE c.taxepecea = :taxepecea")})
public class Cotation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montemp", precision = 17, scale = 17)
    private Double montemp;
    @Size(max = 254)
    @Column(name = "conteneur", length = 254)
    private String conteneur;
    @Size(max = 254)
    @Column(name = "referance", length = 254)
    private String referance;
    @Size(max = 254)
    @Column(name = "position", length = 254)
    private String position;
    @Size(max = 254)
    @Column(name = "type", length = 254)
    private String type;
    @Column(name = "datecreation")
    @Temporal(TemporalType.DATE)
    private Date datecreation;
    @Size(max = 254)
    @Column(name = "status", length = 254)
    private String status;
    @Column(name = "valeurimposable", precision = 17, scale = 17)
    private Double valeurimposable;
    @Column(name = "droitdedouane", precision = 17, scale = 17)
    private Double droitdedouane;
    @Column(name = "droitdaccise", precision = 17, scale = 17)
    private Double droitdaccise;
    @Column(name = "tvacac", precision = 17, scale = 17)
    private Double tvacac;
    @Column(name = "precomptesurachat", precision = 17, scale = 17)
    private Double precomptesurachat;
    @Column(name = "cci", precision = 17, scale = 17)
    private Double cci;
    @Column(name = "tci", precision = 17, scale = 17)
    private Double tci;
    @Column(name = "t0", precision = 17, scale = 17)
    private Double t0;
    @Column(name = "cia", precision = 17, scale = 17)
    private Double cia;
    @Column(name = "ri", precision = 17, scale = 17)
    private Double ri;
    @Column(name = "taxesgs", precision = 17, scale = 17)
    private Double taxesgs;
    @Column(name = "taxepecea", precision = 17, scale = 17)
    private Double taxepecea;
    @JoinColumn(name = "art_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Article artId;
    @JoinColumn(name = "red_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Redevanceservice redId;
    @JoinColumn(name = "uti_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Utilisateur utiId;

    public Cotation() {
    }

    public Cotation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMontemp() {
        return montemp;
    }

    public void setMontemp(Double montemp) {
        this.montemp = montemp;
    }

    public String getConteneur() {
        return conteneur;
    }

    public void setConteneur(String conteneur) {
        this.conteneur = conteneur;
    }

    public String getReferance() {
        return referance;
    }

    public void setReferance(String referance) {
        this.referance = referance;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getValeurimposable() {
        return valeurimposable;
    }

    public void setValeurimposable(Double valeurimposable) {
        this.valeurimposable = valeurimposable;
    }

    public Double getDroitdedouane() {
        return droitdedouane;
    }

    public void setDroitdedouane(Double droitdedouane) {
        this.droitdedouane = droitdedouane;
    }

    public Double getDroitdaccise() {
        return droitdaccise;
    }

    public void setDroitdaccise(Double droitdaccise) {
        this.droitdaccise = droitdaccise;
    }

    public Double getTvacac() {
        return tvacac;
    }

    public void setTvacac(Double tvacac) {
        this.tvacac = tvacac;
    }

    public Double getPrecomptesurachat() {
        return precomptesurachat;
    }

    public void setPrecomptesurachat(Double precomptesurachat) {
        this.precomptesurachat = precomptesurachat;
    }

    public Double getCci() {
        return cci;
    }

    public void setCci(Double cci) {
        this.cci = cci;
    }

    public Double getTci() {
        return tci;
    }

    public void setTci(Double tci) {
        this.tci = tci;
    }

    public Double getT0() {
        return t0;
    }

    public void setT0(Double t0) {
        this.t0 = t0;
    }

    public Double getCia() {
        return cia;
    }

    public void setCia(Double cia) {
        this.cia = cia;
    }

    public Double getRi() {
        return ri;
    }

    public void setRi(Double ri) {
        this.ri = ri;
    }

    public Double getTaxesgs() {
        return taxesgs;
    }

    public void setTaxesgs(Double taxesgs) {
        this.taxesgs = taxesgs;
    }

    public Double getTaxepecea() {
        return taxepecea;
    }

    public void setTaxepecea(Double taxepecea) {
        this.taxepecea = taxepecea;
    }

    public Article getArtId() {
        return artId;
    }

    public void setArtId(Article artId) {
        this.artId = artId;
    }

    public Redevanceservice getRedId() {
        return redId;
    }

    public void setRedId(Redevanceservice redId) {
        this.redId = redId;
    }

    public Utilisateur getUtiId() {
        return utiId;
    }

    public void setUtiId(Utilisateur utiId) {
        this.utiId = utiId;
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
        if (!(object instanceof Cotation)) {
            return false;
        }
        Cotation other = (Cotation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cotation[ id=" + id + " ]";
    }
    
}

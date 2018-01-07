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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "article", catalog = "kreadouane2_db", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findById", query = "SELECT a FROM Article a WHERE a.id = :id"),
    @NamedQuery(name = "Article.findByTaxe", query = "SELECT a FROM Article a WHERE a.taxe = :taxe"),
    @NamedQuery(name = "Article.findByCode", query = "SELECT a FROM Article a WHERE a.code = :code"),
    @NamedQuery(name = "Article.findByDescription", query = "SELECT a FROM Article a WHERE a.description = :description"),
    @NamedQuery(name = "Article.findByUnite", query = "SELECT a FROM Article a WHERE a.unite = :unite"),
    @NamedQuery(name = "Article.findByApe", query = "SELECT a FROM Article a WHERE a.ape = :ape"),
    @NamedQuery(name = "Article.findByPecae", query = "SELECT a FROM Article a WHERE a.pecae = :pecae"),
    @NamedQuery(name = "Article.findByInitial", query = "SELECT a FROM Article a WHERE a.initial = :initial"),
    @NamedQuery(name = "Article.findByStatus", query = "SELECT a FROM Article a WHERE a.status = :status")})
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "taxe")
    private Integer taxe;
    @Size(max = 254)
    @Column(name = "code", length = 254)
    private String code;
    @Size(max = 254)
    @Column(name = "description", length = 254)
    private String description;
    @Size(max = 254)
    @Column(name = "unite", length = 254)
    private String unite;
    @Column(name = "ape")
    private Integer ape;
    @Column(name = "pecae")
    private Integer pecae;
    @Column(name = "initial")
    private Integer initial;
    @Size(max = 254)
    @Column(name = "status", length = 254)
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artId")
    private Collection<Cotation> cotationCollection;

    public Article() {
    }

    public Article(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaxe() {
        return taxe;
    }

    public void setTaxe(Integer taxe) {
        this.taxe = taxe;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Integer getApe() {
        return ape;
    }

    public void setApe(Integer ape) {
        this.ape = ape;
    }

    public Integer getPecae() {
        return pecae;
    }

    public void setPecae(Integer pecae) {
        this.pecae = pecae;
    }

    public Integer getInitial() {
        return initial;
    }

    public void setInitial(Integer initial) {
        this.initial = initial;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return description;
    }
    
}

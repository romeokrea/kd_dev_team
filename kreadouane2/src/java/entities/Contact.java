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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "contact", catalog = "kreadouane2_db", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findByTransId", query = "SELECT c FROM Contact c JOIN c.traId ca WHERE ca.id = :idTrans AND c.status = :status"),
    @NamedQuery(name = "Contact.findByAccId", query = "SELECT c FROM Contact c JOIN c.accId ca WHERE ca.id = :idAcc AND c.status = :status"),
    @NamedQuery(name = "Contact.findByCadId", query = "SELECT c FROM Contact c JOIN c.cadId ca WHERE ca.id = :idCad AND c.status = :status"),
    @NamedQuery(name = "Contact.findByBanqueId", query = "SELECT c FROM Contact c JOIN c.banId ca WHERE ca.id = :idBanq AND c.status = :status"),
    @NamedQuery(name = "Contact.findByAssureurId", query = "SELECT c FROM Contact c JOIN c.assId ca WHERE ca.id = :idAss AND c.status = :status"),
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
    @NamedQuery(name = "Contact.findById", query = "SELECT c FROM Contact c WHERE c.id = :id"),
    @NamedQuery(name = "Contact.findByName", query = "SELECT c FROM Contact c WHERE c.name = :name"),
    @NamedQuery(name = "Contact.findBySurname", query = "SELECT c FROM Contact c WHERE c.surname = :surname"),
    @NamedQuery(name = "Contact.findByPhone", query = "SELECT c FROM Contact c WHERE c.phone = :phone"),
    @NamedQuery(name = "Contact.findByEmail", query = "SELECT c FROM Contact c WHERE c.email = :email"),
    @NamedQuery(name = "Contact.findByFonction", query = "SELECT c FROM Contact c WHERE c.fonction = :fonction"),
    @NamedQuery(name = "Contact.findByStatus", query = "SELECT c FROM Contact c WHERE c.status = :status")})
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 254)
    @Column(name = "name", length = 254)
    private String name;
    @Size(max = 254)
    @Column(name = "surname", length = 254)
    private String surname;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 254)
    @Column(name = "phone", length = 254)
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 254)
    @Column(name = "email", length = 254)
    private String email;
    @Size(max = 254)
    @Column(name = "fonction", length = 254)
    private String fonction;
    @Size(max = 254)
    @Column(name = "status", length = 254)
    private String status;
    @JoinColumn(name = "acc_id", referencedColumnName = "id")
    @ManyToOne
    private Acconier accId;
    @JoinColumn(name = "ass_id", referencedColumnName = "id")
    @ManyToOne
    private Assureur assId;
    @JoinColumn(name = "ban_id", referencedColumnName = "id")
    @ManyToOne
    private Banque banId;
    @JoinColumn(name = "cad_id", referencedColumnName = "id")
    @ManyToOne
    private Cad cadId;
    @JoinColumn(name = "tra_id", referencedColumnName = "id")
    @ManyToOne
    private Transporteur traId;

    public Contact() {
    }

    public Contact(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Acconier getAccId() {
        return accId;
    }

    public void setAccId(Acconier accId) {
        this.accId = accId;
    }

    public Assureur getAssId() {
        return assId;
    }

    public void setAssId(Assureur assId) {
        this.assId = assId;
    }

    public Banque getBanId() {
        return banId;
    }

    public void setBanId(Banque banId) {
        this.banId = banId;
    }

    public Cad getCadId() {
        return cadId;
    }

    public void setCadId(Cad cadId) {
        this.cadId = cadId;
    }

    public Transporteur getTraId() {
        return traId;
    }

    public void setTraId(Transporteur traId) {
        this.traId = traId;
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
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Contact[ id=" + id + " ]";
    }
    
}

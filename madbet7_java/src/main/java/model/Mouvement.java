/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author KEVIN ALLAIN
 */
@Entity
@Table(catalog = "", schema = "RAMANANTSOAMI2021")
@NamedQueries({
    @NamedQuery(name = "Mouvement.findAll", query = "SELECT m FROM Mouvement m"),
    @NamedQuery(name = "Mouvement.findByIdmouvement", query = "SELECT m FROM Mouvement m WHERE m.idmouvement = :idmouvement"),
    @NamedQuery(name = "Mouvement.findByType", query = "SELECT m FROM Mouvement m WHERE m.type = :type"),
    @NamedQuery(name = "Mouvement.findByValeurmouvement", query = "SELECT m FROM Mouvement m WHERE m.valeurmouvement = :valeurmouvement"),
    @NamedQuery(name = "Mouvement.findByDatemouvement", query = "SELECT m FROM Mouvement m WHERE m.datemouvement = :datemouvement")})
public class Mouvement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name= "IDMOUVEMENT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mouvement_generator")
    @SequenceGenerator(name="mouvement_generator", sequenceName = "S_MOUVEMENT", allocationSize = 1)
    @Expose
    private Integer idmouvement;
    @Column(name = "TYPE")
    private String type;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private BigDecimal valeurmouvement;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datemouvement;
    @JoinColumn(name = "IDCOMPTE", referencedColumnName = "IDCOMPTE")
    @ManyToOne(optional = false)
    private Compte idcompte;

    public Mouvement() {
    }

    public Mouvement(Integer idmouvement) {
        this.idmouvement = idmouvement;
    }

    public Integer getIdmouvement() {
        return idmouvement;
    }

    public void setIdmouvement(Integer idmouvement) {
        this.idmouvement = idmouvement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getValeurmouvement() {
        return valeurmouvement;
    }

    public void setValeurmouvement(BigDecimal valeurmouvement) {
        this.valeurmouvement = valeurmouvement;
    }

    public Date getDatemouvement() {
        return datemouvement;
    }

    public void setDatemouvement(Date datemouvement) {
        this.datemouvement = datemouvement;
    }

    public Compte getIdcompte() {
        return idcompte;
    }

    public void setIdcompte(Compte idcompte) {
        this.idcompte = idcompte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmouvement != null ? idmouvement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mouvement)) {
            return false;
        }
        Mouvement other = (Mouvement) object;
        if ((this.idmouvement == null && other.idmouvement != null) || (this.idmouvement != null && !this.idmouvement.equals(other.idmouvement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Mouvement[ idmouvement=" + idmouvement + " ]";
    }
    
}

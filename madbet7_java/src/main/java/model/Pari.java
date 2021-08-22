/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Pari.findAll", query = "SELECT p FROM Pari p"),
    @NamedQuery(name = "Pari.findByIdpari", query = "SELECT p FROM Pari p WHERE p.idpari = :idpari"),
    @NamedQuery(name = "Pari.findByEtat", query = "SELECT p FROM Pari p WHERE p.etat = :etat"),
    @NamedQuery(name = "Pari.findByIdmatch", query = "SELECT p FROM Pari p WHERE p.idmatch = :idmatch"),
    @NamedQuery(name = "Pari.findByChoix", query = "SELECT p FROM Pari p WHERE p.choix = :choix"),
    @NamedQuery(name = "Pari.findByCote", query = "SELECT p FROM Pari p WHERE p.cote = :cote"),
    @NamedQuery(name = "Pari.findByDatepari", query = "SELECT p FROM Pari p WHERE p.datepari = :datepari"),
    @NamedQuery(name = "Pari.findByValeurpari", query = "SELECT p FROM Pari p WHERE p.valeurpari = :valeurpari"),
    @NamedQuery(name = "Pari.findByArgentobtenu", query = "SELECT p FROM Pari p WHERE p.argentobtenu = :argentobtenu")})
public class Pari implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer idpari;
    private BigInteger etat;
    private String idmatch;
    private String choix;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private BigDecimal cote;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datepari;
    private BigDecimal valeurpari;
    private BigDecimal argentobtenu;
    @JoinColumn(name = "IDCOMPTE", referencedColumnName = "IDCOMPTE")
    @ManyToOne
    private Compte idcompte;

    public Pari() {
    }

    public Pari(Integer idpari) {
        this.idpari = idpari;
    }

    public Integer getIdpari() {
        return idpari;
    }

    public void setIdpari(Integer idpari) {
        this.idpari = idpari;
    }

    public BigInteger getEtat() {
        return etat;
    }

    public void setEtat(BigInteger etat) {
        this.etat = etat;
    }

    public String getIdmatch() {
        return idmatch;
    }

    public void setIdmatch(String idmatch) {
        this.idmatch = idmatch;
    }

    public String getChoix() {
        return choix;
    }

    public void setChoix(String choix) {
        this.choix = choix;
    }

    public BigDecimal getCote() {
        return cote;
    }

    public void setCote(BigDecimal cote) {
        this.cote = cote;
    }

    public Date getDatepari() {
        return datepari;
    }

    public void setDatepari(Date datepari) {
        this.datepari = datepari;
    }

    public BigDecimal getValeurpari() {
        return valeurpari;
    }

    public void setValeurpari(BigDecimal valeurpari) {
        this.valeurpari = valeurpari;
    }

    public BigDecimal getArgentobtenu() {
        return argentobtenu;
    }

    public void setArgentobtenu(BigDecimal argentobtenu) {
        this.argentobtenu = argentobtenu;
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
        hash += (idpari != null ? idpari.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pari)) {
            return false;
        }
        Pari other = (Pari) object;
        if ((this.idpari == null && other.idpari != null) || (this.idpari != null && !this.idpari.equals(other.idpari))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pari[ idpari=" + idpari + " ]";
    }
    
}

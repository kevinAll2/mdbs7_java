/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIduser", query = "SELECT u FROM User u WHERE u.iduser = :iduser"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByMdp", query = "SELECT u FROM User u WHERE u.mdp = :mdp"),
    @NamedQuery(name = "User.findByDatedenaissance", query = "SELECT u FROM User u WHERE u.datedenaissance = :datedenaissance"),
    @NamedQuery(name = "User.findByNomcomplet", query = "SELECT u FROM User u WHERE u.nomcomplet = :nomcomplet")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    private Integer iduser;
    private String username;
    private String email;
    private String mdp;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedenaissance;
    private String nomcomplet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iduser")
    private List<Compte> compteList;
    @JoinColumn(name = "IDCOMPTE", referencedColumnName = "IDCOMPTE")
    @ManyToOne
    private Compte idcompte;
    @JoinColumn(name = "IDROLE", referencedColumnName = "IDROLE")
    @ManyToOne(optional = false)
    private Role idrole;

    public User() {
    }

    public User(Integer iduser) {
        this.iduser = iduser;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Date getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(Date datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public String getNomcomplet() {
        return nomcomplet;
    }

    public void setNomcomplet(String nomcomplet) {
        this.nomcomplet = nomcomplet;
    }

    public List<Compte> getCompteList() {
        return compteList;
    }

    public void setCompteList(List<Compte> compteList) {
        this.compteList = compteList;
    }

    public Compte getIdcompte() {
        return idcompte;
    }

    public void setIdcompte(Compte idcompte) {
        this.idcompte = idcompte;
    }

    public Role getIdrole() {
        return idrole;
    }

    public void setIdrole(Role idrole) {
        this.idrole = idrole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduser != null ? iduser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.iduser == null && other.iduser != null) || (this.iduser != null && !this.iduser.equals(other.iduser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.User[ iduser=" + iduser + " ]";
    }
    
    public int ageMajeur(Date birthDate, Date currentDate) {            
            // validate inputs ...                                                                               
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
        int d1 = Integer.parseInt(formatter.format(birthDate));                            
        int d2 = Integer.parseInt(formatter.format(currentDate));                          
        int age = (d2 - d1) / 10000;                                                       
        return age;                                                                        
    }   

    public User(Integer iduser, String username, String email, String mdp, Date datedenaissance, String nomcomplet, Compte compte, Role idrole) throws Exception {
        int age = this.ageMajeur(datedenaissance, datedenaissance);
        if(age>=18){
            compte.setIdcompte(iduser);
            compte.setIduser(this);
            compte.setBalance(20000);
            this.iduser = iduser;
            this.username = username;
            this.email = email;
            this.mdp = mdp;
            this.datedenaissance = datedenaissance;
            this.nomcomplet = nomcomplet;
            this.compteList = compteList;
            this.idcompte = idcompte;
            this.idrole = idrole;
        }
        else{
            throw new Exception("Age minimum: 18");
        }
    }
    
    
    
}

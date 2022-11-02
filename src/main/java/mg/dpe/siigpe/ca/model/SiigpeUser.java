/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.dpe.siigpe.ca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author BasilisseN
 */
@Entity
@Table(name = "siigpe_user", catalog = "siigpe", schema = "gpe")
@NamedQueries({
    @NamedQuery(name = "SiigpeUser.findAll", query = "SELECT s FROM SiigpeUser s")})
public class SiigpeUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private Short userId;
    @Basic(optional = false)
    @Column(name = "user_name", nullable = false, length = 2147483647)
    private String userName;
    @Basic(optional = false)
    @Column(name = "user_pass", nullable = false, length = 2147483647)
    @JsonIgnore
    private String userPass;
    @ManyToMany(mappedBy = "siigpeUserList")
    private List<SiigpeRole> siigpeRoleList;

    public SiigpeUser() {
    }

    public SiigpeUser(Short userId) {
        this.userId = userId;
    }

    public SiigpeUser(Short userId, String userName, String userPass) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
    }

    public Short getUserId() {
        return userId;
    }

    public void setUserId(Short userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public List<SiigpeRole> getSiigpeRoleList() {
        return siigpeRoleList;
    }

    public void setSiigpeRoleList(List<SiigpeRole> siigpeRoleList) {
        this.siigpeRoleList = siigpeRoleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SiigpeUser)) {
            return false;
        }
        SiigpeUser other = (SiigpeUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.dpe.siigpe.ca.model.SiigpeUser[ userId=" + userId + " ]";
    }
    
}

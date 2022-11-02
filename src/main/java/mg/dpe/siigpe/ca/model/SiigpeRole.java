/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.dpe.siigpe.ca.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author BasilisseN
 */
@Entity
@Table(name = "siigpe_role", catalog = "siigpe", schema = "gpe")
@NamedQueries({
    @NamedQuery(name = "SiigpeRole.findAll", query = "SELECT s FROM SiigpeRole s")})
public class SiigpeRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_id", nullable = false)
    private Short roleId;
    @Basic(optional = false)
    @Column(name = "role_lib", nullable = false, length = 2147483647)
    private String roleLib;
    @JoinTable(name = "siigpe_user_role", joinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)})
    @ManyToMany
    private List<SiigpeUser> siigpeUserList;

    public SiigpeRole() {
    }

    public SiigpeRole(Short roleId) {
        this.roleId = roleId;
    }

    public SiigpeRole(Short roleId, String roleLib) {
        this.roleId = roleId;
        this.roleLib = roleLib;
    }

    public Short getRoleId() {
        return roleId;
    }

    public void setRoleId(Short roleId) {
        this.roleId = roleId;
    }

    public String getRoleLib() {
        return roleLib;
    }

    public void setRoleLib(String roleLib) {
        this.roleLib = roleLib;
    }

    public List<SiigpeUser> getSiigpeUserList() {
        return siigpeUserList;
    }

    public void setSiigpeUserList(List<SiigpeUser> siigpeUserList) {
        this.siigpeUserList = siigpeUserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SiigpeRole)) {
            return false;
        }
        SiigpeRole other = (SiigpeRole) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.dpe.siigpe.ca.model.SiigpeRole[ roleId=" + roleId + " ]";
    }
    
}

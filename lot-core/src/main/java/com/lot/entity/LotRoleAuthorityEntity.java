package com.lot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lot_role_authority", schema = "hgz2", catalog = "")
public class LotRoleAuthorityEntity {
    private String id;
    private String roleId;
    private String authorityId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "authority_id")
    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotRoleAuthorityEntity that = (LotRoleAuthorityEntity) o;
        return id == that.id &&
                roleId == that.roleId &&
                authorityId == that.authorityId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, authorityId);
    }
}

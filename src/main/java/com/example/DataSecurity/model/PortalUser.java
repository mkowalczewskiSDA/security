package com.example.DataSecurity.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="portal_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "roles")
@EqualsAndHashCode(exclude = "roles")
public
class PortalUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="PU_ID")
    private int portalUserID;
    @Column(name="PU_LOGIN", unique = true)
    private String portalUserLogin;
    @Column(name="PU_FIRSTNAME")
    private String portalUserFirstName;
    @Column(name="PU_LASTNAME")
    private String portalUserLastName;
    @Column(name="PU_EMAIL", unique = true)
    private String portalUserEmail;
    @Column(name="PU_PASSWORD")
    private String portalUserPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "portal_user_roles", joinColumns = @JoinColumn(name = "PUR_PU_ID"), inverseJoinColumns = @JoinColumn(name = "PUR_RO_ID"))
    private Set<Role> roles;

}

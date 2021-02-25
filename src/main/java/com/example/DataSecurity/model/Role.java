package com.example.DataSecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="RO_ID")
    private int roleId;
    @Column(name="RO_NAME")
    private String roleName;
}

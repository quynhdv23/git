package com.example.computershop.entities;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Entity(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "org_code")
    private String orgCode;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "t24_code")
    private String t24Code;

    @Column(name = "org_group")
    private String orgGroup;

    @Column(name = "org_level")
    private Long org_level;

    @Column(name = "status")
    private Long status;

    @Column(name = "address")
    private String address;

}

package com.example.DataSecurity.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "`order`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "portalUser")
@EqualsAndHashCode(exclude = "portalUser")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORD_ID")
    private int orderId;
    @Column(name = "ORD_PRICE")
    private double orderPrice;
    @Column(name = "ORD_TITLE")
    private String orderTitle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORD_PU_ID", referencedColumnName = "PU_ID")
    private PortalUser portalUser;

}



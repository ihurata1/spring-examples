package com.hurata.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity //mandatory
@Table(name = "User_Address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class Address implements Serializable {

    @Id //mandatory
    @SequenceGenerator(name = "seq_user_address", allocationSize = 1)
    @GeneratedValue(generator = "seq_user_address", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 500, name="address")
    private String address;

    @Enumerated
    private AddressType addressType;

    @Column(name="isActive") // no lenght needed
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER) //default ilgili kişi çağırılır
    @JoinColumn(name = "user_address_id")
    private User user;

    public enum AddressType{
        HOME_ADDRESS,
        WORK_ADDRESS,
        OTHER
    }
}

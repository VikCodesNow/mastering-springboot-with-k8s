package com.master.springboot.series.accounts.entites;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(name = "name")
    private String customerName;
    @Column(name = "email")
    private String customerEmail;
    private String mobileNumber;
}

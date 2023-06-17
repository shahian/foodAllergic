package com.shahian.foodallergic.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullname")
    private String fullName;
    // Other user fields

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Allergy> allergies;

}

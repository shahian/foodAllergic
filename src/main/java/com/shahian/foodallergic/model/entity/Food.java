package com.shahian.foodallergic.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "foods")
@Data
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "foodName")
    private String foodName;

    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL)
    private List<Ingredient>ingredients;
    public Food(){

    }
    public Food(String foodName, List<Ingredient> ingredients) {
        this.foodName=foodName;
        this.ingredients=ingredients;
    }


}

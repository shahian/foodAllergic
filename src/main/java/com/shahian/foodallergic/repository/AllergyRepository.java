package com.shahian.foodallergic.repository;

import com.shahian.foodallergic.model.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy,Long> {

    List<Allergy> findAllByUser_Id(Long userId);
}

package com.rarl.sj.repository;

import com.rarl.sj.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FoodRepository extends JpaRepository<FoodEntity,Integer> {
}

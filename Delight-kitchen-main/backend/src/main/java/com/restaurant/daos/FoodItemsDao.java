package com.restaurant.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.entities.FoodItems;
@Repository
public interface FoodItemsDao extends JpaRepository<FoodItems, Integer> {
	FoodItems findByProductId(int id);

	 List<FoodItems> findByCategory_CategoryIdAndProductStatus(int productCategoryId,String status);
	 List<FoodItems> findByProductStatus(String status);
	
}

package com.restaurant.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.entities.TablesBooking;

@Repository
public interface TablesDao extends JpaRepository<TablesBooking, Integer> {

	List<TablesBooking> findByWaiter_UserId(int waiterId);
	List<TablesBooking> findByTableStatus(String status);
	TablesBooking findByTableId(int tableId);

}

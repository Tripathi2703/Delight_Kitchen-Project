package com.restaurant.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.daos.FoodItemsDao;
import com.restaurant.dtos.FoodItemsDTO;
import com.restaurant.dtos.FoodItemsDTOSend;
import com.restaurant.entities.FoodItems;
import com.restaurant.utils.DtoEntityConverter;

@Service
@Transactional
public class ProductServiceImpl {

	@Autowired
	private FoodItemsDao productDao;

	@Autowired
	private DtoEntityConverter converter;

	public Map<String, Object> addProduct(FoodItemsDTO productDTO) {
		FoodItems product=converter.toProductEntity(productDTO);
		
		product = productDao.save(product);
		return Collections.singletonMap("insertedId", product.getProductId());
	}
	
	
	
	public Map<String, Object> updateProduct(FoodItemsDTO productDTO,int id) {
		FoodItems product=productDao.findByProductId(id);		
		product.setProductName(productDTO.getProductName());
		product.setProductPrice(productDTO.getProductPrice());
//		product.setCategory(new Category(productDTO.getProductcategoryId()));
		product = productDao.save(product);
		return Collections.singletonMap("Updated", product.getProductId());
	}
	
	public Map<String, Object> updateProductStatus(FoodItemsDTO productDTO,int id) {
		FoodItems product=productDao.findByProductId(id);
		product.setProductStatus(productDTO.getProductStatus());
		product = productDao.save(product);
		return Collections.singletonMap("Updated", product.getProductId());
	}
	
	public List<FoodItemsDTOSend> getAllProduct() {
		
	List<FoodItemsDTOSend> sendList= new ArrayList<FoodItemsDTOSend>();	
	List<FoodItems> list = productDao.findAll();
	for (FoodItems product : list) {
		sendList.add(converter.toProductDTO(product));
	}
	
	
	return sendList;
	}
	
	public List<FoodItemsDTOSend> getAllProductByStatus() {
		
		List<FoodItemsDTOSend> sendList= new ArrayList<FoodItemsDTOSend>();	
		List<FoodItems> list = productDao.findByProductStatus("Enabled");
		for (FoodItems product : list) {
			sendList.add(converter.toProductDTO(product));
		}
		
		
		return sendList;
		}
	
	public List<FoodItemsDTOSend> getProductByCat(int id) {
		
	List<FoodItemsDTOSend> sendList= new ArrayList<FoodItemsDTOSend>();	
	List<FoodItems> list = productDao.findByCategory_CategoryIdAndProductStatus(id,"Enabled");
	for (FoodItems product : list) {
		sendList.add(converter.toProductDTO(product));
	}
	
	return sendList;
	}
	public FoodItemsDTOSend getByProductId(int id) {

		FoodItemsDTOSend dtoSend = converter.toProductDTO(productDao.findByProductId(id));
		
	return dtoSend;
	}
	
	public int deleteByProductId(int id) {

		if(productDao.existsById(id))
		{
			FoodItems product = productDao.findByProductId(id);
			productDao.deleteById(id);
			return 1;
		}
		
	return 0;
	}
}

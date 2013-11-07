package me.h2.cafe;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeService {

	@Autowired
	CoffeeRepository repo;
	
	public List<Coffee> getMenu() {
		return repo.findAll();
	}
	
	public List<Coffee> getCheapMenu(int price) {
		return repo.findByPriceLessThan(price);
	}
}

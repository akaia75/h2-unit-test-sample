package me.h2.cafe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, String> {

	public List<Coffee> findByPriceLessThan(int price);
}

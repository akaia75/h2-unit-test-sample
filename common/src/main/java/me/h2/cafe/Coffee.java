package me.h2.cafe;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coffee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Coffee() {
		
	}
	
	public Coffee(String name, int price) {
		this(name, price, Boolean.TRUE);
	}
	
	public Coffee(String name, int price, boolean available) {
		this.name = name;
		this.price = price;
		this.available = available;
	}
	
	@Override
	public String toString() {
		return String.format("id=%d, name=%s, price=%d, available=%b", id, name, price, available);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	int price;
	boolean available;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
}

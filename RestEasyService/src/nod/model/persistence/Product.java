package nod.model.persistence;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement(name = "Product")
@Entity("Product")
public class Product {
	
	@JsonIgnore
	@Id
	protected ObjectId id;
	
	protected int si;
	protected String name;
	protected int price;
	
	public int getSi() {
		return si;
	}

	public void setSi(int si) {
		this.si = si;
	}
	
	public Product() {
		super();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
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
	
}

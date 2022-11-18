package com.sample.mysql;

import java.util.List;

import com.sample.entities.Company;
import com.sample.entities.Contact;
import com.sample.entities.Owner;
import com.sample.entities.Phone;
import com.sample.entities.User;

public class MySQLConnector {
	
	public List<Owner> mockGetOwners() {
		Contact contact1 = new Contact("12345678");
		Contact contact2 = new Contact("11111111");
		
		Contact contact3 = new Contact("87654321");
		Contact contact4 = new Contact("22222222");
		
		Phone phone1 = new Phone("Motorola", List.of(contact1, contact2));
		Phone phone2 = new Phone("Panasonic", List.of(contact3, contact4));
		
		User user = new User("John Doe", List.of(phone1, phone2));
		
		Company company = new Company("FreeCorp Inc.", List.of(user));
		
		Owner owner = new Owner("Mark Richards", List.of(company));
		
		return List.of(owner);
	}

}

package com.sample.entities;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

public class Contact extends LDAPEntity {
	
	private String phoneNumber;

	public Contact(String phoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return "phoneNumber="+phoneNumber+",ou=contacts,ou=phones,ou=users,ou=companies,ou=owners";
	}
	
	@Override
	public void createLDAPObject(DirContext context) throws NamingException {
		Map<String, String> attributes = new HashMap<>();
		attributes.put("phoneNumber", getPhoneNumber());
		super.createLDAPObject(context, getName(), attributes);
	}

}

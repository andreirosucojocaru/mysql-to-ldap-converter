package com.sample.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapContext;

public class User extends LDAPEntity {
	
	private String fullName;
	private List<Phone> phones;

	public User(String fullName, List<Phone> phones) {
		super();
		this.fullName = fullName;
		this.phones = phones;
	}

	public String getFullName() {
		return fullName;
	}

	public void setName(String fullName) {
		this.fullName = fullName;
	}
	
	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public String getName() {
		return "fullName="+fullName+",ou=users,ou=companies,ou=owners";
	}

	@Override
	public void createLDAPObject(DirContext context) throws NamingException {
		Map<String, String> attributes = new HashMap<>();
		attributes.put("fullName", getFullName());
		super.createLDAPObject(context, getName(), attributes);
	}
	
	@Override
	public void writeEntityToLdap(LdapContext context) {
		super.writeEntityToLdap(context);
		getPhones().stream().forEach(phone -> phone.writeEntityToLdap(context));
	}

}

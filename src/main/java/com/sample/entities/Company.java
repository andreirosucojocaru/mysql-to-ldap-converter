package com.sample.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapContext;

public class Company extends LDAPEntity {
	
	private String fullName;
	private List<User> users;

	public Company(String fullName, List<User> users) {
		super();
		this.fullName = fullName;
		this.users = users;
	}

	public String getFullName() {
		return fullName;
	}

	public void setName(String fullName) {
		this.fullName = fullName;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getName() {
		return "fullName="+fullName+",ou=companies,ou=owners";
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
		getUsers().stream().forEach(user -> user.writeEntityToLdap(context));
	}

}

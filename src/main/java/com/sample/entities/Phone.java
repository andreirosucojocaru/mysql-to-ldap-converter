package com.sample.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapContext;

public class Phone extends LDAPEntity {
	
	private String description;
	private List<Contact> contacts;

	public Phone(String description, List<Contact> contacts) {
		super();
		this.description = description;
		this.contacts = contacts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getName() {
		return "description="+description+",ou=phones,ou=users,ou=companies,ou=owners";
	}
	
	@Override
	public void createLDAPObject(DirContext context) throws NamingException {
		Map<String, String> attributes = new HashMap<>();
		attributes.put("description", getDescription());
		super.createLDAPObject(context, getName(), attributes);
	}
	
	@Override
	public void writeEntityToLdap(LdapContext context) {
		super.writeEntityToLdap(context);
		getContacts().stream().forEach(contact -> contact.writeEntityToLdap(context));
	}

}
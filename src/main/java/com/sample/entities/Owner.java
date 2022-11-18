package com.sample.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapContext;

public class Owner extends LDAPEntity {
	
	private String fullName;
	private List<Company> companies;

	public Owner(String fullName, List<Company> companies) {
		super();
		this.fullName = fullName;
		this.companies = companies;
	}

	public String getFullName() {
		return fullName;
	}

	public void setName(String fullName) {
		this.fullName = fullName;
	}
	
	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public String getName() {
		return "fullName="+fullName+",ou=owners";
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
		getCompanies().stream().forEach(company -> company.writeEntityToLdap(context));
	}

}

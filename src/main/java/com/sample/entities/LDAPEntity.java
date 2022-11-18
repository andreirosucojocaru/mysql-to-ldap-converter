package com.sample.entities;

import java.util.Map;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapContext;

public abstract class LDAPEntity {

	public abstract String getName();
	
	public abstract void createLDAPObject(DirContext context) throws NamingException;

	public void createLDAPObject(DirContext context, String name, Map<String, String> attributes)
			throws NamingException {
		Attributes objectAttributes = new BasicAttributes();

		attributes.entrySet().stream().forEach(entry -> {
			Attribute attribute = new BasicAttribute(entry.getKey());
			attribute.add(entry.getValue());
			objectAttributes.put(attribute);
		});
		context.createSubcontext(name, objectAttributes);
	}
	
	public void writeEntityToLdap(LdapContext context) {
		try {
			createLDAPObject(context);
		} catch (NamingException ne) {
			System.out.println("An exception has occurred while writing entity " + this + " to LDAP: " + ne);
		}
	}

}

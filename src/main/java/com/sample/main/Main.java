package com.sample.main;

import java.util.List;

import javax.naming.ldap.LdapContext;

import com.sample.entities.Owner;
import com.sample.ldap.LdapConnector;
import com.sample.mysql.MySQLConnector;

public class Main {

	public static void main(String[] args) {
		// retrieve information from MySQL database
		MySQLConnector mySQLConnector = new MySQLConnector();
		List<Owner> owners = mySQLConnector.mockGetOwners();

		LdapConnector ldapConnector = new LdapConnector();
		LdapContext context = ldapConnector.connectToLdap();

		owners.stream().forEach(owner -> owner.writeEntityToLdap(context));
	}

}

package com.sample.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.aeonbits.owner.ConfigFactory;

import com.sample.config.ApplicationConfig;

public class LdapConnector {

	private String ldapURL;
	private String ldapBase;
	private String ldapUsername;
	private String ldapPassword;

	public LdapConnector() {
		init();
	}

	public void init() {
		ApplicationConfig config = ConfigFactory.create(ApplicationConfig.class);
		ldapURL = config.getLdapURL();
		ldapBase = config.getLdapBase();
		ldapUsername = config.getLdapUsername();
		ldapPassword = config.getLdapPassword();
	}

	public LdapContext connectToLdap() {
		System.out.println("ldapURL: " + ldapURL + " ldapBase: " + ldapBase + " ldapUsername: " + ldapUsername
				+ " ldapPassword: " + ldapPassword);
		try {
			Hashtable<String, String> env = new Hashtable<>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "Simple");
			env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
			env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
			env.put(Context.REFERRAL, "follow");
			env.put(Context.PROVIDER_URL, ldapURL);
			System.out.println("Attempting to connect to AD...");
			LdapContext ctx = new InitialLdapContext(env, null);
			System.out.println("LDAP connection was successful!");
			return ctx;
		} catch (NamingException ex) {
			System.out.println("LDAP connection failed: " + ex);
			return null;
		}
	}

}

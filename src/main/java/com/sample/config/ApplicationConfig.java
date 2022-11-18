package com.sample.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:./config/application.properties"})
public interface ApplicationConfig extends Config {
	
	@Key("ldap.url")
	public String getLdapURL();
	
	@Key("ldap.base")
	public String getLdapBase();
	
	@Key("ldap.username")
	public String getLdapUsername();
	
	@Key("ldap.password")
	public String getLdapPassword();

}
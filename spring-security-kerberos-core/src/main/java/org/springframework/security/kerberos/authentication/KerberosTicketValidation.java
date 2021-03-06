package org.springframework.security.kerberos.authentication;

import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;

import javax.security.auth.Subject;
import javax.security.auth.kerberos.KerberosPrincipal;
import java.util.HashSet;

/**
 * Result of ticket validation
 */
public class KerberosTicketValidation {

	private final String username;
	private final byte[] responseToken;
	private final GSSContext gssContext;
	private final String servicePrincipal;
	private final GSSCredential delegationCredential;

	public KerberosTicketValidation(String username, String servicePrincipal, byte[] responseToken, GSSContext gssContext) {
		this(username, servicePrincipal, responseToken, gssContext, null);
	}
	
	public KerberosTicketValidation(String username, String servicePrincipal, byte[] responseToken, GSSContext gssContext, GSSCredential delegationCredential) {
		this.username = username;
		this.servicePrincipal = servicePrincipal;
		this.responseToken = responseToken;
		this.gssContext = gssContext;
		this.delegationCredential = delegationCredential;
	}

	public String username() {
		return username;
	}

	public byte[] responseToken() {
		return responseToken;
	}

	public GSSContext getGssContext() {
		return gssContext;
	}

	public GSSCredential getDelegationCredential() {
		return delegationCredential;
	}

	public Subject subject() {
		final HashSet<KerberosPrincipal> princs = new HashSet<KerberosPrincipal>();
		princs.add(new KerberosPrincipal(servicePrincipal));
		return new Subject(false, princs, new HashSet<Object>(), new HashSet<Object>());
	}

}
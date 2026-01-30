package com.kroger.pharmacy.csr.service;

import java.rmi.Naming;

import com.kroger.pharmacy.csr.exception.LookupException;
import com.techrx.app.trexone.batch.controlledsubstancereporting.CSRRemoteService;

public class LookupService implements ILookupService {

	private String remoteServiceJndiName;
	
	public CSRRemoteService lookupCSRRemoteService() throws LookupException {
		CSRRemoteService service = null;
		
		//Getting the remote server name from server VM arguments as System property
		String remoteServerName = System.getProperty("remoteServerName", "");
		
		if (remoteServerName.trim().isEmpty()) {
			throw new LookupException("Remote server name not found. Please check server VM arguments");
		}
		
		remoteServiceJndiName = "//" + remoteServerName + "/CSRRemoteService";
		
		try {
			service = (CSRRemoteService)Naming.lookup(remoteServiceJndiName);
		} catch (Exception e) {
			throw new LookupException("CSRRemoteService lookup failed.", e);
		}
		
		return service;
	}

	public String getRemoteServiceJndiName() {
		return remoteServiceJndiName;
	}

	public void setRemoteServiceJndiName(String remoteServiceJndiName) {
		this.remoteServiceJndiName = remoteServiceJndiName;
	}

}

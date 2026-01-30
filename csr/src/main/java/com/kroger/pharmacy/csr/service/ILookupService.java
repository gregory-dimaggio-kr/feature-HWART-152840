package com.kroger.pharmacy.csr.service;

import com.kroger.pharmacy.csr.exception.LookupException;
import com.techrx.app.trexone.batch.controlledsubstancereporting.CSRRemoteService;

public interface ILookupService {
	public CSRRemoteService lookupCSRRemoteService() throws LookupException;
}

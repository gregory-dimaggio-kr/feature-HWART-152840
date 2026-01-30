package com.kroger.pharmacy.csr.service;

import java.io.File;

import org.junit.Test;

import com.kroger.pharmacy.csr.AbstractApplicationTestCase;
import com.kroger.pharmacy.csr.util.AtlanticAssociatesParser;
import com.kroger.pharmacy.csr.util.CorrectionFileReader;

public class RxExtractServiceTest extends AbstractApplicationTestCase {
	@Test
	public void testCsrService() {
		IRxExtractService rxExtractService = (IRxExtractService)applicationContext.getBean("rxExtractService");
//		String facilityId = rxExtractService.getFacilityIdFromOtherId("10152289");
//		Assert.assertEquals("03400373", facilityId);
//		
//		facilityId = rxExtractService.getFacilityIdFromOtherId("BR6323860");
//		Assert.assertEquals("70300120", facilityId);
		
		String facilityId = rxExtractService.getFacilityIdFromOtherId("B0115251");
		System.out.println(facilityId);
		
		AtlanticAssociatesParser parser = new AtlanticAssociatesParser();
		parser.setRxExtractService(rxExtractService);
		
		File file = new File("/tx.txt");
		CorrectionFileReader reader = new CorrectionFileReader();
		reader.readAAFile(file, rxExtractService);
	}
}

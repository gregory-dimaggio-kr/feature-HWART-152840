package com.kroger.pharmacy.csr.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.kroger.pharmacy.csr.domain.RxExtract;
import com.kroger.pharmacy.csr.service.IRxExtractService;

public class CorrectionFileReader {

	public void readAAFile(File file, IRxExtractService rxExtractService) {
		AtlanticAssociatesParser parser = new AtlanticAssociatesParser();
		parser.setRxExtractService(rxExtractService);
		
		List<Correction> corrections = new ArrayList<Correction>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while ( br.ready() ) {
				String line = br.readLine();
				Correction correction = parser.suggestCorrection(line);
				
				if ( correction != null && correction.getErrorMessage() == null )
					corrections.add(correction);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for ( Correction correction : corrections ) {
			if ( correction.getRxExtract() != null ) {
				System.out.println("RX # " + correction.getRxExtract().getRxNumber());
				System.out.println("RX Date:  " + correction.getRxExtract().getRxDispenseDate());
				System.out.println("Field: " + correction.getCorrectedField());
				System.out.println("New value: " + correction.getCorrectedValue());
				System.out.println("-----------------------------");
			}
		}
		
		boolean correct = true;//false;
		
//		try {
//			StreamTokenizer input = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
//			System.out.print("Make corrections? (y/n): ");
//			input.nextToken();
//			
//			if ( input.sval.equalsIgnoreCase("y") )
//				correct = true;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		if ( correct ) {
			for ( Correction correction : corrections ) {
				RxExtract ex = correction.getCorrectedRxExtract();
				
				if ( ex != null ) {
//					rxExtractService.updateRxExtract(correction.getCorrectedRxExtract());
//					
//					if ( !rxExtractService.hasUnreportedCorrections(ex.getExtractKey()) ) {
//						
//						RxExtractCorrection c = new RxExtractCorrection();
//						c.setExtractKey(ex.getExtractKey());
//						c.setCorrectionDate(new Date());
//						c.setUserEuid(new SecurityBean().getUsername());
//						
//						c = rxExtractService.persistCorrection(c);
//					}
					
					System.out.println("UPDATE TREXONE_DW_DATA.CSR_RXFILL_EXTRACT SET " + correction.getDatabaseField() + " = '" + correction.getCorrectedValue() + "' WHERE CRE_KEY = " + correction.getRxExtract().getExtractKey() + ";"); 
				}
				
			}
		}
	}

}

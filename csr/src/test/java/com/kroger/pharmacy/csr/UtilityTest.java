package com.kroger.pharmacy.csr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.junit.Test;

public class UtilityTest extends AbstractApplicationTestCase {
	@Test
	public void testUtil() {
		String filename = "/insert extract record query no where.sql";
		String whereFilename = "/insert where clause 02500452.sql";
		String outputFile = "/insert records 02500452.sql";
		File file = new File(filename);
		try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuffer output = new StringBuffer();
            while ( br.ready() ) {
                String line = br.readLine();
                line = line.trim();
                output.append(line);
                output.append(" \r\n");
            }
            
            String insertClause = output.toString();
            output = new StringBuffer();
            
            br.close();
            
            br = new BufferedReader(new FileReader(new File(whereFilename)));
            while ( br.ready() ) {
                String line = br.readLine();
                output.append(insertClause);
                output.append(line);
                output.append("\r\n");
            }
            
            br.close();
            
            File of = new File(outputFile);
            of.createNewFile();
            FileWriter fileWriter = new FileWriter(of);
            fileWriter.write(output.toString());
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}

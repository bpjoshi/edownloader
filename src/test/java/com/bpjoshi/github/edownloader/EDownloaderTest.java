package com.bpjoshi.github.edownloader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class EDownloaderTest {
	
	private static final String url="https://raw.githubusercontent.com/bpjoshi/java-8/master/src/com/bpjoshi/java8/introduction/EmployeeTest.java";
	private static final String fileDir="src/main/resources";
	/**
	 * This setup method deletes existing files from the directory
	 * @throws Exception
	 */
	@Before
	public void downloadFileTestBefore() throws FileException{
		
			if(!isDirectEmpty()){
				File file = new File(fileDir);
				String[] directoryFiles=file.list();
				for (int i=0; i<directoryFiles.length; i++) {
		               File myFile = new File(file, directoryFiles[i]); 
		               myFile.delete();
		           }
			}
		}

	@Test
	public void downloadFileTest() throws FileException {
		String locationToSaveWithFileName="src/main/resources/EmployeeTest.java";
		EDownloader.downloadFile(url, locationToSaveWithFileName);
		assertFalse("The directory is empty", isDirectEmpty());
		assertEquals("EmployeeTest.java is present", "EmployeeTest.java", getDownloadedFileName());
	}
	
	@Test
	public void downloadFileWithProxyEnhancedTest(){
		String locationToSaveWithFileName="src/main/resources/EmployeeTest.java";
		Properties systemSettings = System.getProperties();
        systemSettings.put("proxySet", "true");
        systemSettings.put("https.proxyHost", "proxy08-master.noid.in.sopra");
        systemSettings.put("https.proxyPort", "8080");
		EDownloader.downloadFileEnhanced(url, locationToSaveWithFileName, true);
		assertEquals("EmployeeTest.java is present", "EmployeeTest.java", getDownloadedFileName());
	}

	
	public boolean isDirectEmpty() throws FileException{
		File file = new File(fileDir);
		if(file.isDirectory()){
			if(file.list().length>0){
				return false;
			}
			return false;
		}
		else throw new FileException("This is not a valid directory");
	}
	
	public String getDownloadedFileName(){
		String[] directoryFiles=new File(fileDir).list();
		return directoryFiles[0];
	}

}

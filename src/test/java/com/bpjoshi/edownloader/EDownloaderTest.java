package com.bpjoshi.edownloader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.bpjoshi.edownloader.EDownloadProps;
import com.bpjoshi.edownloader.EDownloader;
/**
 *  Test Class for EDownloader
 * @author bpjoshi(Bhagwati Prasad)
 */
public class EDownloaderTest {
	
	private static final String url="https://raw.githubusercontent.com/bpjoshi/java-8/master/src/com/bpjoshi/java8/introduction/EmployeeTest.java";
	private static final String fileDir="src/main/resources";
	
	/**
	 * This method gets called before each JUnit Test to delete the existing files from the directory
	 * @throws FileException
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
	
	/**
	 * Test for method downloadFile. This method doesn't require any authentication 
	 * before download or any proxy settings. If you are behind a Proxy, comment this Test Case.
	 * @throws FileException
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	/*@Test
	public void downloadFileTest() throws FileException, MalformedURLException, IOException {
		String locationToSaveWithFileName="src/main/resources/EmployeeTest.java";
		EDownloadProps props=new EDownloadProps();
		props.setFullFileName(locationToSaveWithFileName);
		props.setSourceUrl(url);
		EDownloader.downloadFile(props);
		assertFalse("The directory is empty", isDirectEmpty());
		assertEquals("EmployeeTest.java is present", "EmployeeTest.java", getDownloadedFileName());
	}*/
	/**
	 * This method shows how to set up proxies for downloading. You still don't have 
	 * any authentication here however. This method shows proxy settings for HTTPS.
	 * You could do the same with HTTP by changing properties  like https.proxyHost to http.proxyHost etc.
	 * @throws FileException 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void downloadFileWithProxyTest() throws FileException, MalformedURLException, IOException{
		String locationToSaveWithFileName="src/main/resources/EmployeeTest.java";
		EDownloadProps props=new EDownloadProps();
		props.setFullFileName(locationToSaveWithFileName);
		Properties systemSettings = System.getProperties();
		props.setHttps_Proxy_Host("proxy08-master.noid.in.sopra");
		props.setHttps_Proxy_Port("8080");
        
		EDownloader.downloadFile(props);
		assertFalse("The directory is empty", isDirectEmpty());
		assertEquals("EmployeeTest.java is present", "EmployeeTest.java", getDownloadedFileName());
	}
	
	
	/**
	 * @return boolean value to check if the test directory is empty
	 * @throws FileException
	 */
	public static boolean isDirectEmpty() throws FileException{
		File file = new File(fileDir);
		if(file.isDirectory()){
			if(file.list().length>0){
				return false;
			}
			return false;
		}
		else throw new FileException("This is not a valid directory");
	}
	
	/**
	 * @return String name of the downloaded file.
	 */
	public String getDownloadedFileName(){
		String[] directoryFiles=new File(fileDir).list();
		return directoryFiles[0];
	}
	
	
	/**
	 * It runs after all tests are run and deletes files from test directory
	 * @throws FileException
	 */
	@AfterClass
	public static void downloadFileTestAfter() throws FileException{
			if(!isDirectEmpty()){
				File file = new File(fileDir);
				String[] directoryFiles=file.list();
				for (int i=0; i<directoryFiles.length; i++) {
		               File myFile = new File(file, directoryFiles[i]); 
		               myFile.delete();
		           }
			}
		}
}

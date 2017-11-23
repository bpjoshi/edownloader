package com.bpjoshi.github.edownloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

/**
 * All the exceptions are declared in throws are supposed to be handled by you.
 * @author bpjoshi(Bhagwati Prasad)
 */
public class EDownloader {

	private static final int BUFFER_SIZE = 1024 * 4;
	
	/**
	 * 1. url: Enter full url of the file content to be downloaded. For example : 
	 * 				https://www.uop.edu.jo/download/research/members/oxford_guide_to_english_grammar.pdf
	 * 2. locationToSaveFile: Enter full name of the location where you want to save the file:
	 * 				(classpath of your maven project: ) <quote>src/main/resources/englishgrammar.pdf</quote>
	 * 				(location relative to your current program: ) <quote>../../bpjoshi/englishgrammar.pdf</quote>
	 * 				(absolute path: ) <quote>d:/myfiles/main/resources/englishgrammar.pdf</quote>
	 * 3. behindAProxy: If you are behind a proxy, provide proxy settings and set this flag to true
	 * 4. Use this method when there is to be used when there is no authentication. The URL can be either https or http
	 * 
	 * @param url
	 * @param locationToSaveFile
	 * @param behindAProxy
	 */
	public static void downloadFile(String url, String locationToSaveWithFileName, boolean behindAProxy)
	throws MalformedURLException, IOException {
		
		//Setting up proxies
		Properties systemSettings = System.getProperties();
        systemSettings.put("proxySet", "true");
        systemSettings.put("https.proxyHost", "proxy08-master.noid.in.sopra");
        systemSettings.put("https.proxyPort", "8080");
        if(behindAProxy){
        	System.setProperty("java.net.useSystemProxies", "true");
        }
        System.setProperty("java.net.useSystemProxies", "true");
        saveURLContentToFile(new URL(url), new File(locationToSaveWithFileName));
	}
	
	public static void saveURLContentToFile(final URL downloadSource, final File destinationFile) throws IOException {
		URLConnection connection = downloadSource.openConnection();
		final InputStream downloadSourceStream=connection.getInputStream();
        try {
        	copyContent(downloadSourceStream, destinationFile);
        } finally {
        	closeInputStream(downloadSourceStream);
        }
    }
	
	/**
	 * 
	 * @param sourceStream
	 * @param destination
	 * @throws IOException
	 */
	public static void copyContent(final InputStream sourceStream, final File destination) throws IOException {
		//Create a FileOutputStream for local file system where we want to save the file
		if (destination.exists()) {
            if (destination.isDirectory()) {
                throw new IOException(destination + " is a directory, not a valid file name");
            }
            if (destination.canWrite() == false) {
                throw new IOException("The Program doesn't have enough permission to write to "+destination);
            }
        } 
        final FileOutputStream outputStream = new FileOutputStream(destination);
        //Finally method call to download from url
        try {
        	readAndWrite(sourceStream, outputStream);
        	outputStream.close(); 
        } finally {
        	closeOutputStream(outputStream);
        }
    }
	 	/**
	 	 * It takes an active InputStream and an active Output Stream to read from and write to
	 	 * @param input 
	 	 * @param output
	 	 * @return
	 	 * @throws IOException
	 	 */
		public static long readAndWrite(InputStream input, OutputStream output) throws IOException {
			byte[] buffer = new byte[BUFFER_SIZE];
			long count = 0;
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
				count += n;
			}
			return count;
		}
		/**
		 * Closes InputStream
		 * @param input
		 */
		public static void closeInputStream(InputStream input) {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		/**
		 * Closes OutputStream
		 * @param output
		 */
		public static void closeOutputStream(OutputStream output) {
	        try {
	            if (output != null) {
	                output.close();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	 
	 

	
}

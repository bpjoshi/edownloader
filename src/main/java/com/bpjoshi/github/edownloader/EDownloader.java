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
	public static void downloadFile(String url, String locationToSaveWithFileName, boolean behindAProxy) {
		
		//Setting up proxies
		Properties systemSettings = System.getProperties();
        systemSettings.put("proxySet", "true");
        systemSettings.put("https.proxyHost", "proxy08-master.noid.in.sopra");
        systemSettings.put("https.proxyPort", "8080");
        if(behindAProxy){
        	System.setProperty("java.net.useSystemProxies", "true");
        }
        System.setProperty("java.net.useSystemProxies", "true");
        try {
        	saveURLToFile(new URL(url), new File(locationToSaveWithFileName));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}
	
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @throws IOException
	 */
	public static void saveURLToFile(final URL source, final File destination) throws IOException {
		URLConnection connection = source.openConnection();
        copyInputStreamToFile(connection.getInputStream(), destination);
    }
	/**
	 * 
	 * @param source
	 * @param destination
	 * @throws IOException
	 */
	public static void copyInputStreamToFile(final InputStream source, final File destination) throws IOException {
        try {
            copyToFile(source, destination);
        } finally {
        	//done
        	closeQuietly(source);
        }
    }
	/**
	 * 
	 * @param source
	 * @param destination
	 * @throws IOException
	 */
	public static void copyToFile(final InputStream source, final File destination) throws IOException {
        final FileOutputStream output = openOutputStream(destination);
        try {
        	//done
        	copy(source, output);
            output.close(); // don't swallow close Exception if copy completes normally
        } finally {
        	//done
        	closeQuietly(output);
        }
    }
	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static FileOutputStream openOutputStream(final File file) throws IOException {
        return openOutputStream(file, false);
    }
	/**
	 * 
	 * @param file
	 * @param append
	 * @return
	 * @throws IOException
	 */
	 public static FileOutputStream openOutputStream(final File file, final boolean append) throws IOException {
	        if (file.exists()) {
	            if (file.isDirectory()) {
	                throw new IOException("File '" + file + "' exists but is a directory");
	            }
	            if (file.canWrite() == false) {
	                throw new IOException("File '" + file + "' cannot be written to");
	            }
	        } else {
	            final File parent = file.getParentFile();
	            if (parent != null) {
	                if (!parent.mkdirs() && !parent.isDirectory()) {
	                    throw new IOException("Directory '" + parent + "' could not be created");
	                }
	            }
	        }
	        return new FileOutputStream(file, append);
	    }
	 /**
	  * 
	  * @param input
	  * @param output
	  * @return
	  * @throws IOException
	  */
	 public static int copy(InputStream input, OutputStream output)
				throws IOException {
			long count = copyLarge(input, output);
			if (count > Integer.MAX_VALUE) {
				return -1;
			}
			return (int) count;
		}
	 	/**
	 	 * 
	 	 * @param input
	 	 * @param output
	 	 * @return
	 	 * @throws IOException
	 	 */
		public static long copyLarge(InputStream input, OutputStream output)
				throws IOException {
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
		 * 
		 * @param input
		 */
		public static void closeQuietly(InputStream input) {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException ioe) {
				// ignore
			}
		}
		/**
		 * 
		 * @param output
		 */
		public static void closeQuietly(OutputStream output) {
	        try {
	            if (output != null) {
	                output.close();
	            }
	        } catch (IOException ioe) {
	            // ignore
	        }
	    }
	 
	 

	
}

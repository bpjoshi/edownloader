package com.bpjoshi.edownloader;

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
	 * This method should be called with EDownloadProps object as an argument.
	 * @param props
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static void downloadFile(EDownloadProps props)
	throws MalformedURLException, IOException {
		Properties systemSettings = System.getProperties();
        if(props.isBehindAProxy()){
        	systemSettings.put("proxySet", "true");
        	System.setProperty("java.net.useSystemProxies", "true");
        	if(props.getHttps_Proxy_Host()!=null && props.getHttps_Proxy_Port()!=null){
        		systemSettings.put("https.proxyHost", props.getHttps_Proxy_Host());
                systemSettings.put("https.proxyPort", props.getHttps_Proxy_Port());
        	}
        	if(props.getHttp_Proxy_Host()!=null && props.getHttp_Proxy_Port()!=null){
        		systemSettings.put("http.proxyHost", props.getHttp_Proxy_Host());
                systemSettings.put("http.proxyPort", props.getHttp_Proxy_Port());
        	}
        }
        saveURLContentToFile(new URL(props.getSourceUrl()), new File(props.getFullFileName()));
	}
	
	/**
	 * 
	 * @param downloadSource
	 * @param destinationFile
	 * @throws IOException
	 */
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
        //Finally!, method call to download from url
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

package com.bpjoshi.edownloader;
/**
 * Class for setting properties for file to be downloaded.
 * @author bpjoshi(Bhagwati Prasad)
 * 
 *	Description of Arguments to downloadFile method:
 * 1. sourceUrl: Enter full url of the file content to be downloaded. For example : 
 * 				https://www.uop.edu.jo/download/research/members/oxford_guide_to_english_grammar.pdf
 * 2. locationToSaveFile: Enter full name of the location where you want to save the file:
 * 				(eg. classpath of your maven project: ) <quote>src/main/resources/englishgrammar.pdf</quote>
 * 				(eg. location relative to your current program: ) <quote>../../bpjoshi/englishgrammar.pdf</quote>
 * 				(eg. absolute path: ) <quote>d:/myfiles/main/resources/englishgrammar.pdf</quote>
 * 3. behindAProxy: If you are behind a proxy, provide proxy settings and set this flag to true
 * 4. Use this method when there is to be used when there is no authentication. The URL can be either https or http
 * 
 */
public class EDownloadProps {
	private String sourceUrl;
	private String username;
	private String password;
	private String fullFileName;
	private String https_Proxy_Host;
	private String http_Proxy_Host;
	private String https_Proxy_Port;
	private String http_Proxy_Port;
	private boolean behindAProxy;
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullFileName() {
		return fullFileName;
	}
	public void setFullFileName(String fullFileName) {
		this.fullFileName = fullFileName;
	}
	public String getHttps_Proxy_Host() {
		return https_Proxy_Host;
	}
	public void setHttps_Proxy_Host(String https_Proxy_Host) {
		this.https_Proxy_Host = https_Proxy_Host;
	}
	public String getHttp_Proxy_Host() {
		return http_Proxy_Host;
	}
	public void setHttp_Proxy_Host(String http_Proxy_Host) {
		this.http_Proxy_Host = http_Proxy_Host;
	}
	public String getHttps_Proxy_Port() {
		return https_Proxy_Port;
	}
	public void setHttps_Proxy_Port(String https_Proxy_Port) {
		this.https_Proxy_Port = https_Proxy_Port;
	}
	public String getHttp_Proxy_Port() {
		return http_Proxy_Port;
	}
	public void setHttp_Proxy_Port(String http_Proxy_Port) {
		this.http_Proxy_Port = http_Proxy_Port;
	}
	public boolean isBehindAProxy() {
		return behindAProxy;
	}
	public void setBehindAProxy(boolean behindAProxy) {
		this.behindAProxy = behindAProxy;
	}
}

package com.bpjoshi.github.edownloader;
/**
 * Class for setting properties for file to be downloaded.
 * @author bpjoshi(Bhagwati Prasad)
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

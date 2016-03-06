

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
/**
 * 
 * @author Perez4all
 * 
 * **/

public class Updaterss {
	
	private final static String historyURL = "http://WEBPAGE/ClientVersion/history.html";


	 public static String getLatestVersion() throws IOException
	
	     {
	
		 String data = getData("http://WEBPAGE/ClientVersion/version.html");

	
		 return data.substring(data.indexOf("[version]")+9,data.indexOf("[/version]"));

	     }

	     public static String getWhatsNew() throws IOException

	     {
	    	 
	    	String data = getData(historyURL);
	    
	    	return data.substring(data.indexOf("[history]")+9,data.indexOf("[/history]"));


	     }	
	     private static String getData(String address) throws IOException
	
	     {
	    	 
	    	URL url = new URL(address);
	    	InputStream html = null;
	    
	    	html = url.openStream();
	    
	    	int c = 0;
	    	 
	    	StringBuffer buffer = new StringBuffer("");
	    	
	    	 while(c != -1) {
	    	
	    	 c = html.read();
	    	
	    	 buffer.append((char)c);
	    	
	    	         }
	    	
	    	 return buffer.toString();
	          

	     }

	
}

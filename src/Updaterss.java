

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
/**
 * 
 * @author Alex GOURBILIERE
 * 
 * **/

public class Updaterss {
	
	private final static String historyURL = "http://mu.magestic.eu/Launcher/history.html";


	 public static String getLatestVersion() throws IOException
	
	     {
	
		 String data = getData("http://mu.magestic.eu/Launcher/version.html");

	
		 return data.substring(0,(data.length())-1);

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

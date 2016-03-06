
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;

import net.lingala.zip4j.*;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;
/**
 * 
 * @author Perez4all
 * 
 * 
 * **/




public class Zipperss {


	private ZipFile zip;
	private JProgressBar bar;
	private JLabel label;

	List<String> fileList;
 
	
	
	public Zipperss() throws ZipException
	{
		
		/*zip = new ZipFile("update.zip");
		zip.setRunInThread(true);*/
		
	}
	 /**
     * Unzip it
     * @param zipFile input zip file
     * @param output zip file output folder
     */
    public void UnZip(){
 
     byte[] buffer = new byte[1024];
 
     try{
 
    	//create output directory is not exists
    	File folder = new File(System.getProperty("user.dir"));
    	if(!folder.exists()){
    		folder.mkdir();
    	}
 
    	//get the zip file content
    	ZipInputStream zis = 
    		new ZipInputStream(new FileInputStream("update.zip"));
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();
 
    	while(ze!=null){
 
    	   String fileName = ze.getName();
           File newFile = new File(System.getProperty("user.dir") + File.separator + fileName);
 
           System.out.println("file unzip : "+ newFile.getAbsoluteFile());
 
            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();
 
            FileOutputStream fos = new FileOutputStream(newFile);             
 
            int len;
            while ((len = zis.read(buffer)) > 0) {
       		fos.write(buffer, 0, len);
            }
 
            fos.close();   
            ze = zis.getNextEntry();
    	}
 
        zis.closeEntry();
    	zis.close();
 
    	System.out.println("Done" + System.getProperty("user.dir"));
 
    }catch(IOException ex){
       ex.printStackTrace(); 
    }
   }    
	
	
	/*public boolean unZip() 
	{
		
		try {
			
		
				
				
		zip.extractAll(System.getProperty("user.dir"));
		
		ProgressMonitor monitor = zip.getProgressMonitor();
			
			while(monitor.getState() == ProgressMonitor.STATE_BUSY)
			{
				
				getLabel().setText("Extrayendo la actualizacion.." + " (" + monitor.getPercentDone()+"%)");
				getBar().setValue(monitor.getPercentDone());
				
				
			}
			
			
		
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	
		
	}*/
	public JProgressBar getBar() {
		return bar;
	}
	public void setBar(JProgressBar progressBar) {
		this.bar = progressBar;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	

	
}

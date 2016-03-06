

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;



import java.awt.SystemColor;



public class MMLauncherss extends JFrame  {

	private String ver ;
	private Thread downloader;
    private boolean runState = true;
	private JPanel contentPane;
	private JLabel lblPlay,lblNewLabel,lblLoad;
	private JProgressBar progressBar;
	private static JFrame myparent;
	private String temp = ""; 
	private Zipperss zip;
	


	/**
	 * 
	 * @author Perez4all
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MMLauncherss frame = new MMLauncherss();
					 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frame.setVisible(true);
				} catch (Exception e) {
			
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MMLauncherss()  throws IOException {
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setBackground(new Color(0,0,0,0));
		setMyparent(this);
		
		ImageIcon icon22 = new ImageIcon("Data/Update/32x32xx.png");
		this.setIconImage(icon22.getImage());
		
		ImageIcon icon = new ImageIcon("Data/Update/back2a.png");
		contentPane.setLayout(null);
		
	
	
		lblNewLabel = new JLabel("Recherche de mises à jour.");
		SwingWorker work = new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				// TODO Auto-generated method stub
			
		
				if(Float.parseFloat(Updaterss.getLatestVersion()) > Float.parseFloat(readVer()) && Float.parseFloat(readVer()) != Float.parseFloat(Updaterss.getLatestVersion())-0.1f)
				{
					
				
					runState = false;
					lblPlay.setEnabled(false);
					
					lblNewLabel.setText("Mise à jour disponible!");
		
					Thread.sleep(1200);
					lblNewLabel.setText("Contact avec le serveur de téléchargement...");
					downloadAll();
					
					
					File file = new File("version.ver");
					 
					// if file doesnt exists, then create it
					
						try {
							
							if (!file.exists()) {
								
							file.createNewFile();
							
							}
							
							FileWriter fw = new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write("[version]"+Updaterss.getLatestVersion()+"[/version]");
							bw.close();
		
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						
					}
				
					
				}
				else if(Float.parseFloat(Updaterss.getLatestVersion())-0.1f == Float.parseFloat(readVer()))
				{
					
					
					runState = false;
					lblPlay.setEnabled(false);
					
					lblNewLabel.setText("Mise à jour disponible!");
		
					Thread.sleep(1200);
					lblNewLabel.setText("Contact avec le serveur de téléchargement...");
					downloadLatest();
					
					
					File file = new File("version.ver");
					 
					// if file doesnt exists, then create it
					
						try {
							
							if (!file.exists()) {
								
							file.createNewFile();
							
							}
							
							FileWriter fw = new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write("[version]"+Updaterss.getLatestVersion()+"[/version]");
							bw.close();
		
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						
					}
	
					
				}
				else
				{
				
					for(int i = 0; i<3;i++)
					{
					lblNewLabel.setText("Recherche de mise à jour. .");
					Thread.sleep(500);
					lblNewLabel.setText("Recherche de mise à jour. . .");
					Thread.sleep(500);
					lblNewLabel.setText("Recherche de mise à jour.");
					Thread.sleep(500);
					}
					lblNewLabel.setText("Aucune mise à jour nécessaire");
					
				}

				
		
				return null;
			}
		};
	
		work.execute();
		
		final ImageIcon icon3 = new ImageIcon("Data/Update/jugar.png");
		lblPlay = new JLabel("");
		lblPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				ImageIcon icon4 = new ImageIcon("Data/Update/fires.png");
				lblPlay.setLocation(lblPlay.getX(),lblPlay.getY()-1);
				lblPlay.setIcon(icon4);
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				lblPlay.setLocation(lblPlay.getX(),lblPlay.getY()+1);
				lblPlay.setIcon(icon3);
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// If there is an update
				if(runState == true)
				{
				
				
			        /**
			         * To launch the process, the manifest is needed. It's compiled with Launch4j which create a .exe.
			         * Config : JRE Minimum Version 1.0.0
			         * 			Header : Application type selection, add manifest
			         * THE NAME OF THE MANIFEST NEED TO BE THE SAME THAN THE EXPORTED JAR.
			        **/
			        
			        try {
			        	Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\main.exe").start();
						//Process pp=run.exec(System.getProperty("user.dir")+"\\Starter.exe" , "-fullscreen");
			  
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e1);
					}
			        
			        
			        
			        
			        
			        
			        dispose();
				
			        
			        
			        
				}
				else
				{
					
					JOptionPane.showMessageDialog(contentPane, "S'il vous plait, veuillez attendre la fin de la mise à jour", "Actualisation", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
			}
		});
		
		final JLabel lblMuMagestic = new JLabel("Mu Magestic 2016\u00A9 ");
		lblMuMagestic.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMuMagestic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				
				lblMuMagestic.setForeground(Color.LIGHT_GRAY);
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				
				lblMuMagestic.setForeground(Color.GRAY);
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JOptionPane.showMessageDialog(null, "Crédits: Mu Magestic\nSite Web : mu.magestic.eu","A propos de..",JOptionPane.INFORMATION_MESSAGE);
				
				
			}
		});
		ImageIcon iconM = new ImageIcon("Data/Update/32x32xx.png");
		
		lblLoad = new JLabel("");
		lblLoad.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoad.setVisible(false);
		lblLoad.setBounds(701, 404, 89, 58);
		contentPane.add(lblLoad);
		JLabel iconMM = new JLabel("");
		iconMM.setHorizontalAlignment(SwingConstants.CENTER);
	
		iconMM.setIcon(iconM);
		iconMM.setBounds(717, 85, 173, 75);
		contentPane.add(iconMM);
		
		JLabel notice = new JLabel("New label");
		notice.setFont(new Font("Buxton Sketch", Font.PLAIN, 14));
		notice.setForeground(SystemColor.menu);
		notice.setBorder(null);
		notice.setBounds(363, 260, 300, 89);
		contentPane.add(notice);
		
			try{
			notice.setText(Updaterss.getWhatsNew());
			}catch(Exception e)
			{}
	
		


		
		lblMuMagestic.setForeground(Color.GRAY);
		lblMuMagestic.setBounds(571, 454, 111, 14);
		contentPane.add(lblMuMagestic);
		lblPlay.setIcon(icon3);
		lblPlay.setBounds(705, 414, 96, 37);
		contentPane.add(lblPlay);
		
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel.setBounds(363, 411, 184, 14);
		contentPane.add(lblNewLabel);
		
	
		progressBar = new JProgressBar();
		progressBar.setBounds(366, 430, 297, 4);
		progressBar.setMaximum(100);
		progressBar.setBackground(new Color(0,0,0,0));
		progressBar.setBorder(null);
		progressBar.setMinimum(0);
		contentPane.add(progressBar);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Site Web");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				try{
				openURL("WEBPAGE URL");
				}catch(Exception e)
				{}
			}
		});
		lblNewLabel_3.setBorder(null);
		lblNewLabel_3.setBounds(687, 238, 106, 23);
		contentPane.add(lblNewLabel_3);
		
		
		
		JLabel lblForo = new JLabel("Forum");
		lblForo.setHorizontalAlignment(SwingConstants.CENTER);
		lblForo.setForeground(Color.WHITE);
		lblForo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				
			}
		});
		lblForo.setBorder(null);
		lblForo.setBounds(686, 271, 106, 23);
		contentPane.add(lblForo);
		
		JLabel lblRanking = new JLabel("Classements");
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setForeground(Color.WHITE);
		lblRanking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try{
				openURL("RANKINGS URL");
				}catch(Exception e)
				{
					
					
					
				}
				
			}
		});
		lblRanking.setBorder(null);
		lblRanking.setBounds(687, 305, 106, 23);
		contentPane.add(lblRanking);
		
		JLabel lblConfiguracion = new JLabel("Configuration");
		lblConfiguracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfiguracion.setForeground(Color.WHITE);
		lblConfiguracion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Configss config = new Configss();
				config.setVisible(true);
				
				
			}
		});
		lblConfiguracion.setBorder(null);
		lblConfiguracion.setBounds(689, 337, 106, 23);
		contentPane.add(lblConfiguracion);
		JLabel lblTitulo = new JLabel("");

		lblTitulo.setBounds(434, 131, 314, 75);
		contentPane.add(lblTitulo);
		
		JLabel lblMinimize = new JLabel("Voici le label");
		lblMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setState(Frame.ICONIFIED);
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
			}
		});
		lblMinimize.setBounds(766, 186, 16, 16);
		contentPane.add(lblMinimize);
		lblMinimize.setBorder(null);
		
		JLabel lblClose = new JLabel("Voici le label");
		lblClose.setBounds(785, 186, 16, 16);
		contentPane.add(lblClose);
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				dispose();
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				
				
			}
		});
		
		lblClose.setBorder(null);
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 890, 590);
		contentPane.add(lblBackground);
		lblBackground.setIcon(icon);
		
		setLocationRelativeTo(null);
		
		

		
	}

	private String readVer()
	{
		

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("version.ver"));
		
		//asi funciona con getresource astream
		while(reader.ready())
		{
			ver = reader.readLine();
			
		}

		ver = ver.substring(ver.indexOf("[version]")+9,ver.indexOf("[/version]"));
		
		reader.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se ecuentra version.ver");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ver;
		
		
	}
	
	        
	public static void openURL(String url) {
        String osName = System.getProperty("os.name");
        try {
            if (osName.startsWith("Windows")) {
            	
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
                
            } else if (osName.startsWith("Mac OS X")) {
    
                Runtime.getRuntime().exec("open " + url);
                
            } else {
                JOptionPane.showMessageDialog(null,"S'il vous plait, ouvrez votre navigateur web et allez sur la page : "+ url);
            }
        } catch (IOException e) {
            System.out.println("Échec de l'ouverture de l'URL : " + url);
            e.printStackTrace();
        }
    }
	
	public void downloadLatest()
	{
		lblPlay.setVisible(false);
		ImageIcon licon = new ImageIcon("Data/Update/load.gif");
		lblLoad.setIcon(licon);
		lblLoad.setVisible(true);
		
		
		downloader = new Thread(
			
				        new Runnable(){
				
				            public void run()
				
				            {
				
				                try {
	
				                    downloadFile(getDownloadLinkLatest());
				                    
				            
				                    zip = new Zipperss();
				                    zip.setBar(progressBar);
				                    zip.setLabel(lblNewLabel);
				                    zip.UnZip();
				                    
				         
				                    cleanup();
				                    
				                    lblPlay.setEnabled(true);

		
				                    runState = true;
				                    
				                    lblLoad.setVisible(false);
				                    lblPlay.setVisible(true);
				                    lblNewLabel.setText("Mise à jour terminée !!");
				                 
				
				                } catch (Exception ex) {
				
				                    ex.printStackTrace();
				
				                    JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour !");
			
				                }
			
				            }
				
				        });
				
				        downloader.start();
		
	}
	
	
	public void downloadAll()
	{
		
		lblPlay.setVisible(false);
	 	ImageIcon licon = new ImageIcon("Data/Update/load.gif");
		lblLoad.setIcon(licon);
		lblLoad.setVisible(true);
		
		downloader = new Thread(
			
				        new Runnable(){
				
				            public void run()
				
				            {
				
				                try {
	
				                    downloadFile(getDownloadLinkFromHost());
				                    
				            
				                    zip = new Zipperss();
				                    zip.setBar(progressBar);
				                    zip.setLabel(lblNewLabel);
				                    zip.UnZip();
				                    
				         
				                    cleanup();
				                    
				                    lblPlay.setEnabled(true);

		
				                    runState = true;
				                    
				                    
				                    lblLoad.setVisible(false);
				                    lblPlay.setVisible(true);
				                    lblNewLabel.setText("Mise à jour terminée !!");
				                 
				                    
				                } catch (Exception ex) {
				
				                    ex.printStackTrace();
				
				                    JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour !");
			
				                }
			
				            }
				
				        });
				
				        downloader.start();
		
	}
	private void cleanup()

	    {
		
		System.gc();
	
		 lblNewLabel.setText("Nettoyage des fichiers temporaires...");

		  String path = System.getProperty("user.dir")+"\\update.zip";
	        File f = new File(path);

	        try {
	            if (java.nio.file.Files.deleteIfExists(f.toPath())) {
	               
	            	lblNewLabel.setText("Fichiers temporaires nettoyés");
	            	
	            } else {
	            	
	            	lblNewLabel.setText("S'il vous plait, veuillez supprimer le fichier .zip.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        

	    }


	private void downloadFile(String link) throws MalformedURLException, IOException

	    {

			//progressBar.setMaximum(1000000000);
		
	        URL url = new URL(link);

	        URLConnection conn = url.openConnection();
	
	        InputStream is = conn.getInputStream();
	
	        long max = conn.getContentLength();
	
	        lblNewLabel.setText("Chargement... Taille de la mise à jour : "+max+" octets");
	        
			progressBar.setMaximum((int)max);
	
	        BufferedOutputStream fOut = new BufferedOutputStream(new FileOutputStream(new File("update.zip")));
	
	        byte[] buffer = new byte[32 * 1024];
	
	        int bytesRead = 0;
	
	        int in = 0;
	
	        while ((bytesRead = is.read(buffer)) != -1) {

	            in += bytesRead;
	           
	            progressBar.setValue(in);

	            fOut.write(buffer, 0, bytesRead);

	        }
	
	
	        fOut.flush();

	        fOut.close();
	
	        is.close();
	
	    
	        lblNewLabel.setText("Téléchargement terminé !");
	
	        progressBar.setValue(0);

	    }
	private String getDownloadLinkFromHost() throws MalformedURLException, IOException
	
	    {
		
		
	
	        String path = "WEBPAGE/ClientVersion/url.html";
	
	        URL url = new URL(path);

	 
	
	        InputStream html = null;
	
	 
	
	        html = url.openStream();
	
	 
	
	        int c = 0;
	
	        StringBuilder buffer = new StringBuilder("");

	 
	
	        while(c != -1) {

	            c = html.read();

	        buffer.append((char)c);

	 

	        }
	
	        return buffer.substring(buffer.indexOf("[url]")+5,buffer.indexOf("[/url]"));
	
	    }
	
	private String getDownloadLinkLatest() throws MalformedURLException, IOException
	
	    {
	
	        String path = "WEBPAGE/ClientVersion/latest.html";
	
	        URL url = new URL(path);

	 
	
	        InputStream html = null;
	
	 
	
	        html = url.openStream();
	
	 
	
	        int c = 0;
	
	        StringBuilder buffer = new StringBuilder("");

	 
	
	        while(c != -1) {

	            c = html.read();

	        buffer.append((char)c);

	 

	        }
	
	        return buffer.substring(buffer.indexOf("[url]")+5,buffer.indexOf("[/url]"));
	
	    }
	
	
	public static JFrame getMyparent() {
		return myparent;
	}

	public static void setMyparent(JFrame myparent) {
		MMLauncherss.myparent = myparent;
	}
	}
	



import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
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
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Alex GOURBILIERE
 * TODO Refactoring
 *
 */

@SuppressWarnings("deprecation")
public class MMLauncherss extends JFrame  {

	private String ver ;
	private Thread downloader;
    private boolean runState = true;
	private JPanel contentPane;
	private JLabel labelPlay,labelCenter,labelLoad;
	private JProgressBar progressBar;
	private static JFrame parentJFrame;
	private Zipperss zip;
	private Configss config;
    private Point mousePosition;
	


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
					e.printStackTrace();
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
		contentPane.setLayout(null);

		labelCenter = new JLabel("Recherche de mises à jour.");
		
		// Add the name of the server
		createLabelNameServer();
	
		// Make the launcher movable
		makeJFrameMovable();
		
		// Do the update
		SwingWorker work = createSwingWorker();	
		work.execute();
		
		// Create play label & button
		createPlayComponents();
		
		// Create the label for credits (at bottom)
		createLabelCredits();
		
		// Create the label for load icon
		createLabelLoad();
		
		// We get the distant file history.html to display it
		createLabelNotice();
		
		// Create the label above the progress bar
		createLabelCenter();
		
		// Create the progress bar
		createProgressBar();
		
		// Create label for WebSite link
		createLabelWebsite();
		
		// Create label for Forum link
		createLabelForum();
		
		// Create label for Rankings link
		createLabelRankings();
		
		// Create label for Config JFrame
		createLabelConfig();
		
		// Create label to minimize the launcher
		createLabelMinimize();
		
		// Create label to close the launcher
		createLabelClose();
		
		// Create label for the background
		createLabelBackground();
		
		// Launcher position relative to nothing
		setLocationRelativeTo(null);	
		
		// Set the icon of the launcher
		setLauncherIcon("/favicon.png");	
	}
	
	
	/**
	 * Create the label which displays the name of the server
	 */
	private void createLabelNameServer() {
		ImageIcon serverNameIcon = new ImageIcon(new ImageIcon(MMLauncherss.class.getResource("/server_name.png")).getImage().getScaledInstance(160, 33, Image.SCALE_DEFAULT));
		
		JLabel serverNameLabel = new JLabel("");
		serverNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		serverNameLabel.setIcon(serverNameIcon);
		serverNameLabel.setBounds(460, 140, 190, 75);
		contentPane.add(serverNameLabel);
	}
	
	/**
	 * Create play label to launch the game
	 */
	private void createPlayComponents() {
		final ImageIcon iconPlay = new ImageIcon(MMLauncherss.class.getResource("/jouer.png"));
		
		labelPlay = new JLabel("");
		labelPlay.setIcon(iconPlay);
		labelPlay.setBounds(705, 414, 96, 37);
		labelPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				ImageIcon icon4 = new ImageIcon(MMLauncherss.class.getResource("/jouer_hover.png"));
				setCursor(Cursor.HAND_CURSOR);
				labelPlay.setLocation(labelPlay.getX(),labelPlay.getY());
				labelPlay.setIcon(icon4);
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				labelPlay.setLocation(labelPlay.getX(),labelPlay.getY());
				labelPlay.setIcon(iconPlay);
				setCursor(Cursor.DEFAULT_CURSOR);
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// If there is no update in process
				if(runState == true)
				{
			        /**
			         * To launch the process, the manifest is needed. It's compiled with Launch4j which create a .exe.
			         * Config : JRE Minimum Version 1.0.0
			         * 			Header : Application type selection, add manifest
			         * THE NAME OF THE MANIFEST NEED TO BE THE SAME THAN THE EXPORTED JAR.
			        **/
			        
			        try {
			        	new ProcessBuilder(new String[] {"cmd.exe", "/C", System.getProperty("user.dir")+"\\main.exe"}).start();
					} catch (IOException error) {
						JOptionPane.showMessageDialog(parentJFrame, "Problème lors du lancement du jeu. Veuillez contacter un administrateur.\n" + error.getMessage(), "Erreur lors du lancement du jeu", JOptionPane.ERROR_MESSAGE);
					}
			        
					// Try to close Config's JFrame to close it if it's still opened
					try {
						config.dispose();
					} catch (Exception e2) {
						
					}
			        
			        dispose();  
				} else {
					// Indicate to the user that he needs to wait the end of update before launching the game
					JOptionPane.showMessageDialog(contentPane, "S'il vous plait, veuillez attendre la fin de la mise à jour", "Actualisation", JOptionPane.INFORMATION_MESSAGE);
				}	
			}
		});

		contentPane.add(labelPlay);
	}
	
	
	/**
	 * Create the label for credits
	 */
	private void createLabelCredits() {
		final JLabel lblMuMagestic = new JLabel("Mu Magestic 2016\u00A9 ");
		lblMuMagestic.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMuMagestic.setForeground(Color.GRAY);
		lblMuMagestic.setBounds(571, 454, 111, 14);
		lblMuMagestic.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblMuMagestic.setForeground(Color.LIGHT_GRAY);
	    		setCursor(Cursor.HAND_CURSOR);
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblMuMagestic.setForeground(Color.GRAY);
	    		setCursor(Cursor.DEFAULT_CURSOR);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Crédits: Mu Magestic\nSite Web : mu.magestic.eu","A propos de..",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		contentPane.add(lblMuMagestic);
	}
	
	
	/**
	 * Create the label for load icon
	 */
	private void createLabelLoad() {
		labelLoad = new JLabel("");
		labelLoad.setHorizontalAlignment(SwingConstants.CENTER);
		labelLoad.setVisible(false);
		labelLoad.setBounds(701, 404, 89, 58);
		
		contentPane.add(labelLoad);
	}
	
	
	/**
	 * Create the notice label (which display a distant file)
	 */
	private void createLabelNotice() {
		try {
			URL url = new URL("http://mu.magestic.eu/Launcher/history.php"); 
			InputStreamReader ipsr = new InputStreamReader(url.openStream()); 
			BufferedReader reader = new BufferedReader(ipsr); 
			
			String labelNotice = null;
			while(reader.ready())
			{
				labelNotice = reader.readLine();
				
			}
			reader.close();
			
			JLabel notice = new JLabel(labelNotice);
			notice.setFont(new Font("Buxton Sketch", Font.PLAIN, 14));
			notice.setForeground(SystemColor.menu);
			notice.setBorder(null);
			notice.setBounds(363, 260, 300, 89);
			contentPane.add(notice);
		} catch (Exception error) {
			JOptionPane.showMessageDialog(parentJFrame, "Problème lors de la récupération ou de l'affichage du contenu du launcher. Veuillez contacter un administrateur.\n" + error.getMessage(), "Erreur lors du lancement du jeu", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	/**
	 * Create the label above the progress bar
	 */
	private void createLabelCenter() {
		labelCenter.setForeground(Color.WHITE);
		labelCenter.setFont(new Font("Tahoma", Font.PLAIN, 8));
		labelCenter.setBounds(363, 411, 184, 14);
		
		contentPane.add(labelCenter);
	}
	
	
	/**
	 * Create the progress bar
	 */
	private void createProgressBar() {
		progressBar = new JProgressBar();
		progressBar.setBounds(366, 430, 297, 4);
		progressBar.setMaximum(100);
		progressBar.setBackground(new Color(0,0,0,0));
		progressBar.setBorder(null);
		progressBar.setMinimum(0);
		
		contentPane.add(progressBar);
	}
	
	
	/**
	 *  Create label for WebSite link
	 */
	private void createLabelWebsite() {
		final JLabel labelWebsite = new JLabel("Site Web");
		labelWebsite.setHorizontalAlignment(SwingConstants.CENTER);
		labelWebsite.setForeground(Color.WHITE);
		labelWebsite.setBorder(null);
		labelWebsite.setBounds(687, 238, 106, 23);
		labelWebsite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				setCursor(Cursor.HAND_CURSOR);
				labelWebsite.setForeground(new Color(208, 0, 0));
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
				labelWebsite.setForeground(Color.white);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				try{
					openURL("http://mu.magestic.eu/");
				}catch(Exception e)
				{}
			}
		});
		
		contentPane.add(labelWebsite);
	}
	
	
	/**
	 * Create label for forum link
	 */
	private void createLabelForum() {
		final JLabel labelForum = new JLabel("Forum");
		labelForum.setHorizontalAlignment(SwingConstants.CENTER);
		labelForum.setForeground(Color.WHITE);
		labelForum.setBorder(null);
		labelForum.setBounds(686, 271, 106, 23);
		labelForum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				setCursor(Cursor.HAND_CURSOR);
				labelForum.setForeground(new Color(208, 0, 0));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
				labelForum.setForeground(Color.white);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					openURL("http://mu.magestic.eu/Forum");
				}catch(Exception e2)
				{}
			}
		});
		
		contentPane.add(labelForum);
	}
	
	
	/**
	 * Create the label for Rankings link
	 */
	private void createLabelRankings() {
		final JLabel lblRanking = new JLabel("Classements");
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setForeground(Color.WHITE);
		lblRanking.setBorder(null);
		lblRanking.setBounds(687, 305, 106, 23);
		lblRanking.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				setCursor(Cursor.HAND_CURSOR);
				lblRanking.setForeground(new Color(208, 0, 0));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
				lblRanking.setForeground(Color.white);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					openURL("http://mu.magestic.eu/Classement_Joueurs");
				} catch(Exception e){}
			}
		});
		
		contentPane.add(lblRanking);
	}
	
	
	/**
	 * Create the label for opening config JFrame
	 */
	private void createLabelConfig() {
		final JLabel labelConfiguration = new JLabel("Configuration");
		labelConfiguration.setHorizontalAlignment(SwingConstants.CENTER);
		labelConfiguration.setForeground(Color.WHITE);
		labelConfiguration.setBorder(null);
		labelConfiguration.setBounds(689, 337, 106, 23);
		labelConfiguration.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				setCursor(Cursor.HAND_CURSOR);
				labelConfiguration.setForeground(new Color(208, 0, 0));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
				labelConfiguration.setForeground(Color.white);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				config = new Configss();
				config.setVisible(true);
				
				
			}
		});
		
		contentPane.add(labelConfiguration);
	}
	
	
	/**
	 * Create the label to minimize the launcher
	 */
	private void createLabelMinimize() {
		JLabel labelMinimize = new JLabel("");
		labelMinimize.setBounds(766, 186, 16, 16);
		labelMinimize.setBorder(null);
		labelMinimize.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(Frame.ICONIFIED);
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				setCursor(Cursor.HAND_CURSOR);	
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);	
			}
		});
		
		contentPane.add(labelMinimize);
	}
	
	
	/**
	 * Create the label to close the launcher
	 */
	private void createLabelClose() {
		JLabel labelClose = new JLabel("");
		labelClose.setBounds(785, 186, 16, 16);
		labelClose.setBorder(null);
		labelClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Try to close Config's JFrame to close it if it's still opened
				try {
					config.dispose();
				} catch (Exception e) {}
				
				dispose();
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				setCursor(Cursor.HAND_CURSOR);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);	
			}
		});
		
		contentPane.add(labelClose);
	}
	
	/**
	 * Create the label for the background
	 */
	private void createLabelBackground() {
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 890, 590);
		
		ImageIcon iconBackground = new ImageIcon(MMLauncherss.class.getResource("/back2a.png"));
		lblBackground.setIcon(iconBackground);
		
		contentPane.add(lblBackground);
	}
	
	/**
	 * Make the JFrame movable
	 */
	private void makeJFrameMovable() {
        mousePosition = null;
        
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
	               mousePosition = null;
	        }
            public void mousePressed(MouseEvent e) {
                mousePosition = e.getPoint();
            }
		});

		this.addMouseMotionListener(new MouseMotionListener(){
            public void mouseMoved(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - mousePosition.x, currCoords.y - mousePosition.y);
            }
        });
	}
	
	
	/**
	 * Set the icon of the launcher
	 * 
	 * @param iconPath
	 * 		Path to the image
	 */
	private void setLauncherIcon(String iconPath) {
		ImageIcon iconLauncher = new ImageIcon(MMLauncherss.class.getResource(iconPath));
		this.setIconImage(iconLauncher.getImage());
	}
	
	
	/**
	 * Create the SwingWorker for updating
	 * @return
	 * 		the SwingWorker
	 */
	private SwingWorker createSwingWorker() {
		return new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				
				if(Float.parseFloat(Updaterss.getLatestVersion()) > Float.parseFloat(readVer())) {
					
					doUpdate();
					
				} else {
				
					for(int i = 0; i<2;i++) {
						// Simule for the client a search of update (already done)
						labelCenter.setText("Recherche de mise à jour. .");
						Thread.sleep(500);
						labelCenter.setText("Recherche de mise à jour. . .");
						Thread.sleep(500);
						labelCenter.setText("Recherche de mise à jour.");
						Thread.sleep(500);
					}
					
					labelCenter.setText("Aucune mise à jour nécessaire");
				}

				
		
				return null;
			}
		};
	}
	
	
	/**
	 * Update the game
	 */
	private void doUpdate() {
		// Cannot run the game
		runState = false;
		// Disable Play label
		labelPlay.setEnabled(false);
		
		// Warn the user that there is an update available
		labelCenter.setText("Mise à jour disponible!");

		try {
			// Temporisation to let the user see the label above
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			
		}
		
		// Warn the user that we download the files on server
		labelCenter.setText("Contact avec le serveur de téléchargement...");
		
		// Download the files on server
		update();
		
		
		File file = new File("version.ver");
		 
		try {
			// Write the version written on the server in the clint folder
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("[version]"+Updaterss.getLatestVersion()+"[/version]");
			bw.close();
		} catch (IOException error) {
			JOptionPane.showMessageDialog(parentJFrame, "Problème lors de la mise à jour du fichier version.ver. Veuillez contacter un administrateur.\n" + error.getMessage(), "Erreur lors de la vérification de la version", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	/**
	 * Read the actual version of the game
	 * @return
	 * 		Version of the game
	 */
	private String readVer() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("version.ver"));
		
			// Read version.ver file with a reader
			while(reader.ready())
			{
				ver = reader.readLine();
				
			}

			ver = ver.substring(ver.indexOf("[version]")+9,ver.indexOf("[/version]"));
		
			reader.close();
		
		} catch (FileNotFoundException e) {
			// File creation with version 0.0
			File file = new File("version.ver");
			FileWriter fw;
			try {
				fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("[version]0.0[/version]");
				bw.close();
				
				// We call again readVer()
				readVer();
			} catch (IOException error) {
				JOptionPane.showMessageDialog(parentJFrame, "Problème lors de la création du fichier version.ver. Veuillez contacter un administrateur.\n" + error.getMessage(), "Erreur lors de la vérification de la version", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (IOException error) {
			JOptionPane.showMessageDialog(parentJFrame, "Problème lors de la lecture du fichier version.ver. Veuillez contacter un administrateur.\n" + error.getMessage(), "Erreur lors de la vérification de la version", JOptionPane.ERROR_MESSAGE);
		}
		
		return ver;	
	}
	

	/**
	 * Open an URL according to the Operating System
	 * 
	 * @param url
	 * 		Url to open
	 */
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
	
	
	/**
	 * Manage the download and the replacement of files
	 */
	public void update() {
		// Set the load icon to show that ther is a loading
	 	ImageIcon licon = new ImageIcon(MMLauncherss.class.getResource("/load.gif"));
		labelLoad.setIcon(licon);
		labelLoad.setVisible(true);
		
		downloader = new Thread(
			
				        new Runnable(){
				        	public void run() {
				
				                try {
				                	// Download the zip which contains files to update
				                    downloadFile(getDownloadLinkFromHost());
				                    
				                    // Unzip the temporary zip file to the client folders
				                    zip = new Zipperss();
				                    zip.setBar(progressBar);
				                    zip.setLabel(labelCenter);
				                    zip.UnZip();
				                    
				                    // Delete the temporary zip
				                    cleanup();
				                    
				                    // Make the play label clickable
				                    labelPlay.setEnabled(true);

				                    // Make the game runnable
				                    runState = true;
				                    
				                    // Hide the load gif
				                    labelLoad.setVisible(false);
				                    // Show the play label
				                    labelPlay.setVisible(true);
				                    // Tell the user that the update is finished
				                    labelCenter.setText("Mise à jour terminée !!");
				                } catch (Exception error) {				
				                    JOptionPane.showMessageDialog(parentJFrame, "Erreur lors de la mise à jour ! Veuillez contacter un administrateur.\nRaison : " + error.getMessage(), "Erreur de mise à jour", JOptionPane.ERROR_MESSAGE);
				                }
			
				            }
				
				        });
		
		// Start the download
		downloader.start();	
	}
	
	/**
	 * Delete the zip file
	 */
	private void cleanup() {
		System.gc();

	 	labelCenter.setText("Nettoyage des fichiers temporaires...");

	  	String path = System.getProperty("user.dir")+"\\update.zip";
		File f = new File(path);
		
		try {
		    if (java.nio.file.Files.deleteIfExists(f.toPath())) {
		    	labelCenter.setText("Fichiers temporaires nettoyés");
		    }
		} catch (Exception error) {
			JOptionPane.showMessageDialog(parentJFrame, "Erreur lors de la suppression des fichiers temporaires ! Veuillez contacter un administrateur.\nRaison : " + error.getMessage(), "Erreur de mise à jour", JOptionPane.ERROR_MESSAGE);
		}
   }

	/**
	 * Download the file situated on the linbk
	 * @param link
	 * 		Path to the file
	 */
	private void downloadFile(String link) {

		try {
			// Get the zip
	        URL url = new URL(link);
	        URLConnection conn = url.openConnection();
	        InputStream is = conn.getInputStream();
	
	        // Size of the zip
	        long max = conn.getContentLength();
	
	        // We show the size to the user
	        labelCenter.setText("Chargement... Taille de la mise à jour : "+max+" octets");
	        
	        // Set the progress bar maximum to the size of the zip
			progressBar.setMaximum((int)max);
	
			// Write the content of zip in the client folder
	        BufferedOutputStream fOut = new BufferedOutputStream(new FileOutputStream(new File("update.zip")));
	        byte[] buffer = new byte[32 * 1024];
	        int bytesRead = 0;
	        int in = 0;
	        while ((bytesRead = is.read(buffer)) != -1) {
	            in += bytesRead;
	            // Update of the progress bar
	            progressBar.setValue(in);	
	            fOut.write(buffer, 0, bytesRead);
	        }
	        fOut.flush();
	        fOut.close();
	        is.close();
		} catch (Exception error) {
			JOptionPane.showMessageDialog(parentJFrame, "Erreur lors du téléchargement des fichiers sur le serveur ! Veuillez contacter un administrateur.\nRaison : " + error.getMessage(), "Erreur de mise à jour", JOptionPane.ERROR_MESSAGE);
		}
		
        labelCenter.setText("Téléchargement terminé !");

        progressBar.setValue(0);
    }
	
	/**
	 * Get the location of the zip which contains files to update
	 * 
	 * @return
	 * 		the location of the zip
	 */
	private String getDownloadLinkFromHost() {
		StringBuilder buffer = null;
		
		// Path to the file which contains the location of the update archive (.zip)
        String path = "http://mu.magestic.eu/Launcher/url.html";
        
        try {
	        // Read the file to get the zip location
	        URL url = new URL(path);
	        InputStream html = null;
	        html = url.openStream();
	        buffer = new StringBuilder("");
	        int c = 0;
	        while(c != -1) {
	            c = html.read();
	            buffer.append((char)c);
	        }
        } catch (Exception error) {
        	JOptionPane.showMessageDialog(parentJFrame, "Erreur lors de la récupération de la localisation des fichiers sur le serveur ! Veuillez contacter un administrateur.\nRaison : " + error.getMessage(), "Erreur de mise à jour", JOptionPane.ERROR_MESSAGE);
        }

        // Return the location of the zip
        return buffer.substring(buffer.indexOf("[url]")+5,buffer.indexOf("[/url]"));

    }	
	
	public static JFrame getMyparent() {
		return parentJFrame;
	}

	public static void setMyparent(JFrame myparent) {
		MMLauncherss.parentJFrame = myparent;
	}
}
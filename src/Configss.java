

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import at.jta.Key;
import at.jta.RegistryErrorException;
import at.jta.Regor;

@SuppressWarnings(value = { "deprecation" })
public class Configss extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroupResolution = new ButtonGroup();
	private final ButtonGroup buttonGroupDisplayMode = new ButtonGroup();
	private JRadioButton radioButtonMusic, radioButtonEffects;
	private Regor reg;
	private JRadioButton radioButtonWindow, radioButtonFullscreen;
	private Key keyMu = new Key();
	private JRadioButton radioButtonRes1600x1200;
	private JRadioButton radioButtonRes1280x960;
	private JRadioButton radioButtonRes1024x768;
	private JRadioButton radioButtonRes800x600;
	private JLabel labelExit;
	private Point mousePosition;
	

	/**
	 * @author Alex GOURBILIERE
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configss frame = new Configss();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Configss() {
		// Localization of registry for Mu
		keyMu.setPath("HKEY_CURRENT_USER\\Software\\Webzen\\Mu\\Config");
		
		/**
		 * Configure of the JFrame itself
		 */
		configureJFrame();
	    
		/**
		* Label for the cross which exit the JFrame
		*/
		createExitCross();
	    
	    /**
	    * Radio buttons for Music and Sound Effects
	    */
	    createSoundComponents();
	    
	    /**
	    *  Label + Radio buttons for Resolution
	    */
	    createResolutionComponents();
	    
		/**
		*  Label + Radio button for way of display
		*/
	   createDisplayModeComponents();
		
	   	/**
	    * Label + Button for saving
	    */
	   	createSaveComponents();	
		
		/**
		 *  Initialize background
		 */
		initializeBackground();
		
		/**
		 *  Initialize selection of options when the JFrame opens
		 */
		initializeSelection();
		
		/**
		 *  Make the JFrame draggable
		 */
		makeJFrameMovable();
	}
	
	private void configureJFrame() {
		setTitle("Configuration");
		setUndecorated(true);
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 271, 314);
		contentPane = new JPanel();
		contentPane.setLayout(null);setLocationRelativeTo(MMLauncherss.getMyparent());
		setContentPane(contentPane);
		setBackground(new Color(0,0,0,0));
	}
	
	/**
	 * Initialize selection of options when the JFrame opens
	 */
	private void initializeSelection() {
		try {
			// Object to work with registry
			// reg.readDword(...) ==> Read the value in registry
			reg = new Regor();
			
			// Check which resolution to button to select
		    String valueResolution  = reg.readDword(keyMu,"Resolution");
		    if (valueResolution.equals("0x1")) {
				radioButtonRes800x600.setSelected(true);
		    } else if (valueResolution.equals("0x2")) {
		    	radioButtonRes1024x768.setSelected(true);
		    } else if (valueResolution.equals("0x3")) {
		    	radioButtonRes1280x960.setSelected(true);
		    } else if (valueResolution.equals("0x4")) {
		    	radioButtonRes1600x1200.setSelected(true);
		    } 
			
			// Check if sounds is selected when the JFrame appears
		    String valueSound  = reg.readDword(keyMu,"SoundOnOff");
			if(valueSound.equals("0x1")) {
				radioButtonEffects.setSelected(true);
			}
			
			// Check if music is selected when the JFrame appears
		    String valueMusic  = reg.readDword(keyMu,"MusicOnOff");
			if(valueMusic.equals("0x1")) {
				radioButtonMusic.setSelected(true);
			}
			
			// Check the value selected for display mode when the JFrame appears
		    String displayMode  = reg.readDword(keyMu,"WindowMode");
		    if(displayMode.equals("0x1")) {	
		    	radioButtonWindow.setSelected(true);	
			} else {
		    	radioButtonFullscreen.setSelected(true);	
		    }			
		} catch (RegistryErrorException error) {
			// Error with registry
			JOptionPane.showMessageDialog(this, "Échec lors de la lecture de la configuration. Veuillez contacter un administrateur.\n Cause : " + error.getMessage(), "Échec de la lecture de la configuration", JOptionPane.ERROR_MESSAGE);	
		}
	}
	
	/**
	 * Initialize the background of the JFrame
	 */
	private void initializeBackground() {
		// Border
		ImageIcon imageBorderBackgound = new ImageIcon(Configss.class.getResource("/marcoo.png"));
		JLabel labelBackgroundBorder = new JLabel("");
		labelBackgroundBorder.setIcon(imageBorderBackgound);
		labelBackgroundBorder.setBounds(0, 0, 265, 268);
		contentPane.add(labelBackgroundBorder);
		
		// Background
		ImageIcon imageBackground = new ImageIcon(Configss.class.getResource("/font.png"));
		JLabel labelBackground = new JLabel("");
		labelBackground.setIcon(imageBackground);
		labelBackground.setBounds(18, 11, 231, 243);
		contentPane.add(labelBackground);
	}
	
	/**
	 * Create the components (JLabels/JButtons...) for Resolution Configuration
	 */
	private void createResolutionComponents() {
		// Global label
		JLabel labelResolution = new JLabel("Résolution:");
		labelResolution.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelResolution.setForeground(Color.WHITE);
		labelResolution.setBounds(20, 14, 95, 14);
		contentPane.add(labelResolution);
		
		// 800x600
		radioButtonRes800x600 = new JRadioButton("800x600");
		radioButtonRes800x600.setForeground(Color.WHITE);
		radioButtonRes800x600.setOpaque(false);
		buttonGroupResolution.add(radioButtonRes800x600);
		radioButtonRes800x600.setBounds(20, 35, 109, 23);
		radioButtonRes800x600.addMouseListener(new MouseAdapter() {
	    	@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.HAND_CURSOR);
	    	}
	    	@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
	    	}
		});
		contentPane.add(radioButtonRes800x600);
		
	    // 1024x768
	    radioButtonRes1024x768 = new JRadioButton("1024x768");
	    radioButtonRes1024x768.setForeground(Color.WHITE);
	    radioButtonRes1024x768.setOpaque(false);
	    buttonGroupResolution.add(radioButtonRes1024x768);
	    radioButtonRes1024x768.setBounds(121, 35, 109, 23);
	    radioButtonRes1024x768.addMouseListener(new MouseAdapter() {
	    	@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.HAND_CURSOR);
	    	}
	    	@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
	    	}
		});
	    contentPane.add(radioButtonRes1024x768);
	    
	    // 1280x960
	    radioButtonRes1280x960 = new JRadioButton("1280x960");
	    radioButtonRes1280x960.setForeground(Color.WHITE);
	    radioButtonRes1280x960.setOpaque(false);
	    buttonGroupResolution.add(radioButtonRes1280x960);
	    radioButtonRes1280x960.setBounds(20, 61, 109, 23);
	    radioButtonRes1280x960.addMouseListener(new MouseAdapter() {
	    	@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.HAND_CURSOR);
	    	}
	    	@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
	    	}
		});
	    contentPane.add(radioButtonRes1280x960);
	    
		//1600x1200
		radioButtonRes1600x1200 = new JRadioButton("1600x1200");
		radioButtonRes1600x1200.setForeground(Color.WHITE);
		radioButtonRes1600x1200.setOpaque(false);
		buttonGroupResolution.add(radioButtonRes1600x1200);
		radioButtonRes1600x1200.setBounds(121, 61, 109, 23);
		radioButtonRes1600x1200.addMouseListener(new MouseAdapter() {
	    	@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.HAND_CURSOR);
	    	}
	    	@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
	    	}
		});
		contentPane.add(radioButtonRes1600x1200);
		
	}
	
	/**
	 * Create the components (JLabels/JButtons...) for Display Mode Configuration
	 */
	private void createDisplayModeComponents() {
		// Global label
		JLabel labelDisplayMode = new JLabel("Mode :");
		labelDisplayMode.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelDisplayMode.setForeground(Color.WHITE);
		labelDisplayMode.setBounds(21, 137, 46, 14);
		contentPane.add(labelDisplayMode);
		
		 // Fullscreen
	    radioButtonFullscreen = new JRadioButton("Plein Écran");
	    buttonGroupDisplayMode.add(radioButtonFullscreen);
	    radioButtonFullscreen.setForeground(Color.WHITE);
	    radioButtonFullscreen.setOpaque(false);
	    radioButtonFullscreen.setBounds(20, 182, 163, 23);
	    radioButtonFullscreen.addMouseListener(new MouseAdapter() {
	    	@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.HAND_CURSOR);
	    	}
	    	@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
	    	}
		});
	    contentPane.add(radioButtonFullscreen);
	    
	    // Windowed
	    radioButtonWindow = new JRadioButton("Mode Fenêtre");
		buttonGroupDisplayMode.add(radioButtonWindow);
		radioButtonWindow.setForeground(Color.WHITE);
		radioButtonWindow.setOpaque(false);
		radioButtonWindow.setBounds(19, 158, 109, 23);
		radioButtonWindow.addMouseListener(new MouseAdapter() {
	    	@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.HAND_CURSOR);
	    	}
	    	@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
	    	}
		});
		contentPane.add(radioButtonWindow);
	}
	
	/**
	 * Create the components (JLabels/JButtons...) for Music and Sound Effects Configuration
	 */
	private void createSoundComponents() {
		// Global label
		JLabel labelSound = new JLabel("Son:");
		labelSound.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelSound.setForeground(Color.WHITE);
		labelSound.setBounds(20, 86, 46, 14);
		contentPane.add(labelSound);
		
		// Sound Effects
	    radioButtonEffects = new JRadioButton("Effets (On/Off) ");
	    radioButtonEffects.setForeground(Color.WHITE);
	    radioButtonEffects.setOpaque(false);
	    radioButtonEffects.setBounds(125, 107, 120, 23);
	    radioButtonEffects.addMouseListener(new MouseAdapter() {
	    	@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.HAND_CURSOR);
	    	}
	    	@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
	    	}
		});
	    contentPane.add(radioButtonEffects);
	    
	    // Music
	    radioButtonMusic = new JRadioButton("Musique (On/Off) ");
		radioButtonMusic.setForeground(Color.WHITE);
		radioButtonMusic.setOpaque(false);
		radioButtonMusic.setBounds(20, 107,115, 23);
		radioButtonMusic.addMouseListener(new MouseAdapter() {
	    	@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.HAND_CURSOR);
	    	}
	    	@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
	    	}
		});
		contentPane.add(radioButtonMusic);
	}
	
	/**
	 * Create the cross which close the JFrame
	 */
	private void createExitCross() {
	    labelExit = new JLabel();
	    labelExit.setIcon(new ImageIcon(MMLauncherss.class.getResource("/close.png")));
	    labelExit.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		
	    		dispose();
	    		
	    	}
	    	@Override
	    	public void mouseEntered(MouseEvent e) {
	    		setCursor(Cursor.HAND_CURSOR);
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		setCursor(Cursor.DEFAULT_CURSOR);
	    	}
	    });
	    labelExit.setForeground(SystemColor.inactiveCaption);
	    labelExit.setFont(new Font("Tahoma", Font.BOLD, 11));
	    labelExit.setHorizontalAlignment(SwingConstants.CENTER);
	    labelExit.setBounds(219, 14, 26, 23);
	    contentPane.add(labelExit);
	}
	
	/**
	 * Create the components for saving
	 */
	private void createSaveComponents() {
		
		// Save button
	    final JButton buttonSave = new JButton("Sauvegarder", new ImageIcon(Configss.class.getResource("/button.png")));
	    buttonSave.setBorder(null);
	    buttonSave.setBounds(82, 213, 120, 32);
	    buttonSave.setOpaque(false);
	    buttonSave.setContentAreaFilled(false);
	    buttonSave.setBorderPainted(false);
	    buttonSave.addMouseListener(new MouseAdapter() {
	    	@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.HAND_CURSOR);
	    	}
	    	@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
	    	}
		});
	    buttonSave.addActionListener(new ActionListener() {	    	
	    	public void actionPerformed(ActionEvent e) {
	    		save();
	    	}
	    });
	    
	    // Save label
	    final JLabel labelSave = new JLabel("Sauvegarder");
	    labelSave.setHorizontalAlignment(SwingConstants.CENTER);
	    labelSave.setForeground(Color.WHITE);
	    labelSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				setCursor(Cursor.HAND_CURSOR);
				labelSave.setForeground(new Color(208, 0, 0));
				PlaySoundss play = new PlaySoundss();
				play.clickSound();
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.DEFAULT_CURSOR);
				labelSave.setForeground(Color.white);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonSave.doClick();
			}
		});
	    labelSave.setBorder(null);
		labelSave.setBounds(83, 216, 106, 23);
		labelSave.setFont(new Font(labelSave.getFont().getFontName(), Font.BOLD, labelSave.getFont().getSize()));
		contentPane.add(labelSave);
	    contentPane.add(buttonSave);
	}
	
	/**
	 * Save the config
	 */
	private void save() {
		try {
			// Object to work with registry
			reg = new Regor();
			
			// String to display errors
			String lMessageReturn;
			
			// Boolean to know if all configs are ok
			boolean isFailed = false;
			
			/**
			 * RESOLUTION
			 */
			lMessageReturn = saveResolution();
			if (lMessageReturn != null) {
				JOptionPane.showMessageDialog(this, lMessageReturn, "Échec de la configuration", JOptionPane.ERROR_MESSAGE);
				isFailed = true;
			}
		
			/**
			 *  SOUND & MUSIC
			 */
			lMessageReturn = saveSound();
			if (lMessageReturn != null) {
				JOptionPane.showMessageDialog(this, lMessageReturn, "Échec de la configuration", JOptionPane.ERROR_MESSAGE);
				isFailed = true;
			}
		
			/**
			 *  DISPLAY MODE
			 */
			lMessageReturn = saveDisplayMode();
			if (lMessageReturn != null) {
				JOptionPane.showMessageDialog(this, lMessageReturn, "Échec de la configuration", JOptionPane.ERROR_MESSAGE);
				isFailed = true;
			}
			
			if (!isFailed) {
				JOptionPane.showMessageDialog(null, "Les changements ont été enregistrés.","Configuration réussie",JOptionPane.INFORMATION_MESSAGE);
			}
			
			dispose();
			
			} catch (RegistryErrorException error) {
				JOptionPane.showMessageDialog(this, "Échec lors de la configuration du jeu. Veuillez contacter un administrateur", "Échec de la configuration", JOptionPane.ERROR_MESSAGE);
			}	
	}

	/**
	 * Save the config for resolution
	 * @return
	 * 		null if no error, a String to describe the error otherwise
	 */
	private String saveResolution() {
		if (radioButtonRes800x600.isSelected()){
			// 800x600		
			try {
	        	new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Configuration\\Resolution_800x600.reg"}).start();
			} catch (IOException error) {
				return "Échec de la mise à jour de la résolution en 800x600.Veuillez contacter un administrateur.\nRaison : " + error.getMessage();
			}
		} else if (radioButtonRes1024x768.isSelected()){
			// 1024x768
			try {
	        	new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Configuration\\Resolution_1024x768.reg"}).start();
				
			} catch (IOException error) {
				return "Échec de la mise à jour de la résolution en 1024x768.Veuillez contacter un administrateur.\nRaison : " + error.getMessage();
			}
		} else if (radioButtonRes1280x960.isSelected()){
			// 1280x960
			try {
	        	new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Configuration\\Resolution_1280x960.reg"}).start();
			} catch (IOException error) {
				return "Échec de la mise à jour de la résolution en 1280x960. Veuillez contacter un administrateur.\nRaison : " + error.getMessage();
			}
		} else if (radioButtonRes1600x1200.isSelected()){
			//1600x1200
			try {
	        	new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Configuration\\Resolution_1600x1200.reg"}).start();
			} catch (IOException error) {
				return "Échec de la mise à jour de la résolution en 1600x1200.Veuillez contacter un administrateur.\nRaison : " + error.getMessage();
			}
		} else {
			return "Échec de la mise à jour de la résolution\nRaison : La résolution ne correspond à aucune valeur attendue.";
		}
		
		return null;
	}
	
	/**
	 * Save the config for display mode
	 * @return
	 * 		null if no error, a String to describe the error otherwise
	 */
	private String saveDisplayMode() {
		if(radioButtonWindow.isSelected()) {
			// Windowed
			try {
	        	new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Configuration\\window_mode.reg"}).start();
			} catch (IOException error) {
				return "Échec de la mise à jour du mode d'affichage en mode fenêtre.Veuillez contacter un administrateur.\nRaison : " + error.getMessage();
			}
			
		} else if(radioButtonFullscreen.isSelected()) {
			// Fullscreen
			try {
				new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Configuration\\fullscreen.reg"}).start();
			} catch (IOException error) {
				return "Échec de la mise à jour du mode d'affichage en mode plein écran.Veuillez contacter un administrateur.\nRaison : " + error.getMessage();
			}
		}
		
		return null;
	}
	
	/**
	 * Save the config for sound
	 * @return
	 * 		null if no error, a String to describe the error otherwise
	 */
	private String saveSound() {
		/**
		 *  Music
		 */
		if(radioButtonMusic.isSelected()) {	
			// Music on
			try {
    			new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Configuration\\Music_On.reg"}).start();
			} catch (IOException error) {
				return "Échec lors de la tentative d'activer la musique en jeu. Veuillez contacter un administrateur.\nRaison : " + error.getMessage();
			}
			
		} else if(!radioButtonMusic.isSelected()) {
			// Music off
			try {
    			new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Configuration\\Music_Off.reg"}).start();
			} catch (IOException error) {
				return "Échec lors de la tentative de désactiver la musique en jeu. Veuillez contacter un administrateur.\nRaison : " + error.getMessage();
			}
		}
		
		/**
		 *  Sound Effects
		 */
		if(radioButtonEffects.isSelected()) {
			// Sound Effects on
			try {
    			new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Configuration\\Effects_On.reg"}).start();
			} catch (IOException error) {
				return "Échec lors de la tentative d'activer les sons en jeu. Veuillez contacter un administrateur.\nRaison : " + error.getMessage();
			}
			
		} else if(!radioButtonEffects.isSelected()) {
			// Sound Effects off
			try {
    			new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Configuration\\Effects_Off.reg"}).start();
			} catch (IOException error) {
				return "Échec lors de la tentative de désactiver les sons en jeu. Veuillez contacter un administrateur.\nRaison : " + error.getMessage();
			}
		}
		
		return null;
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
}




import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import at.jta.Key;
import at.jta.RegistryErrorException;
import at.jta.Regor;

public class Configss extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private JRadioButton radioButtonMusic, radioButtonEffects;
	private Regor reg;
	private JRadioButton radioButtonWindow, radioButtonFullscreen;
	private Key k = new Key();
	private final JRadioButton radioButtonRes1024x768 ,radioButtonRes1280x1024,radioButtonRes800x600, radioButtonRes640x480 ;
	private JLabel labelExit;
	private JSpinner spinner ;
	private Point mouseDownCompCoords;
	

	/**
	 * @author Perez4all
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
		
		setTitle("Configuration");
		setUndecorated(true);
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 271, 314);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		// Adding listeners to the JFrame to make it movable
        mouseDownCompCoords = null;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
	               mouseDownCompCoords = null;
	        }
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }
		});

		this.addMouseMotionListener(new MouseMotionListener(){
            public void mouseMoved(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        });

		setLocationRelativeTo(MMLauncherss.getMyparent());
		setContentPane(contentPane);
		setBackground(new Color(0,0,0,0));
			
		ImageIcon icon7 = new ImageIcon(Configss.class.getResource("marcoo.png"));
				
					// Save button
				    JButton buttonSave = new JButton("Sauvegarder", new ImageIcon(Configss.class.getResource("button.png")));
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
				    // Save label
				    JLabel labelSave = new JLabel("Sauvegarder");
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
				    buttonSave.addActionListener(new ActionListener() {
				    	
				    	
				    	public void actionPerformed(ActionEvent e) {
				    		// TODO Auto-generated method stub
				    		
				    		try {
				    			
				    			//RESOLUTION
				    			
				    			reg = new Regor();
				    	
				    		
			    			if(radioButtonRes640x480.isSelected())//640x480
				    		{	
//					    			reg.saveDword(k, "DisplayDeviceModeIndex", "0");
				    			try {
						        	Process process = new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Resolution\\Resolution_640x480.reg"}).start();
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null,e1);
								}
				    		} else if(radioButtonRes800x600.isSelected()){ //800x600
				    			
//				    			reg.saveDword(k, "DisplayDeviceModeIndex", "0");
				    			try {
						        	Process process = new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Resolution\\Resolution_800x600.reg"}).start();
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null,e1);
								}
				    		}
				    		else if(radioButtonRes1024x768.isSelected()){//1024x768
				    			
//				    			reg.saveDword(k, "DisplayDeviceModeIndex", "1");
				    			try {
						        	Process process = new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Resolution\\Resolution_1024x768.reg"}).start();
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null,e1);
								}
				    			
				    		}
				    		else if(radioButtonRes1280x1024.isSelected()){//1280x1024
				    			
//				    			reg.saveDword(k, "DisplayDeviceModeIndex", "2");
				    			try {
						        	Process process = new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Resolution\\Resolution_1280x1024.reg"}).start();
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null,e1);
								}
				    			
				    			
				    		}
				    		//SOUND
				    		
				    		if(radioButtonMusic.isSelected())//Musica
				    		{	
				    			reg.saveDword(k, "MusicOn", "1");
				    			
				    		}
				    		else if(!radioButtonMusic.isSelected())
				    		{
				    			reg.saveDword(k, "MusicOn", "0");
				    			
				    		}
				    		if(radioButtonEffects.isSelected())//Efectos
				    		{
	
				    			reg.saveDword(k, "SoundOn", "1");
				    			
				    		}
				    		else if(!radioButtonEffects.isSelected())
				    		{
				    			
				    			reg.saveDword(k, "SoundOn", "0");
				    			
				    			
				    		}
				    		
				    		//VISUALIZACION
				    		
				    		if(radioButtonWindow.isSelected())
				    		{
//				    			reg.saveDword(k, "FullScreenMode", "0");
				    			try {
						        	Process process = new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Resolution\\window_mode.reg"}).start();
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null,e1);
								}
				    			
				    		}
				    		else if(radioButtonFullscreen.isSelected()){
				    			
//				    			reg.saveDword(k, "FullScreenMode", "1");
				    			try {
						        	Process process = new ProcessBuilder(new String[] {"regedit.exe", "/s", System.getProperty("user.dir")+"\\Resolution\\fullscreen.reg"}).start();
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null,e1);
								}
				    			
				    		}
				    		
				    	
				    		reg.saveDword(k, "VolumeLevel", spinner.getValue().toString());
				    		
				    		JOptionPane.showMessageDialog(null, "Les changements ont été enregistrés.","Correct",JOptionPane.INFORMATION_MESSAGE);
				    		
				    		dispose();
				    		
				    		} catch (RegistryErrorException e1) {
				    			// TODO Auto-generated catch block
				    			e1.printStackTrace();
				    		}	
				    		
				    		
				    	}
				    });
				    
					contentPane.setBackground(new Color(0,0,0,0));;
				    
					/**
					 * Label for the cross which exit the JFrame
					 */
				    labelExit = new JLabel();
				    labelExit.setIcon(new ImageIcon(MMLauncherss.class.getResource("close.png")));
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
				    
				    /**
				     * Radio button for Effects
				     */
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

				    
				    /**
				     *  Label + Radio buttons for Resolution
				     */
					JLabel labelResolution = new JLabel("Résolution:");
					labelResolution.setFont(new Font("Tahoma", Font.BOLD, 11));
					labelResolution.setForeground(Color.WHITE);
					labelResolution.setBounds(20, 14, 95, 14);
					contentPane.add(labelResolution);
					//640x480
					radioButtonRes640x480 = new JRadioButton("640x480");
					radioButtonRes640x480.setForeground(Color.WHITE);
					radioButtonRes640x480.setOpaque(false);
					buttonGroup.add(radioButtonRes640x480);
					radioButtonRes640x480.setBounds(20, 35, 109, 23);
					radioButtonRes640x480.addMouseListener(new MouseAdapter() {
				    	@Override
						public void mouseEntered(MouseEvent e) {
							setCursor(Cursor.HAND_CURSOR);
				    	}
				    	@Override
						public void mouseExited(MouseEvent e) {
							setCursor(Cursor.DEFAULT_CURSOR);
				    	}
					});
					contentPane.add(radioButtonRes640x480);
					// 800x600
					radioButtonRes800x600 = new JRadioButton("800x600");
					radioButtonRes800x600.setForeground(Color.WHITE);
					radioButtonRes800x600.setOpaque(false);
					buttonGroup.add(radioButtonRes800x600);
					radioButtonRes800x600.setBounds(121, 35, 109, 23);
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
				    buttonGroup.add(radioButtonRes1024x768);
				    radioButtonRes1024x768.setBounds(20, 61, 109, 23);
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
				    // 1280x1024
				    radioButtonRes1280x1024 = new JRadioButton("1280x1024");
				    radioButtonRes1280x1024.setForeground(Color.WHITE);
				    radioButtonRes1280x1024.setOpaque(false);
				    buttonGroup.add(radioButtonRes1280x1024);
				    radioButtonRes1280x1024.setBounds(121, 61, 109, 23);
				    radioButtonRes1280x1024.addMouseListener(new MouseAdapter() {
				    	@Override
						public void mouseEntered(MouseEvent e) {
							setCursor(Cursor.HAND_CURSOR);
				    	}
				    	@Override
						public void mouseExited(MouseEvent e) {
							setCursor(Cursor.DEFAULT_CURSOR);
				    	}
					});
				    contentPane.add(radioButtonRes1280x1024);
				    contentPane.add(buttonSave);
		
				    
					/**
					 *  Label + Radio button for way of display
					 */
				    // Fullscreen
				    radioButtonFullscreen = new JRadioButton("Plein Écran");
				    buttonGroup_3.add(radioButtonFullscreen);
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
					buttonGroup_3.add(radioButtonWindow);
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
		
		JLabel labelDisplayMode = new JLabel("Mode :");
		labelDisplayMode.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelDisplayMode.setForeground(Color.WHITE);
		labelDisplayMode.setBounds(21, 137, 46, 14);
		contentPane.add(labelDisplayMode);
		
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
		
	
		
		JLabel labelSound = new JLabel("Son:");
		labelSound.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelSound.setForeground(Color.WHITE);
		labelSound.setBounds(20, 86, 46, 14);
		contentPane.add(labelSound);
		
		JLabel labelVolume = new JLabel("Volume:");
		labelVolume.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelVolume.setForeground(Color.WHITE);
		labelVolume.setBounds(128, 137, 79, 14);
		contentPane.add(labelVolume);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		spinner.setBounds(197, 134, 38, 20);
		contentPane.add(spinner);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(icon7);
		lblNewLabel_1.setBounds(0, 0, 265, 268);
		contentPane.add(lblNewLabel_1);
		
		
		k.setPath("HKEY_CURRENT_USER\\Software\\Webzen\\Mu\\Config");
		
		try {
	
			reg = new Regor();//con regor leo registro
			
		    String value  = reg.readDword(k,"DisplayDeviceModeIndex");//leo la dword tipo de dato
		
			switch(value)
			{
			case "0x0":
			{
				
				radioButtonRes800x600.setSelected(true);
				
			}
			case "0x1":
			{
				radioButtonRes1024x768.setSelected(true);
				
				
			}break;
			case "0x2":
			{
				
				radioButtonRes1280x1024.setSelected(true);
				
				
			}break;
		
			
			
			}
			
			
			reg = new Regor();
		    String volume = reg.readDword(k,"VolumeLevel");
			switch(volume)
			{
			case "0x1":
			{
				
				spinner.setValue(1);
				
				
			}break;
			case "0x2":
			{
				
				spinner.setValue(2);
				
				
			}break;
			case "0x3":
			{
				
				spinner.setValue(3);
				
				
			}break;
			case "0x4":
			{
				
				spinner.setValue(4);
				
				
			}break;
			case "0x5":
			{
				
				spinner.setValue(5);
				
				
			}break;
			case "0x6":
			{
				
				spinner.setValue(6);
				
				
			}break;
			case "0x7":
			{
				
				spinner.setValue(7);
				
				
			}break;
			case "0x8":
			{
				
				spinner.setValue(8);
				
				
			}break;
			case "0x9":
			{
				
				spinner.setValue(9);
				
				
			}break;
			
				
			}
			
			
			reg = new Regor();
		    String sound  = reg.readDword(k,"SoundOn");
	
		    
			if(sound.equals("0x1"))
			{
				
				radioButtonEffects.setSelected(true);
				
				
			}
			reg = new Regor();
		    String music  = reg.readDword(k,"MusicOn");

			if(music.equals("0x1"))
			{	
				
				radioButtonMusic.setSelected(true);
				
				
			}
			
			reg = new Regor();
		    String ventana  = reg.readDword(k,"FullScreenMode");

		    if(ventana.equals("0x0"))
			{
				
		    	radioButtonWindow.setSelected(true);
				
				
			}
		    else
		    {
		    	radioButtonFullscreen.setSelected(true);	
		    	
		    }
		    
		    
		    
			
		} catch (RegistryErrorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		ImageIcon icon = new ImageIcon(Configss.class.getResource("font.png"));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(18, 11, 231, 243);
		contentPane.add(lblNewLabel);
		
	}
}




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
	private final JRadioButton radioButtonRes1024x768 ,radioButtonRes1280x720,radioButtonRes800x600 ;
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
				    
				    
				    
				    
				    JButton btnNewButton = new JButton("Sauvegarder");
				    btnNewButton.setBorder(null);
				    btnNewButton.setBounds(76, 213, 118, 28);
				    btnNewButton.addMouseListener(new MouseAdapter() {
				    	@Override
						public void mouseEntered(MouseEvent e) {
							setCursor(Cursor.HAND_CURSOR);
				    	}
				    	@Override
						public void mouseExited(MouseEvent e) {
							setCursor(Cursor.DEFAULT_CURSOR);
				    	}
					});
				    btnNewButton.addActionListener(new ActionListener() {
				    	
				    	
				    	public void actionPerformed(ActionEvent e) {
				    		// TODO Auto-generated method stub
				    		
				    		try {
				    			
				    			//RESOLUTION
				    			
				    			reg = new Regor();
				    	
				    		
				    		if(radioButtonRes800x600.isSelected())//800x600
				    		{	
				    			reg.saveDword(k, "DisplayDeviceModeIndex", "0");
				    			
				    		}
				    		else if(radioButtonRes1024x768.isSelected()){//1024x768
				    			
				    			
				    			reg.saveDword(k, "DisplayDeviceModeIndex", "1");
				    			
				    		}
				    		else if(radioButtonRes1280x720.isSelected()){//1280x720
				    			
				    			reg.saveDword(k, "DisplayDeviceModeIndex", "2");
				    			
				    			
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
				    			reg.saveDword(k, "FullScreenMode", "0");
				    			
				    		}
				    		else if(radioButtonFullscreen.isSelected()){
				    			
				    			reg.saveDword(k, "FullScreenMode", "1");
				    			
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
				    
				    labelExit = new JLabel("X");
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
				    
				    radioButtonRes1280x720 = new JRadioButton("1280x720");
				    radioButtonRes1280x720.setForeground(Color.WHITE);
				    radioButtonRes1280x720.setOpaque(false);
				    buttonGroup.add(radioButtonRes1280x720);
				    radioButtonRes1280x720.setBounds(20, 61, 109, 23);
				    radioButtonRes1280x720.addMouseListener(new MouseAdapter() {
				    	@Override
						public void mouseEntered(MouseEvent e) {
							setCursor(Cursor.HAND_CURSOR);
				    	}
				    	@Override
						public void mouseExited(MouseEvent e) {
							setCursor(Cursor.DEFAULT_CURSOR);
				    	}
					});
				    contentPane.add(radioButtonRes1280x720);
				    
				    radioButtonRes1024x768 = new JRadioButton("1024x768");
				    radioButtonRes1024x768.setForeground(Color.WHITE);
				    radioButtonRes1024x768.setOpaque(false);
				    buttonGroup.add(radioButtonRes1024x768);
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
				    contentPane.add(btnNewButton);
		
					
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
		
		JLabel lblIniciar = new JLabel("Mode :");
		lblIniciar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIniciar.setForeground(Color.WHITE);
		lblIniciar.setBounds(21, 137, 46, 14);
		contentPane.add(lblIniciar);
		
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
		
	
		
		JLabel lblSonido = new JLabel("Son:");
		lblSonido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSonido.setForeground(Color.WHITE);
		lblSonido.setBounds(20, 86, 46, 14);
		contentPane.add(lblSonido);
		
		JLabel lblResolucion = new JLabel("Résolution:");
		lblResolucion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResolucion.setForeground(Color.WHITE);
		lblResolucion.setBounds(20, 14, 95, 14);
		contentPane.add(lblResolucion);
		
		
		radioButtonRes800x600 = new JRadioButton("800x600");
		radioButtonRes800x600.setForeground(Color.WHITE);
		radioButtonRes800x600.setOpaque(false);
		buttonGroup.add(radioButtonRes800x600);
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
		
		JLabel lblVolumen = new JLabel("Volume:");
		lblVolumen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVolumen.setForeground(Color.WHITE);
		lblVolumen.setBounds(128, 137, 79, 14);
		contentPane.add(lblVolumen);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		spinner.setBounds(197, 134, 38, 20);
		contentPane.add(spinner);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(icon7);
		lblNewLabel_1.setBounds(0, 0, 265, 268);
		contentPane.add(lblNewLabel_1);
		
		
		k.setPath("HKEY_CURRENT_USER\\Software\\Webzen\\Mu_Blue\\Config");
		
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
				
				radioButtonRes1280x720.setSelected(true);
				
				
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


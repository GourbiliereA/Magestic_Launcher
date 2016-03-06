

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JRadioButton rdbtnSi, rdbtnNo;
	private Regor reg;
	private JRadioButton rdbtnMinimizado, rdbtnPantallaCompleta;
	private Key k = new Key();
	private final JRadioButton rdbtnx ,rdbtnx_1,rdbtnNewRadioButton ;
	private JLabel lblNewLabel_2;
	private JSpinner spinner ;
	
	

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

		setLocationRelativeTo(MMLauncherss.getMyparent());
		setContentPane(contentPane);
		setBackground(new Color(0,0,0,0));
			
		ImageIcon icon7 = new ImageIcon("Data/Update/marcoo.png");
				    
				    
				    
				    
				    JButton btnNewButton = new JButton("Sauvegarder");
				    btnNewButton.setBorder(null);
				    btnNewButton.setBounds(76, 213, 118, 28);
				    btnNewButton.addActionListener(new ActionListener() {
				    	
				    	
				    	public void actionPerformed(ActionEvent e) {
				    		// TODO Auto-generated method stub
				    		
				    		try {
				    			
				    			//RESOLUTION
				    			
				    			reg = new Regor();
				    	
				    		
				    		if(rdbtnNewRadioButton.isSelected())//800x600
				    		{	
				    			reg.saveDword(k, "DisplayDeviceModeIndex", "0");
				    			
				    		}
				    		else if(rdbtnx.isSelected()){//1024x768
				    			
				    			
				    			reg.saveDword(k, "DisplayDeviceModeIndex", "1");
				    			
				    		}
				    		else if(rdbtnx_1.isSelected()){//1280x720
				    			
				    			reg.saveDword(k, "DisplayDeviceModeIndex", "2");
				    			
				    			
				    		}
				    		//SOUND
				    		
				    		if(rdbtnSi.isSelected())//Musica
				    		{	
				    			reg.saveDword(k, "MusicOn", "1");
				    			
				    		}
				    		else if(!rdbtnSi.isSelected())
				    		{
				    			reg.saveDword(k, "MusicOn", "0");
				    			
				    		}
				    		if(rdbtnNo.isSelected())//Efectos
				    		{
	
				    			reg.saveDword(k, "SoundOn", "1");
				    			
				    		}
				    		else if(!rdbtnNo.isSelected())
				    		{
				    			
				    			reg.saveDword(k, "SoundOn", "0");
				    			
				    			
				    		}
				    		
				    		//VISUALIZACION
				    		
				    		if(rdbtnMinimizado.isSelected())
				    		{
				    			reg.saveDword(k, "FullScreenMode", "0");
				    			
				    		}
				    		else if(rdbtnPantallaCompleta.isSelected()){
				    			
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
				    
				    lblNewLabel_2 = new JLabel("X");
				    lblNewLabel_2.addMouseListener(new MouseAdapter() {
				    	@Override
				    	public void mouseClicked(MouseEvent arg0) {
				    		
				    		dispose();
				    		
				    	}
				    });
				    lblNewLabel_2.setForeground(SystemColor.inactiveCaption);
				    lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
				    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				    lblNewLabel_2.setBounds(219, 14, 26, 23);
				    contentPane.add(lblNewLabel_2);
				    
				    rdbtnNo = new JRadioButton("Effets (On/Off) ");
				    rdbtnNo.setForeground(Color.WHITE);
				    rdbtnNo.setOpaque(false);
				    rdbtnNo.setBounds(125, 107, 120, 23);
				    contentPane.add(rdbtnNo);
				    
				    rdbtnx_1 = new JRadioButton("1280x720");
				    rdbtnx_1.setForeground(Color.WHITE);
				    rdbtnx_1.setOpaque(false);
				    buttonGroup.add(rdbtnx_1);
				    rdbtnx_1.setBounds(20, 61, 109, 23);
				    contentPane.add(rdbtnx_1);
				    
				    rdbtnx = new JRadioButton("1024x768");
				    rdbtnx.setForeground(Color.WHITE);
				    rdbtnx.setOpaque(false);
				    buttonGroup.add(rdbtnx);
				    rdbtnx.setBounds(121, 35, 109, 23);
				    contentPane.add(rdbtnx);
				    contentPane.add(btnNewButton);
		
					
				    rdbtnPantallaCompleta = new JRadioButton("Plein Écran");
				    buttonGroup_3.add(rdbtnPantallaCompleta);
				    rdbtnPantallaCompleta.setForeground(Color.WHITE);
				    rdbtnPantallaCompleta.setOpaque(false);
				    rdbtnPantallaCompleta.setBounds(20, 182, 163, 23);
				    contentPane.add(rdbtnPantallaCompleta);
		
		
		
		rdbtnMinimizado = new JRadioButton("Mode Fenêtre");
		buttonGroup_3.add(rdbtnMinimizado);
		rdbtnMinimizado.setForeground(Color.WHITE);
		rdbtnMinimizado.setOpaque(false);
		rdbtnMinimizado.setBounds(19, 158, 109, 23);
		contentPane.add(rdbtnMinimizado);
		
		JLabel lblIniciar = new JLabel("Mode :");
		lblIniciar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIniciar.setForeground(Color.WHITE);
		lblIniciar.setBounds(21, 137, 46, 14);
		contentPane.add(lblIniciar);
		
		rdbtnSi = new JRadioButton("Musique (On/Off) ");
		rdbtnSi.setForeground(Color.WHITE);
		rdbtnSi.setOpaque(false);
		rdbtnSi.setBounds(20, 107,115, 23);
		contentPane.add(rdbtnSi);
		
	
		
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
		
		
		rdbtnNewRadioButton = new JRadioButton("800x600");
		rdbtnNewRadioButton.setForeground(Color.WHITE);
		rdbtnNewRadioButton.setOpaque(false);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(20, 35, 109, 23);
		contentPane.add(rdbtnNewRadioButton);
		
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
				
				rdbtnNewRadioButton.setSelected(true);
				
			}
			case "0x1":
			{
				rdbtnx.setSelected(true);
				
				
			}break;
			case "0x2":
			{
				
				rdbtnx_1.setSelected(true);
				
				
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
				
				rdbtnNo.setSelected(true);
				
				
			}
			reg = new Regor();
		    String music  = reg.readDword(k,"MusicOn");

			if(music.equals("0x1"))
			{	
				
				rdbtnSi.setSelected(true);
				
				
			}
			
			reg = new Regor();
		    String ventana  = reg.readDword(k,"FullScreenMode");

		    if(ventana.equals("0x0"))
			{
				
		    	rdbtnMinimizado.setSelected(true);
				
				
			}
		    else
		    {
		    	rdbtnPantallaCompleta.setSelected(true);	
		    	
		    }
		    
		    
		    
			
		} catch (RegistryErrorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		ImageIcon icon = new ImageIcon("Data/Update/wall.jpg");
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(18, 11, 231, 243);
		contentPane.add(lblNewLabel);
		
	}
}


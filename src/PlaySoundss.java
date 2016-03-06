


import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlaySoundss {

	Clip sonido;
	/**
	 * 
	 * @author Perez4all
	 * Constructor
	 * 
	 * */
	public PlaySoundss()
	{

	}

	private void play(URL file)
	{
		try{
			sonido = AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(file));
			sonido.start();
			
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Play .wav sound

	}

	public void clickSound()
	{

		//URL defaultSound = getClass().getResource("src/click.wav");
		//play(defaultSound);
		
		
	}

}

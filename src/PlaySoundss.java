


import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * @author Alex GOURBILIERE
 * 
 * Class not used 
 * 
 * */
public class PlaySoundss {

	Clip sound;
	
	public PlaySoundss()
	{

	}

	private void play(URL file)
	{
		try{
			sound = AudioSystem.getClip();
			sound.open(AudioSystem.getAudioInputStream(file));
			sound.start();
			
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

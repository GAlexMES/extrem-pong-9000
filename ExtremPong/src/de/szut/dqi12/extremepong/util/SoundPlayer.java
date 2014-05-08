package de.szut.dqi12.extremepong.util;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * @author Thomas Darimont
 * tutorials.de (src)
 */
public class SoundPlayer {
	public static void main(String[] args) {
		try{
	         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:/WINNT/Media/Windows-Anmeldeklang.wav"));
	         AudioFormat af = audioInputStream.getFormat();
	         int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
	         byte[] audio = new byte[size];
	         DataLine.Info info = new DataLine.Info(Clip.class, af, size);
	         audioInputStream.read(audio, 0, size);
	         
      
             Clip clip = (Clip) AudioSystem.getLine(info);
             clip.open(af, audio, 0, size);
             clip.start();

	     }catch(Exception e){
	    	 e.printStackTrace();
	     }
	}
}
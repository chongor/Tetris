import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class PlayAudio {
	// some of these files were for testing purposes
  Clip applause;       // Applause
  Clip bomb;           // Opening
  Clip explosion;      // Opening stop
  Clip land;      	   // block dropped to bottom
  Clip hit1;           // Choose Level increment
  Clip hit2;           // Choose Level decrement
  Clip laser;          // New piece created
  Clip bump;     	   // Welcome page to Game page, and GO clicked
  Clip raygun;         // STOP clicked
  Clip aah;            // followed by bomb after QUIT clicked
  Clip noise;          // Help
  Clip wolfwhistle;    // Opening Whistle
  Clip lose;		   // Sound when you lose
  
	
  public PlayAudio() {
	  loadAll();
  }
	
  public Clip loadAudio(String filename) {
	if (filename == null)
		return null;
	
	try {
		// from a wave File
	 	File soundFile = new File(filename);
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
		
		Clip clip = AudioSystem.getClip();
		clip.open(audioIn);
		return clip;
	} catch (UnsupportedAudioFileException e) {
		return null;
	} catch (IOException ioe) {
		return null;
	} catch (LineUnavailableException le) {
		return null;
	}
  }

  public void audioIncrement() {
	  Clip sound = loadAudio("hit1.wav");
	  sound.start();
  }
  
  public void audioDecrement() {
	  Clip sound = loadAudio("hit2.wav");
	  sound.start();
  }
  
  public void loadAll() {
	  applause = loadAudio("applause.wav");
	  bomb = loadAudio("bomb.wav");
	  wolfwhistle = loadAudio("wolfwhistle.wav");
	  explosion = loadAudio("explosion.wav");
	  land = loadAudio("land.wav");
	  hit1 = loadAudio("hit1.wav");
	  hit2 = loadAudio("hit2.wav");
	  
	  laser = loadAudio("laser.wav");
	  bump = loadAudio("bump.wav");
	  raygun = loadAudio("raygun.wav");
	  aah = loadAudio("aah.wav");
	  noise = loadAudio("noise.wav");
	  lose = loadAudio("lose.wav");
  }
  
  public void playClip(String clipname) {
	  
	  if (clipname == "Stop") {
		  raygun = loadAudio("raygun.wav");
		  raygun.start();
	  }
	  else if (clipname == "Increment") {
		  hit1 = loadAudio("hit1.wav");
		  hit1.start();
      }
	  else if (clipname == "Decrement") {
		  hit2 = loadAudio("hit2.wav");
		  hit2.start();
	  }  
	  else if (clipname == "Newpiece") {
		  laser = loadAudio("laser.wav");
		  laser.start();
      }  
	  else if (clipname == "Dropped") {
		  land = loadAudio("land.wav");
		  land.start();
      }  
	  else if (clipname == "Go") {
		  bump = loadAudio("bump.wav");
		  bump.start();
      }  
	  else if (clipname == "Quit") {
		  aah = loadAudio("aah.wav");
		  aah.start();
      }  
	  else if (clipname == "Help") {
		  noise = loadAudio("noise.wav");
		  noise.start();
	  }
	  else if (clipname == "Lose"){
		  lose = loadAudio("lose.wav");
		  lose.start();
	  }
  }
  
  public void playClip(Clip clip, int num) {
	  clip.loop(num);
  }
  
  public void playClip(Clip clip) {
      // start()
	  clip.start();  // play once
	  // Loop()
	  //clip.loop(0);  // repeat none (play once), can be used in place of start().
	  //clip.loop(5);  // repeat 5 times (play 6 times)
	  // clip.loop(Clip.LOOP_CONTINUOUSLY);  // repeat forever
  }
  
  public void stopClip(String clipname) {
	  if (clipname == "Opening") {
		  stopClip(bomb);
	  }
	  if (clipname == "Stop")
		  stopClip(raygun);
	  else if (clipname == "Incement")
		  stopClip(hit1);
	  else if (clipname == "Decrement")
		  stopClip(hit2);
	  else if (clipname == "Newpiece")
		  stopClip(laser);
	  else if (clipname == "Go")
		  stopClip(bump);
	  else if (clipname == "Quit")
		  stopClip(aah);
	  else if (clipname == "Help")
		  stopClip("noise");
	  else if (clipname == "Lose")
		  stopClip("lose");
  }
  
  public void stopClip(Clip clip) {
	  if (clip.isRunning()) clip.stop();
  }
}
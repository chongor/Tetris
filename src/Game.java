import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javax.sound.midi.*;

public class Game implements Runnable {
	int level = 1;
    JFrame frame;
    Welcome welcome;
    PlayAudio audio;
    House house;
    Instruction instruction;
    Sequencer musicb;    
    MidiPlayer midi;
    
	public void run() {
	   audio = new PlayAudio();
	   midi = new MidiPlayer();
	   musicb = midi.loadMidi("Tetris_MusicB.mid");
	   
	   midi.playMidi(musicb);
	   
	   frame = new JFrame();
	   welcome = new Welcome(this, audio);
	   house = new House(this, audio);
	 
	   frame.add(welcome);
	   welcome.setVisible(true);
	   instruction = new Instruction(this, audio);

	   frame.setSize(600, 600);
       frame.setTitle("Tetris Game");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
   }
	
   public void startInstruction() {
	   welcome.setVisible(false);
	   frame.add(instruction);
	   instruction.setVisible(true);
   }
   
   public void startGame() {
	   instruction.setVisible(false);
	   midi.stopMidi(musicb);
	   frame.add(house);
	   house.setVisible(true);
	   house.getMidi(midi);
   }

   /*
    * Get the game started!
    */
   public static void main(String[] args) {
       SwingUtilities.invokeLater(new Game());
   }
}

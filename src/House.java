import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

import java.awt.*;
import javax.sound.midi.*;

public class House extends JPanel {
	final Game game;
	final PlayAudio audio;
    Board board;
    Tool toolPanel;
    
	public House(Game parent, PlayAudio sound){
		game = parent;
		audio = sound;
		setPreferredSize(new Dimension(600, 600));
		
		setLayout(new GridLayout(1, 2));
		
		board = new Board(this, audio);
		add(board);

		toolPanel = new Tool(this, audio);
		add(toolPanel);
		
		board.setVisible(true);
		setVisible(true);
	}
	
	public void updateScore(int numLinesRemoved, int score, int totalScore,
			int oneLineRemoved, int twoLinesRemoved, int threeLinesRemoved,
			int fourLinesRemoved) {
		
        toolPanel.updateScore(numLinesRemoved, score, totalScore,
        		oneLineRemoved, twoLinesRemoved, threeLinesRemoved,
        		fourLinesRemoved);
	}
	
	public void updateScoreLevel(int numLinesRemoved, int score, int level,
			int totalScore, int oneLineRemoved, int twoLinesRemoved,
			int threeLinesRemoved, int fourLinesRemoved) {
		
        toolPanel.updateScoreLevel(numLinesRemoved, score, level, totalScore,
        		oneLineRemoved, twoLinesRemoved, threeLinesRemoved,
        		fourLinesRemoved);
	}
	
	public void updateLevel(int lev) {
	   board.setLevel(lev);
	}
	
	public void updateStatus(String s) {
		toolPanel.updateStatus(s);
	}
	
	public void stop() {
		board.stop();
	}
	
    public void getMidi(MidiPlayer midi) {
        board.getMidi(midi);
    }
	
	public void go() {
		if (!board.isStarted)
			board.start();
		else if (board.isPaused) {
		    board.pause();
		}
	}
	
	public void updateShape(Tetrominoes shape) {
		toolPanel.updateShape(shape);
	}
}




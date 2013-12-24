import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

import java.awt.*;

public class Tool extends JPanel {
	final PlayAudio audio;
    final House house;
    ShapePanel shapePanel;
    ScorePanel scorePanel;
    StopGo stopGoPanel;
    ChoiceLevel choiceLevel;
    Manual manual;
    
	public Tool(House parent, PlayAudio sound){
		house = parent;
		audio = sound;
		setPreferredSize(new Dimension(285, 600));
		
		shapePanel = new ShapePanel();
		add(shapePanel);
		scorePanel = new ScorePanel();
		add(scorePanel);
		stopGoPanel = new StopGo(this, audio);
		add(stopGoPanel);
		choiceLevel = new ChoiceLevel(this,audio);
		add(choiceLevel);
		
		manual = new Manual(this);
		add(manual);
		
		manual.setVisible(false);
		shapePanel.setVisible(true);
		scorePanel.setVisible(true);
		stopGoPanel.setVisible(true);
		choiceLevel.setVisible(true);
		
	}
	
	public void setLevel(int level) {
		scorePanel.setLevel(level);
		house.updateLevel(level);
	}
	
	public void updateScore(int numLinesRemoved, int score, int totalScore,
			int oneLineRemoved, int twoLinesRemoved, int threeLinesRemoved,
			int fourLinesRemoved) {
		
        scorePanel.updateScore(numLinesRemoved, score, totalScore,
        		oneLineRemoved, twoLinesRemoved, threeLinesRemoved,
        		fourLinesRemoved);
	}
	
	public void updateScoreLevel(int numLinesRemoved, int score, int lev,
			int totalScore, int oneLineRemoved, int twoLinesRemoved,
			int threeLinesRemoved, int fourLinesRemoved) {
		
        scorePanel.updateScoreLevel(numLinesRemoved, score, lev,
        		totalScore, oneLineRemoved, twoLinesRemoved,
        		threeLinesRemoved, fourLinesRemoved);
        
        choiceLevel.bumpLevel(lev);
	}
	
	public void updateShape(Tetrominoes shape) {
	   	shapePanel.updateShape(shape);
    	shapePanel.repaint();
 	}
	
	public void stop() {
		house.stop();
	}
	
	public void go() {
		house.go();
	}
	
	public void help() {
        manual.setVisible(true);
        
		shapePanel.setVisible(false);
		scorePanel.setVisible(false);
		stopGoPanel.setVisible(false);
		choiceLevel.setVisible(false);
	}

	public void updateStatus(String s) {
		choiceLevel.updateStatus(s);
	}
	
	public void exitHelp() {
		manual.setVisible(false);

		shapePanel.setVisible(true);
		scorePanel.setVisible(true);
		stopGoPanel.setVisible(true);
		choiceLevel.setVisible(true);
	}
	public class ShapePanel extends JPanel {

		private int squareWidth = 24;
		private int squareHeight = 24;
		private int pointX = 60;
		private int pointY = 30;
		
		private Tetrominoes s = Tetrominoes.NoShape;
		
		public ShapePanel(){
			this.setBorder(BorderFactory.createLineBorder(Color.blue));
	    }
		
		@Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        
	        drawShape(g);
	    }

	    public void updateShape(Tetrominoes shape) {
	    	s = shape;
	    }
	    
	    private void drawShape(Graphics g)
	    {
	        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), 
	            new Color(102, 204, 102), new Color(102, 102, 204), 
	            new Color(204, 204, 102), new Color(204, 102, 204), 
	            new Color(102, 204, 204), new Color(218, 170, 0)
	        };

	        Color color = colors[s.ordinal()];
	        
	        int shapeTable[][][] = { 
	        		{{0, 0, 0, 0}, {0, 0, 0, 0}}, 
	        		{{0, 1, 1, 0}, {1, 1, 0, 0}},
	        		{{1, 1, 0, 0}, {0, 1, 1, 0}},
	        		{{1, 1, 1, 1}, {0, 0, 0, 0}},
	        		{{0, 1, 0, 0}, {1, 1, 1, 0}},
	        		{{0, 1, 1, 0}, {0, 1, 1, 0}},
	        		{{1, 0, 0, 0}, {1, 1, 1, 0}},
	        		{{0, 0, 1, 0}, {1, 1, 1, 0}}
	        };
	        
	        int sdata[][] = shapeTable[s.ordinal()];
	        
	        for (int i = 0; i < 2; i++) {
	        	for (int j = 0; j < 4; j++) {
	        		
	        		int x = pointX + j * squareWidth;
	        		int y = pointY + i * squareHeight;
	        		
	        		if (sdata[i][j] != 0) {
	        			drawSquare(g, x, y, color);
	        		}
	        	}
	        }
	    }
	    
	    private void drawSquare(Graphics g, int x, int y, Color color)
	    {
	        g.setColor(color);
	        g.fillRect(x + 1, y + 1, squareWidth - 2, squareHeight - 2);

	        g.setColor(color.brighter());
	        g.drawLine(x, y + squareHeight - 1, x, y);
	        g.drawLine(x, y, x + squareWidth - 1, y);

	        g.setColor(color.darker());
	        g.drawLine(x + 1, y + squareHeight - 1,
	                         x + squareWidth - 1, y + squareHeight - 1);
	        g.drawLine(x + squareWidth - 1, y + squareHeight - 1,
	                         x + squareWidth - 1, y + 1);

	    }
	    @Override
	 	public Dimension getPreferredSize() {
	 		return new Dimension(200, 100);
	    }
	}
	
	public class ScorePanel extends JPanel {
		JLabel levelLabel;
		JLabel lineLabel;
		JLabel scoreLabel;
		
		int level = 1;
		int score = 0;
		int totalScore = 0;
		
		int linesRemoved = 0;
		int oneLineRemoved = 0;
		int twoLinesRemoved = 0;
		int threeLinesRemoved = 0;
		int fourLinesRemoved = 0;
		
		public ScorePanel() {
			this.setBorder(BorderFactory.createLineBorder(Color.blue));
			this.setBackground(Color.lightGray);
		}

		public void drawPanel(Graphics g) {
			
			g.setFont(new Font("Arial", Font.BOLD, 13));
			
			String str1 = "Level: " + level;
			String str2 = "Lines Removed: " + linesRemoved;
			String str3 = "Score: " + score;
			String str4 = "Total Score: " + totalScore;
			
			g.drawString(str1, 5, 20);
			g.drawString(str2, 5, 50);
			g.drawString(str3, 5, 80);
			g.drawString(str4, 5, 110);
			
			String str5 = "1 Line Removed: " + oneLineRemoved;
			String str6 = "2 Lines Removed: " + twoLinesRemoved;
			String str7 = "3 Lines Removed: " + threeLinesRemoved;
			String str8 = "4 Lines Removed: " + fourLinesRemoved;
			
			g.drawString(str5, 150, 20);
			g.drawString(str6, 150, 50);
			g.drawString(str7, 150, 80);
			g.drawString(str8, 150, 110);
		}
		
		public void updateScore(int linesRemoved1, int score1, int totalScore1,
				int oneLineRemoved1, int twoLinesRemoved1, int threeLinesRemoved1,
				int fourLinesRemoved1) {
			
            linesRemoved = linesRemoved1;
			score = score1;
			totalScore = totalScore1;
            oneLineRemoved = oneLineRemoved1;
            twoLinesRemoved = twoLinesRemoved1;
            threeLinesRemoved = threeLinesRemoved1;
            fourLinesRemoved = fourLinesRemoved1;
			
			repaint();
		}
		
		public void updateScoreLevel(int linesRemoved1, int score1, int level1,
				int totalScore1, int oneLineRemoved1, int twoLinesRemoved1,
				int threeLinesRemoved1, int fourLinesRemoved1) {
			
			level = level1;
            linesRemoved = linesRemoved1;
			score = score1;
			totalScore = totalScore1;
			
            oneLineRemoved = oneLineRemoved1;
            twoLinesRemoved = twoLinesRemoved1;
            threeLinesRemoved = threeLinesRemoved1;
            fourLinesRemoved = fourLinesRemoved1;
            
			repaint();
		}
		
		public void setLevel(int level1) {
			level = level1;
			repaint();
		}
		
		@Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        drawPanel(g);
	    }
	   
	    @Override
	 	public Dimension getPreferredSize() {
	 		return new Dimension(300, 120);
	    }
	}
}  




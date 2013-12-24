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

public class Welcome extends JPanel {
	final Game game;
	final PlayAudio audio;
	Upper upper;
	
	public Welcome(Game parent, PlayAudio sound){
		game = parent;
		audio = sound;
		Lower lower = new Lower();
		Middle middle = new Middle();
		upper = new Upper();
		
		setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.blue));
		setLayout(new BorderLayout());
		
		add(upper, BorderLayout.NORTH);
		add(middle, BorderLayout.CENTER);
		add(lower, BorderLayout.SOUTH);
	}
	
	public class Lower extends JPanel {
		public Lower() {
			setPreferredSize(new Dimension(600,100));
            Icon playGameIcon = new ImageIcon("PlayGame.jpg");
	        JButton playGameButton = new JButton(playGameIcon);
	    
	        playGameButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	    
	        this.add(playGameButton);
	    
	        // playGameButton.setBounds(50, 60, 80, 30);
	        playGameButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	        	    audio.playClip("Go");
	                game.startInstruction();
	           }
	        });
		}
	}
	
	public class Middle extends JPanel {
		public Middle() {
			setSize(new Dimension(600,100));
			setBackground(Color.LIGHT_GRAY);
			// setBorder(BorderFactory.createLineBorder(Color.blue));
			/*
              Font monoFont = new Font("Monospaced", Font.BOLD
		                | Font.ITALIC, 36);
	           g.setFont(monoFont);
	           g.drawString("TETRIS", w, x);
			*/
			String str1 = "TETRIS GAME";
			String str2 = "Click PLAY to Play Game";
			String str3 = "CIS120 Homework 10";
			String str4 = "12/6/2012";
			
			JLabel row1 = new JLabel(str1);
			JLabel row2 = new JLabel(str2);
			JLabel row3 = new JLabel(str3);
			JLabel row4 = new JLabel(str4);
			
			setLayout(new GridLayout(4,1));
			
			row1.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
			row2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			row3.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			row4.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
			
			row1.setFont(new Font("Serif", Font.BOLD, 20));
			row2.setFont(new Font("Serif", Font.BOLD, 20));
			row3.setFont(new Font("Serif", Font.BOLD, 20));
			row4.setFont(new Font("Serif", Font.BOLD, 20));
			
			row1.setHorizontalAlignment( SwingConstants.CENTER );
			row2.setHorizontalAlignment( SwingConstants.CENTER );
			row3.setHorizontalAlignment( SwingConstants.CENTER );
			row4.setHorizontalAlignment( SwingConstants.CENTER );
			
			add(row1);
			add(row2);
			add(row3);
			add(row4);
		}
	}
	public class Upper extends JPanel implements ActionListener {
		private int squareWidth = 18;
		private int squareHeight = 18;
		private int pointX = 20;
		private int pointY = 0;
	    private Timer timer;
	    private int interval = 50;
		
		Tetrominoes s;
		
		public Upper() {
			setPreferredSize(new Dimension(600,350));
			timer = new Timer(interval, this);
			timer.start(); 
		}
		@Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        drawShape(g);
	    }

	    private void drawShape(Graphics g)
	    {
	        Color colors[] = { 
	        	new Color(0, 0, 0),       new Color(204, 102, 102), 
	            new Color(102, 204, 102), new Color(102, 102, 204), 
	            new Color(204, 204, 102), new Color(204, 102, 204), 
	            new Color(102, 204, 204), new Color(218, 170, 0)
	        };

	        int shapeTable[][][] = { 
	        		{{0, 0, 0, 0}, {0, 0, 0, 0}}, 
	        		{{0, 1, 1, 0}, {1, 1, 0, 0}},
	        		{{1, 1, 0, 0}, {0, 1, 1, 0}},
	        		{{0, 0, 0, 0}, {1, 1, 1, 1}},
	        		{{0, 1, 0, 0}, {1, 1, 1, 0}},
	        		{{0, 1, 1, 0}, {0, 1, 1, 0}},
	        		{{1, 0, 0, 0}, {1, 1, 1, 0}},
	        		{{0, 0, 1, 0}, {1, 1, 1, 0}}
	        };

	        pointY++;
	        pointX = 20;
	        int x1 = pointX;
	        int y1 = pointY++;
	        
	        for (int n = 1; n <= 7; n++) {
	            Color color = colors[n];
	            int shapedata[][] = shapeTable[n];
	        
	            for (int i = 0; i < 2; i++) {
	        	    for (int j = 0; j < 4; j++) {
	        		    int x = pointX + j * squareWidth;
	        		    int y = pointY + i * squareHeight;
	        		    if (shapedata[i][j] != 0)
	        			    drawSquare(g, x, y, color);
	        	    }
	            }
	            if (n == 3)
	            	pointX += 95;
	            else if (n == 4)
	            	pointX += 75;
	            else
	            	pointX += 85;
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
	    
	    /**
	     * Timer function to invoke paintComponent to repaint the pieces
	     * dropping down to the floor
	     */
	     public void actionPerformed(ActionEvent e) {
	         Graphics g = getGraphics();
	         if (pointY <= 311){
	        	 // play explosion sound after pieces dropped to floor
	        	 if (pointY >= 310)
	        	     audio.playClip("Stop");
	        	 repaint();
	         }
	         else {
	             timer.stop();
	         }
	     }	
	}
}




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

public class Instruction extends JPanel {

    final Game game;
    final PlayAudio audio;
    
    public Instruction(Game parent, PlayAudio sound) {
        game = parent;
        audio = sound;
        
        setPreferredSize(new Dimension(600,600));
		
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
		
        final Icon playNowIcon = new ImageIcon("PlayNow.jpg");
	    
        JButton playNowButton = new JButton(playNowIcon);
        playNowButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
	    this.add(playNowButton);
	   
        playNowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                audio.playClip("Go");
                game.startGame();
            }
        });
    }
	
    public void drawPanel(Graphics g) {
        String str[] = {
        "How to Play Tetris:",
        "Create horizontal lines using the falling blocks. When you make a",
        "line, it is cleared from the screen. You lose if the blocks pile up",
        "to the top of the screen. You get more points for completing multiple",
        "lines at once. On higher levels scores earned are multipled by the ",
        "level you are on.",
        "",
        "Keyboard Command:",
        "Use the left and right arrow keys to move blocks left or right,",
        "up arrow key to rotate blocks clockwise, down arrow key to hard drop",
        "P or p key to toggle Stop/Go command.", 
        "",
        "Mouse Click Command:",
        "Click STOP to pause the game.  Click GO to start a game or to resume ",
        "a paused game.  Click ? to display Instructions. Click Quit to exit ",
        "the game.  Click Right or Left arrows to select level.",
        "",	
        "Score System:",
        "Score = Level * base score",
        "Base Score for lines removed: 1 line: 4, 2 lines: 10, 3 lines: 80,",
        "4 lines: 120.  Note: Your level is automatically incremented by 1 ",
        "after your total score exceeds 300, 600, 900, or 1200. Blocks fall",
        "on higher levels!  The max level is 5.",
        "                                                           Enjoy!"
        };

        g.setFont(new Font("Arial", Font.BOLD, 14));

        for (int i = 0; i < str.length; i++) {		
            g.drawString(str[i], 40, 20 * i + 100);
        }
    }
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPanel(g);
    }
   
    @Override
 	public Dimension getPreferredSize() {
 		return new Dimension(600, 600);
    }
}


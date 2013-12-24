import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class ChoiceLevel extends JPanel {

    int level = 1;
    JLabel number;
    PlayAudio audio;
    Tool toolPanel;
    JLabel statusbar;
    final Icon oneIcon;
    final Icon twoIcon;
    final Icon threeIcon;
    final Icon fourIcon;
    final Icon fiveIcon;
    
    public ChoiceLevel(Tool parent, PlayAudio sound) {
        toolPanel = parent;
        audio = sound;
		
        setPreferredSize(new Dimension(160, 100));
        setFocusable(true);
		
        Icon leftIcon = new ImageIcon("ArrowLeft.jpg");
        Icon rightIcon = new ImageIcon("ArrowRight.jpg");

        oneIcon = new ImageIcon("Red1.jpg");
        twoIcon = new ImageIcon("Red2.jpg");
        threeIcon = new ImageIcon("Red3.jpg");
        fourIcon = new ImageIcon("Red4.jpg");
        fiveIcon = new ImageIcon("Red5.jpg");
	    
        JLabel header = new JLabel("SELECT LEVEL");
        header.setFont(new Font("Serif", Font.BOLD, 16));
        header.setHorizontalAlignment(JLabel.CENTER);

        statusbar = new JLabel("Click GO to Start");
        statusbar.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 16));
        statusbar.setHorizontalAlignment(JLabel.CENTER);
        statusbar.setForeground(Color.blue);
	    
        number = new JLabel(oneIcon);
        JButton leftButton = new JButton(leftIcon);
        JButton rightButton = new JButton(rightIcon);
	    
        number.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        leftButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rightButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	    
	    
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (level > 1) {
                    level--;
	                Icon choiceIcon = null;
	        		
	                if (level == 1)
	                    choiceIcon = oneIcon;
	                else if (level == 2)
	                    choiceIcon = twoIcon;
	                else if (level == 3)
	                    choiceIcon = threeIcon;
	                else if (level == 4)
	                    choiceIcon = fourIcon;
	                else if (level == 5)
	                    choiceIcon = threeIcon;
	        		
	                number.setIcon(choiceIcon);
	                toolPanel.setLevel(level);
	                audio.playClip("Decrement");
	            }
	        }
	    });

	    rightButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	            if (level < 5) {
	                level++;
	                Icon choiceIcon = null;
	        		
	                if (level == 1)
	                    choiceIcon = oneIcon;
	                if (level == 2)
	                    choiceIcon = twoIcon;
	                if (level == 3)
	                    choiceIcon = threeIcon;
	                if (level == 4)
	                    choiceIcon = fourIcon;
	                if (level == 5)
	                    choiceIcon = fiveIcon;
	        		
	                number.setIcon(choiceIcon);
	                toolPanel.setLevel(level);
	                audio.playClip("Increment");
	        	}
	       }
	    });
	    
	    setLayout(new BorderLayout());
	    this.add(header, BorderLayout.NORTH);
	    this.add(number, BorderLayout.CENTER);

	    this.add(leftButton, BorderLayout.WEST);
	    this.add(rightButton, BorderLayout.EAST);
	    this.add(statusbar, BorderLayout.SOUTH);
	}
	
    public void bumpLevel(int lev) {
        if (lev > 5) return;
        level = lev;
		
        Icon choiceIcon = null;
    		
        if (level == 1)
            choiceIcon = oneIcon;
        if (level == 2)
            choiceIcon = twoIcon;
        if (level == 3)
            choiceIcon = threeIcon;
        if (level == 4)
            choiceIcon = fourIcon;
        if (level == 5)
            choiceIcon = fiveIcon;
    		
        number.setIcon(choiceIcon);
        audio.playClip("Increment");
   		
        repaint();
	}

    public void updateStatus(String s) {
	    statusbar.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 16));
	    statusbar.setHorizontalAlignment(JLabel.CENTER);
	    statusbar.setForeground(Color.blue);
	    if (s.isEmpty()) s = "   ";
	    
	    statusbar.setText(s);
	    repaint();
	}
	/** Set the state of the state of the game to its initial value and 
	    prepare the game for keyboard input. */

   @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Paint background, border
    }

   @Override
    public Dimension getPreferredSize() {
        return new Dimension(160, 100);
    }
}

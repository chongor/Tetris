import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.*;

public class Manual extends JPanel {

	/*
	private int squareWidth = 24;
	private int squareHeight = 24;
	private int pointX = 60;
	private int pointY = 30;
	private boolean stop = true;
	private boolean go = false;
	private boolean helpOn = false;
	*/
	final Tool toolPanel;
	
	public Manual(Tool parent) {
		toolPanel = parent;
		
		String str1 = "USEAGE OF CONTROL KEYS AND BUTTONS";
		String str2 = "Left Arrow: Move Block Left";
		String str3 = "Right Arrow: Move Block Right";
		String str4 = "Up Arrow: Rotate Block Clockwise";
		String str5 = "Down Arrow: Fast Speed Hard Drop";
		String str6 = "P or p: Toggle Pause(Stop)";
		String str7 = "GO: Start to Play";
		String str8 = "STOP: Stop(Pause)";
		String str9 = "QUIT: Exit the Game";
		String str10 = "?: Help";
		String str11 = "SCORE SYSTEM:";
		String str12 = "1 Line = 5       2 Lines = 10"; 
		String str13 = "3 Lines = 30     4 Lines = 120";
		String str14 = "Score = Level x Lines Removed";
		
		this.setBorder(BorderFactory.createLineBorder(Color.blue));
		this.setBackground(Color.lightGray);
		setLayout(new GridLayout(20,1));
		
		JLabel row1 = new JLabel(str1);
		JLabel row2 = new JLabel(str2);
		JLabel row3 = new JLabel(str3);
		JLabel row4 = new JLabel(str4);
		JLabel row5 = new JLabel(str5);
		JLabel row6 = new JLabel(str6);
		JLabel row7 = new JLabel(str7);
		JLabel row8 = new JLabel(str8);
		JLabel row9 = new JLabel(str9);
		JLabel row10 = new JLabel(str10);
		JLabel row11 = new JLabel(str11);
		JLabel row12 = new JLabel(str12);
		JLabel row13 = new JLabel(str13);
		JLabel row14 = new JLabel(str14);
		
		row1.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
		row2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		row3.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		row4.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		row5.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		row6.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		row7.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		row8.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		row9.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		row10.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		row11.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		row12.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		row13.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		row14.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
		this.add(row1);
		this.add(row2);
		this.add(row3);
		this.add(row4);
		this.add(row5);
		this.add(row6);
		this.add(row7);
		this.add(row8);
		this.add(row9);
		this.add(row10);
		this.add(row11);
		this.add(row12);
		this.add(row13);
		this.add(row14);
		
	    final Icon exitIcon = new ImageIcon("Exit.jpg");
	    
	    JButton exitButton = new JButton(exitIcon);
		exitButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	   
	    exitButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	            toolPanel.exitHelp();
	       }
	    });
	    
	    this.add(exitButton);
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
   
    @Override
 	public Dimension getPreferredSize() {
 		return new Dimension(300, 600);
    }
}


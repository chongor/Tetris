import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.*;

public class StopGo extends JPanel {

	private boolean stop = true;
	private boolean go = false;
	
	
	final Tool toolPanel;
	final PlayAudio audio;
	
	public StopGo(Tool parent, PlayAudio sound) {
		toolPanel = parent;
	    audio = sound;

		this.setBorder(BorderFactory.createLineBorder(Color.blue));
		setLayout(new GridLayout(2,2));
		
	    final Icon stopIcon = new ImageIcon("Stop.jpg");
	    final Icon goIcon = new ImageIcon("Go.jpg");

	    final Icon quitIcon = new ImageIcon("Quit.jpg");
	    final Icon helpIcon = new ImageIcon("Help.jpg");
	    
	    final JButton stopButton = new JButton(stopIcon);
	    //stopButton.setBounds(80, 20, 30, 30);
	    
	    final JButton goButton = new JButton(goIcon);
	    //goButton.setBounds(120, 20, 30, 30);
	    
	    JButton quitButton = new JButton(quitIcon);
	    //quitButton.setBounds(10, 20, 30, 30);

	    JButton helpButton = new JButton(helpIcon);
	    //helpButton.setBounds(800, 100, 40, 40);
	    
		stopButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		goButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		quitButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		helpButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	   
		add(stopButton);
		add(goButton);
		add(quitButton);
		add(helpButton);
		
	    quitButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	            System.exit(0);
	       }
	    });
	    
	    stopButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	        	audio.playClip("Help");
        		toolPanel.stop();
	       }
	    });
	    
	    helpButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	        	audio.playClip("Newpiece");
         		toolPanel.help();
	       }
	    });
	    
	    goButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	        	audio.playClip("Help");
        		toolPanel.go();
            }
	    });
	    
	    this.add(quitButton);
        this.add(stopButton);
        this.add(goButton);
        this.add(helpButton);
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
   
    @Override
 	public Dimension getPreferredSize() {
 		return new Dimension(240, 160);
    }
    
}


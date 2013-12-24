import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.sound.midi.*;

public class Board extends JPanel implements ActionListener {
	
    final int BoardWidth = 15;
    final int BoardHeight = 30;
   
    Timer timer;
    boolean isFallingFinished = false;
    public boolean isStarted = false;
    public boolean isPaused = false;
    
    int level = 1;
    int score = 0;
    int totalScore = 0;
    int scoreLadder = 300;
    
    int numLinesRemoved = 0;
    int oneLineRemoved = 0;
    int twoLinesRemoved = 0;
    int threeLinesRemoved = 0;
    int fourLinesRemoved = 0;
    
    int curX = 0;
    int curY = 0;
    Shape curPiece = null;
    Shape nextPiece = null;
    
    Tetrominoes[] board;
    PlayAudio audio;
    
    House house;
    boolean gameover = false;
    int scorelevel = 10;
    
    Sequencer musica;
    MidiPlayer midi;

    public Board(House parent, PlayAudio sound){
        audio = sound;
        house = parent;
        setPreferredSize(new Dimension(315,560));
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
    	setBackground(Color.white);
        setLevel(1);    // starting level: 1
        // create current piece and next piece
        curPiece = new Shape();
        nextPiece = new Shape();
        nextPiece.setRandomShape();
        timer = new Timer(400, this);
        board = new Tetrominoes[BoardWidth * BoardHeight];
        addKeyListener(new TAdapter());
        clearBoard(); 
    }
    
    public void getMidi(MidiPlayer m) {
        midi = m;
        musica = midi.loadMidi("Tetris_MusicA.mid");
    }
   
    public void startMusic() {
        midi.playMidi(musica);
    }
   
    public void stopMusic() {
        midi.stopMidi(musica);
    }

    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            for(int i = 0; i < level; i++)
                oneLineDown();
        }
    }

    int squareWidth() { return (int) getSize().getWidth() / BoardWidth; }
    
    int squareHeight() { return (int) getSize().getHeight() / BoardHeight; }
    
    Tetrominoes shapeAt(int x, int y) { return board[(y * BoardWidth) + x]; }

    public void getKeyboard() {
        setFocusable(true);
        requestFocusInWindow();
    }
    
    public void start()
    {
        if (isPaused)
            return;
        
        score = 0;
        startMusic(); 
        
        getKeyboard();
        
        isStarted = true;
        isFallingFinished = false;
        clearBoard();

        newPiece();
        gameover = false;
        timer.start();
        house.updateStatus("Play Game");
        
        house.updateScore(numLinesRemoved, score, totalScore, 
        		oneLineRemoved, twoLinesRemoved, threeLinesRemoved, 
        		fourLinesRemoved);
    }
    
    public void stop() {
        if (!isStarted)
            return;

        if (!isPaused) {
            timer.stop();
            house.updateStatus("Stopped.Click GO");
        }
        isPaused = true;
        repaint();
        getKeyboard();
    }

    public void pause()
    {
        if (!isStarted)
            return;

        isPaused = !isPaused;
        if (isPaused) {
            timer.stop();
            house.updateStatus("Stopped.Click GO");
        } else {
            timer.start();
            gameover = false;
            house.updateStatus("Play Game");
        }
        repaint();
        getKeyboard();
    }

    public void paint(Graphics g)
    { 
        super.paint(g);

        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();

        for (int i = 0; i < BoardHeight; ++i) {
            for (int j = 0; j < BoardWidth; ++j) {
                Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);
                if (shape != Tetrominoes.NoShape)
                    drawSquare(g, 0 + j * squareWidth(),
                               boardTop + i * squareHeight(), shape);
            }
        }

        if (curPiece.getShape() != Tetrominoes.NoShape) {
            for (int i = 0; i < 4; ++i) {
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);
                drawSquare(g, 0 + x * squareWidth(),
                           boardTop + (BoardHeight - y - 1) * squareHeight(),
                           curPiece.getShape());
            }
        }
        if (isPaused || gameover) {
        	String s;
        	if (gameover){
                audio.playClip("Lose");
                s = "GAME OVER";
        		
        	}
        	else
                s = "STOPPED";
        	setOpaque(true);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.setColor(Color.red);
        	g.drawString(s, 60, 200);
        	setOpaque(false);
        }
    }

    private void dropDown()
    {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }

    private void oneLineDown()
    {
        if (!tryMove(curPiece, curX, curY - 1))
            pieceDropped();
    }


    private void clearBoard()
    {
        for (int i = 0; i < BoardHeight * BoardWidth; ++i)
            board[i] = Tetrominoes.NoShape;
    }

    private void pieceDropped()
    {
    	audio.playClip("Dropped");
    	
        for (int i = 0; i < 4; ++i) {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BoardWidth) + x] = curPiece.getShape();
        }

        removeFullLines();

        if (!isFallingFinished)
            newPiece();
    }

    private void newPiece()
    {
        curPiece.setShape(nextPiece.getShape());
        nextPiece.setRandomShape();

        curX = BoardWidth / 2 + 1;
        curY = BoardHeight - 1 + curPiece.minY();
        
        if (!tryMove(curPiece, curX, curY)) {
            curPiece.setShape(Tetrominoes.NoShape);
            nextPiece.setRandomShape();
            timer.stop();
            isStarted = false;
            house.updateStatus("Game Over.Click GO.");
            gameover = true;
            getKeyboard();
            stopMusic();
            repaint();
        }
        else {
            house.updateShape(nextPiece.getShape());
            getKeyboard();
        }
        	
    }

    private boolean tryMove(Shape newPiece, int newX, int newY)
    {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight)
                return false;
            if (shapeAt(x, y) != Tetrominoes.NoShape)
                return false;
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;
        repaint();
        
        return true;
    }

    
    /**
     * Base Score: 1 line: 5, 2 lines: 10, 3 lines: 30, 4 lines: 120,
     * 5 lines: 200
     * Score: Level * Lines Removed
     * @param lev
     */
    public void setLevel(int lev) {
    	level = lev;
    	
    	getKeyboard();
    }
    
    private void removeFullLines()
    {
        int numFullLines = 0;
        int baseScore = 0;

        for (int i = BoardHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < BoardWidth; ++j) {
                if (shapeAt(j, i) == Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < BoardHeight - 1; ++k) {
                    for (int j = 0; j < BoardWidth; ++j)
                         board[(k * BoardWidth) + j] = shapeAt(j, k + 1);
                }
            }
        }

        int quotient = score / scoreLadder;
        
        if (numFullLines > 0) {
        	if (numFullLines == 1) {
        		oneLineRemoved++;
                baseScore = 5;
        	} else if (numFullLines == 2) {
        		twoLinesRemoved++;
                baseScore = 10;
        	} else if (numFullLines == 3) {
        		threeLinesRemoved++;
                baseScore = 30;
        	} else if (numFullLines == 4) {
        		fourLinesRemoved++;
                baseScore = 120;
        	} else {
                baseScore = 200;
        	}
        	
        	score += level * baseScore;
        	totalScore += level * baseScore;
        	
        	int quotient1 = score / scoreLadder;
            numLinesRemoved += numFullLines;
            isFallingFinished = true;
            curPiece.setShape(Tetrominoes.NoShape);
            repaint();
            
            if ((quotient1 > quotient) && (level < 5)) {
            	
                level++;
                
                house.updateScoreLevel(numLinesRemoved, score, level,
                		totalScore, oneLineRemoved, twoLinesRemoved,
                		threeLinesRemoved, fourLinesRemoved);
            }
            else {
            	
                house.updateScore(numLinesRemoved, score, totalScore,
                		oneLineRemoved, twoLinesRemoved, threeLinesRemoved,
                		fourLinesRemoved);
                
            }
            getKeyboard();
        }
     }

    private void drawSquare(Graphics g, int x, int y, Tetrominoes shape)
    {
        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), 
            new Color(102, 204, 102), new Color(102, 102, 204), 
            new Color(204, 204, 102), new Color(204, 102, 204), 
            new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                         x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                         x + squareWidth() - 1, y + 1);

    }

    class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            if (!isStarted || curPiece.getShape() == Tetrominoes.NoShape) {  
                return;
            }

            int keycode = e.getKeyCode();

            if (keycode == 'p' || keycode == 'P') {
                pause();
                return;
            }

            if (isPaused)
                return;

            switch (keycode) {
            case KeyEvent.VK_LEFT:
                tryMove(curPiece, curX - 1, curY);
                break;
            case KeyEvent.VK_RIGHT:
                tryMove(curPiece, curX + 1, curY);
                break;
            case KeyEvent.VK_DOWN:
                dropDown();
                break;
            case KeyEvent.VK_UP:
            	Shape newShape = curPiece.rotateRight();
            	boolean okRotate = tryMove(newShape, curX, curY);
            	if (!okRotate) {
            		okRotate = tryMove(newShape, curX - 1, curY);
            	}
                break;
            }
         }
     }
}


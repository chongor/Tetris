import javax.sound.midi.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MidiPlayer{
    public MidiPlayer() {
    }
	
    public Sequencer loadMidi(String filename) {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            if (sequencer == null)
                throw new MidiUnavailableException();
            sequencer.open();
            FileInputStream is = new FileInputStream(filename);
            Sequence Seq = MidiSystem.getSequence(is);
            sequencer.setSequence(Seq);
            return sequencer;
      } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
    }
    public void playMidi(Sequencer sequencer) {
        sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        sequencer.start();
    }
  
    public void stopMidi(Sequencer sequencer) {
        if (sequencer.isRunning())
            sequencer.stop();
    }
}


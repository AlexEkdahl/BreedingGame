package Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Audio implements Serializable {
    private static final long serialVersionUID = 1L;
    static Clip clip;
    static AudioInputStream stream;

    public Audio(String filePath)
            throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        stream = AudioSystem.getAudioInputStream(new File(filePath));
        clip = AudioSystem.getClip();
        clip.open(stream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    public static void soundEffect(String filePath)
    throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        stream = AudioSystem.getAudioInputStream(new File(filePath));
        clip = AudioSystem.getClip();
        clip.open(stream);
        clip.start();
    }



}

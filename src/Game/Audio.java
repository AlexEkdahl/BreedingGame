package Game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Audio implements Serializable {
    static Clip clip;
    static AudioInputStream stream;

    public static void soundEffect(String filePath)
            throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        stream = AudioSystem.getAudioInputStream(new File(filePath));
        clip = AudioSystem.getClip();
        clip.open(stream);
        clip.start();
    }

    public static void themeSong(String filePath)
            throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        stream = AudioSystem.getAudioInputStream(new File(filePath));
        clip = AudioSystem.getClip();
        clip.open(stream);
        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        double sound = 0.2;
        float dB = (float) (Math.log(sound) / Math.log(10.0) * 20.0);
        volume.setValue(dB);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    public static void dummyMethod() {
        if (clip.isRunning()) {
            clip.stop();
        } else {
            clip.start();
        }
    }
}

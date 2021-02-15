package game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Audio implements Serializable {

    static Clip clip;
    static Clip clip1;
    static AudioInputStream stream;

    public static void soundEffect(String filePath) throws IOException, UnsupportedAudioFileException,
            LineUnavailableException {
        stream = AudioSystem.getAudioInputStream(new File(filePath));
        clip = AudioSystem.getClip();
        clip.open(stream);
        clip.start();
    }

    public static void themeSong(String filePath) throws IOException, UnsupportedAudioFileException,
            LineUnavailableException {
        stream = AudioSystem.getAudioInputStream(new File(filePath));
        clip1 = AudioSystem.getClip();
        clip1.open(stream);
        FloatControl volume = (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
        double sound = 0.2;
        // calculate db
        float dB = (float) (Math.log(sound) / Math.log(10.0) * 20.0);
        volume.setValue(dB);
        clip1.loop(Clip.LOOP_CONTINUOUSLY);
        clip1.start();
    }

    public static void dummyMethod() {
        if (clip1.isRunning()) {
            clip1.stop();
        } else {
            clip1.start();
        }
    }

}

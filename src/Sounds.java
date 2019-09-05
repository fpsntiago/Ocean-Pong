import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;
//From tank game
class Sounds{
    void WAV(String filename) {

        try {
            Clip clip = AudioSystem.getClip();
            URL file = this.getClass().getClassLoader().getResource(filename);

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip.open(inputStream);

            FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(0);
            clip.start();

        } catch (IOException|LineUnavailableException|UnsupportedAudioFileException e ) {
            e.printStackTrace();
        }
    }
}
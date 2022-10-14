import javax.sound.sampled.*;
import java.io.File;

public class playmusic {
    public playmusic(){
        try {
            
        
        File file = new File("C:\\Users\\hille\\Documents\\GitHub\\DDU\\Game2D\\assets\\SCI FI REMIX.wav");
        AudioInputStream audiostream = AudioSystem.getAudioInputStream(file); 
        Clip clip = AudioSystem.getClip();
        clip.open(audiostream);

        while(true){
            clip.loop(clip.LOOP_CONTINUOUSLY);
            clip.start();
        }


        } catch (Exception e) {
            System.out.println("music dont work");
        }
    }
}

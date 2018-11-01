package edu.virginia.engine.display;

import java.util.HashMap;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class SoundManager {
    static HashMap<String,Clip> soundHashmap = new HashMap();
    static HashMap<String,Clip> musicHashmap = new HashMap();

    private void LoadSoundEffect(String id, String filename){
        try {
            URL url = this.getClass().getClassLoader().getResource(filename);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            soundHashmap.put(id, AudioSystem.getClip());
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void PlaySoundEffect(String id){
        soundHashmap.get(id).start();
    }

    private void LoadMusic(String id, String filename){
        try {
            URL url = this.getClass().getClassLoader().getResource(filename);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            musicHashmap.put(id, AudioSystem.getClip());
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void PlayMusic(String id){
        musicHashmap.get(id).loop(Clip.LOOP_CONTINUOUSLY);
    }
}

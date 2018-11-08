package edu.virginia.engine.display;

import java.util.HashMap;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class SoundManager {
    static HashMap<String,Clip> soundHashmap = new HashMap();
    static HashMap<String,Clip> musicHashmap = new HashMap();

    public void LoadSoundEffect(String id, String filename){
        try {
            URL url = this.getClass().getResource("../../../../sounds/" + filename);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            soundHashmap.put(id, AudioSystem.getClip());
            soundHashmap.get(id).open(audioIn);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void PlaySoundEffect(String id){
        soundHashmap.get(id).setFramePosition(0);
        soundHashmap.get(id).start();
    }

    public void LoadMusic(String id, String filename){
        try {
            URL url = this.getClass().getResource("../../../../sounds/" + filename);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            musicHashmap.put(id, AudioSystem.getClip());
            musicHashmap.get(id).open(audioIn);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void PlayMusic(String id){
        musicHashmap.get(id).start();
        musicHashmap.get(id).loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static HashMap<String, Clip> getMusicHashmap() {
        return musicHashmap;
    }
}

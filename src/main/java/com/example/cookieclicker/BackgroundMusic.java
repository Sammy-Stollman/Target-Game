package com.example.cookieclicker;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BackgroundMusic extends Application {
    public void RunMusic(String path){
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(5);
        }catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        BackgroundMusic song = new BackgroundMusic();
        song.RunMusic("target/classes/com/example/cookieclicker/Background music.wav");
    }
}


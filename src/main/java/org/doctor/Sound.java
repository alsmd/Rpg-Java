package org.doctor;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Sound {
    Map<String, Clip> clips = new HashMap<>();

    public Sound(){
    }

    public void add(String name, String path){
        try{
            File audioFile = new File(path);
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;

            stream = AudioSystem.getAudioInputStream(audioFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clips.put(name, (Clip) AudioSystem.getLine(info));
            clips.get(name).open(stream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void play(String name){
        clips.get(name).start();
    }

    public void loop(String name){
        clips.get(name).loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(String name){
        clips.get(name).stop();
    }
}

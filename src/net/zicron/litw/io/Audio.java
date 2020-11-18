package net.zicron.litw.io;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

public class Audio {

    public static final Audio SOUND = new Audio(false);
    public static final Audio MUSIC = new Audio(false);


    private boolean loop;
    private AudioClip clip;

    public Audio(boolean loop){
        this.loop = loop;
    }

    public void play(String file){
        try{
            clip = Applet.newAudioClip(new File("res/" + file).toURI().toURL());
            new Thread(() -> {if(loop) {clip.loop();} clip.play();}).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

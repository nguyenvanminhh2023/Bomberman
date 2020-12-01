/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;

/**
 *
 * @author Tram Tram
 */
public class Sound {
    public static Sound instance;

    public static final String PLAYGAME = "PLAYGAME.wav";
    public static final String BOMB = "BOM_SET.wav";
    public static final String DIE = "DIE.wav";
    public static final String BOM_EXPLODED = "BOM_EXPLODED.wav";
    public static final String ITEM = "item.wav";
    public static final String WIN = "WIN.wav";
    public static final String LOSE = "LOSE.wav";
    private HashMap<String, AudioClip> audioMap;

    public Sound() {
            audioMap = new HashMap<>();
            loadAllAudio();
    }

    public static Sound getIstance() {
        if (instance == null) {
            instance = new Sound();
        }

        return instance;
    }

    public void loadAllAudio() {
        putAudio(PLAYGAME);
        putAudio(BOMB);
        putAudio(DIE);
        putAudio(BOM_EXPLODED);
        putAudio(ITEM);
        putAudio(WIN);
        putAudio(LOSE);
    }

    public void stop() {
        getAudio(PLAYGAME).stop();
        getAudio(BOMB).stop();
        getAudio(BOM_EXPLODED).stop();
        getAudio(WIN).stop();
        getAudio(LOSE).stop();
    }

    public void putAudio(String name) {
        AudioClip auClip = Applet.newAudioClip(Sound.class.getResource(name));
        audioMap.put(name, auClip);
    }

    public AudioClip getAudio(String name) {
        return audioMap.get(name);
    }
}

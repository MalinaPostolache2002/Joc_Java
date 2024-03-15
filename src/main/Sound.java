package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.IOException;
import java.net.URL;

public class Sound {

    Clip clip;//deschidem audio file ul
    URL soundURL[]= new URL[30];//soundUrl importa audio urile
    FloatControl fc;//Controlul de volum al sunetului.

    int volumeScale=3;//avem 6 patratele ptr volum in bara de la music
    Float volume;

    public Sound ()
    {
        soundURL[0]= getClass().getResource("/sound/platformer.wav");
        soundURL[1]= getClass().getResource("/sound/banut.wav");
        soundURL[2]= getClass().getResource("/sound/pestisor.wav");
        soundURL[3]= getClass().getResource("/sound/speed.wav");
        soundURL[4]= getClass().getResource("/sound/congrats.wav");

    }
    public void SetFile(int i)
    {
        try{
            AudioInputStream ais= AudioSystem.getAudioInputStream(soundURL[i]);//încarcă sunetul corespunzător indicele utilizând URL-ul din soundURL
            clip=AudioSystem.getClip();
            clip.open(ais);//asta e un format ptr a deschide un audio in java
            fc=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);// punem o valoare/ setăm volumul

            checkVolume();

        }catch(Exception e)
        {

        }

    }

    public void play()
    {
        clip.start();
    }

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }//Această metodă redă sunetul în mod continuu, repetându-l.

    public void stop()
    {
        clip.stop();//cand vrem sa oprim sound ul
    }

    public void checkVolume()
    {
        switch (volumeScale)
        {
            case 0: volume= -80F;break;// min
            case 1: volume= -20F;break;
            case 2: volume= -12F;break;
            case 3: volume= -5F;break;
            case 4: volume= 1F;break;
            case 5: volume= 6F; break;// max

        }

        fc.setValue(volume);

    }




}

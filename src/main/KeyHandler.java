package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
public class KeyHandler implements KeyListener
{
    public boolean upPressed,downPressed,leftPressed,rightPressed;
    public boolean enterpressed=false;

    GamePanel gp;

    public KeyHandler(GamePanel gp)
    {
        this.gp=gp;
    }
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();//codul de pe tastatura(numar)

        //PLAY STATE

        if(gp.gamestate== gp.playstate)
        {
            if(code==KeyEvent.VK_UP)
            {
                upPressed=true;

            }
            if(code==KeyEvent.VK_RIGHT)
            {
                rightPressed=true;

            }
            if(code==KeyEvent.VK_LEFT)
            {
                leftPressed=true;

            }
            if(code==KeyEvent.VK_DOWN)
            {
                downPressed=true;

            }
            if(code==KeyEvent.VK_P)
            {
                gp.gamestate=gp.pausestate;


            }
            if(code==KeyEvent.VK_C)
            {
                gp.gamestate=gp.characterstate;


            }
            if(code==KeyEvent.VK_ESCAPE)
            {
                gp.gamestate=gp.optionstate;



            }



        }
        //PAUSE STATE
        else if(gp.gamestate==gp.pausestate)
        {
            if (code== KeyEvent.VK_P)
            {
                gp.gamestate= gp.playstate;
            }
        }

        //DIALOGUE STATE

        else if (gp.gamestate== gp.dialoguestate)
        {
            if(code== KeyEvent.VK_ENTER)
            {
                gp.gamestate=gp.playstate;
            }
        }

        //CHARACTER STATE
        else if (gp.gamestate== gp.characterstate)
        {
            if(code== KeyEvent.VK_C)
            {
                gp.gamestate=gp.playstate;
            }
        }

        //OPTION STATE
        else if (gp.gamestate== gp.optionstate)
        {
            int maxcomand=0;

            maxcomand=2;

            if(code==KeyEvent.VK_ENTER)
            {
                enterpressed=true;
            }
            if(code==KeyEvent.VK_UP)
            {
                gp.ui.commandNum--;

                if (gp.ui.commandNum<0)
                {
                    gp.ui.commandNum= maxcomand;
                }
            }
            if(code==KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if (gp.ui.commandNum>maxcomand)
                {
                    gp.ui.commandNum= 0;
                }
            }
            if(code==KeyEvent.VK_LEFT)
            {
                //MUSIC
                if(gp.ui.commandNum==0 && gp.music.volumeScale> 0)//music e prima=commandum e 1 si 0 prima patratia
                {
                    gp.music.volumeScale--;// daca putem da mai  incet sonoru o facem
                    gp.music.checkVolume();
                }

                //SE

                if(gp.ui.commandNum==1 && gp.se.volumeScale> 0)//se e a doua commandum e 1 si 0 e prima patratica
                {
                    gp.se.volumeScale--;// daca putem da mai  incet sonoru o facem

                }

            }
            if(code==KeyEvent.VK_RIGHT)
            {
                //MUSIC
                if(gp.ui.commandNum==0 && gp.music.volumeScale<5)//5 e ul patratica
                {
                    gp.music.volumeScale++;// daca putem da mai  incet sonoru o facem
                    gp.music.checkVolume();
                }

                //SE
                if(gp.ui.commandNum==1 && gp.se.volumeScale<5)//5 e ul patratica
                {
                    gp.se.volumeScale++;// daca putem da mai  incet sonoru o facem

                }

            }
            if(code== KeyEvent.VK_ESCAPE)
            {
                gp.gamestate=gp.playstate;
            }
        }

        //GAME OVER  STATE
        else if (gp.gamestate== gp.gameOverState)
        {

            if(code==KeyEvent.VK_UP)
            {
                gp.ui.commandNum--;

                if (gp.ui.commandNum<0)
                {
                    gp.ui.commandNum= 1;
                }
            }
            if(code==KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if (gp.ui.commandNum>1)
                {
                    gp.ui.commandNum= 0;
                }
            }

            if(code==KeyEvent.VK_ENTER)
            {
                enterpressed=true;
                if(gp.ui.commandNum== 0)
                {
                    gp.gamestate=gp.playstate;
                    gp.retry();
                }

                else  if(gp.ui.commandNum== 1)
                {
                    System.exit(0);

                }
            }
        }

        //TITLE STATE
        if (gp.gamestate == gp.titleState)
        {
            if(code==KeyEvent.VK_UP)
            {
                gp.ui.commandNum--;
                if (gp.ui.commandNum<0)
                {
                    gp.ui.commandNum= 2;
                }
            }
            if(code==KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if (gp.ui.commandNum> 2)
                {
                    gp.ui.commandNum= 0;
                }
            }
            if(code==KeyEvent.VK_ENTER)
            {
                if (gp.ui.commandNum==0)
                {
                    gp.gamestate=gp.playstate;//in GP declaram ca facemn update la playstate
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum==1)
                {
                    gp.saveload.load();
                    gp.gamestate=gp.playstate;
                    gp.playMusic(0);


                }
                if (gp.ui.commandNum==2)
                {
                    System.exit(0);
                }
            }
        }



        if(code== KeyEvent. VK_R)// refresh map
        {
            switch (gp.currentMap)
            {
                case 0: gp.tileM.loadMap("/maps/world01.txt",0); break;
                case 1: gp.tileM.loadMap("/maps/world02.txt",1); break;
                case 2: gp.tileM.loadMap("/maps/world03.txt",2); break;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_UP)
        {
            upPressed=false;

        }
        if(code==KeyEvent.VK_RIGHT)
        {
            rightPressed=false;

        }
        if(code==KeyEvent.VK_LEFT)
        {
            leftPressed=false;

        }
        if(code==KeyEvent.VK_DOWN)
        {
            downPressed=false;

        }
        if (code==KeyEvent.VK_ENTER)
        {
            enterpressed=false;
        }

    }
}
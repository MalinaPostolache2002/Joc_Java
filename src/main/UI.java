package main;

import object.OBJ_Banut;
import object.OBJ_Heart;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI //ptr a scrie mesaje e clasa asta
{
    GamePanel gp;
    Graphics2D g2;
    BufferedImage heart1, heart2, heart3;
    Font italic_40, italic_80B;
    BufferedImage banutImage;
    public boolean messageOn = false;
    public String message = " ";
    int messageCounter = 0;// ca sa disp la un mom dat msj ul de pe ecran
    public boolean gameFinished = false;
   public double playtime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");// cu cate zecimale
    public int commandNum = 0;//ptr a selecta un meniu item
    public String currentDialogue = "";
    int substate = 0;// in fctie de el scriem dif texte

    public UI(GamePanel gp) {
        this.gp = gp;
        italic_40 = new Font("Arial", Font.ITALIC, 40);
        italic_80B = new Font("Arial", Font.BOLD, 40);
        OBJ_Banut banut = new OBJ_Banut();
        banutImage = banut.image;

        //CREATE HEART OBJ
        SuperObject Heart = new OBJ_Heart();
        heart1 = Heart.image1;//inima goala
        heart2 = Heart.image2;//inima jum
        heart3 = Heart.image3;//inima plina

    }

    public void showMessage(String text)// primeste un text si il pune in acest mesaj string
    {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if (gameFinished == true) {
            g2.setFont(italic_80B);
            g2.setColor(Color.white);

            String text;
            int textLenght;
            int x;
            int y;

            text = "You win!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();// returneaza kung textului
            x = gp.screeenWidth / 2 - textLenght / 2;// textul va fi pozitionat pe centru
            y = gp.screeenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            text = "Your Time is: " + dFormat.format(playtime) + "!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();// returneaza kung textului
            x = gp.screeenWidth / 2 - textLenght / 2;// textul va fi pozitionat pe centru
            y = gp.screeenHeight / 2 + (gp.tileSize * 4);
            g2.drawString(text, x, y);

          // gp.SQLiteDatabaseCreator.insertScore(playtime);



            g2.setFont(italic_40);
            g2.setColor(Color.green);

            text = "You are great!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();// returneaza kung textului

            x = gp.screeenWidth / 2 - textLenght / 2;// textul va fi pozitionat pe centru
            y = gp.screeenHeight / 2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else {
            g2.setFont(italic_40);
            g2.setColor(Color.white);
            g2.drawImage(banutImage, gp.tileSize / 2, gp.tileSize, gp.tileSize, gp.tileSize, null);
            g2.drawString("x  " + gp.player.hasbanuti, 74, 85);// avem poza cu banutu si x ul este ori(cati banuti avem)

            //TIME
            playtime += (double) 1 / 60;//se adaugă o valoare de 1/60 (o secundă) la variabila playtime pentru a reprezenta trecerea timpului
            g2.drawString("Time: " + dFormat.format(playtime), gp.tileSize * 10, 65);//afișează textul "Time:" urmat de valoarea timpului
            // dFormat= e afisat cu 2 zecimale


            //MESSAGE

            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 3);

                messageCounter++;

                if (messageCounter > 120)// ins 2 sec
                {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
        this.g2 = g2;
        g2.setFont(italic_40);
        g2.setColor(Color.white);

        //TITLE STATE
        if (gp.gamestate == gp.titleState) {
            drawTitleScreen();
        }
        //PAUSE STATE
        if (gp.gamestate == gp.pausestate) {
            drawpausescreen();
            drawPLAYERlIFE();

        }
        //PLAY STATE
        if (gp.gamestate == gp.playstate) {
            drawPLAYERlIFE();
        }

        //DIALOGUE STATE
        if (gp.gamestate == gp.dialoguestate) {
            drawPLAYERlIFE();
            drawDialoguescreen();
        }

        //CHARACTER STATE

        if (gp.gamestate == gp.characterstate) {
            drawcharacterscreen();
        }

        //OPTION STATE
        if (gp.gamestate == gp.optionstate) {
            drawOptionscreen();
        }

        //GAME OVER STATE
        if (gp.gamestate == gp.gameOverState) {
            drawgameOverScreen();
        }

    }

    public void drawgameOverScreen() {
        g2.setColor(new Color(82, 149, 208, 255));
        g2.fillRect(0, 0, gp.screeenWidth, gp.screeenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));

        text = "    You died!";

        //SHADOW
        g2.setColor(Color.black);
        x = getcenteredtext(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);

        //Main
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50F));
        text = "  Retry";
        x = getcenteredtext(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
            if (gp.keyH.enterpressed == true) {
                gp.gamestate = gp.playstate;
                // gp.keyH.enterpressed=false;
            }
        }


        //Back to the TITLE SCRREN
        text = "  Exit";
        x = getcenteredtext(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
            if (gp.keyH.enterpressed == true) {
                gp.gamestate = gp.gameOverState;
                // gp.keyH.enterpressed=false;
            }
        }

    }


    public void drawOptionscreen() {
        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //SUB WINDOW
        final int frame_x = gp.tileSize * 3;
        final int frame_y = gp.tileSize;
        final int frame_width = gp.tileSize * 8;
        final int frame_height = gp.tileSize * 10;

        drawSubWindow(frame_x, frame_y, frame_width, frame_height);


        option_top(frame_x, frame_y);


    }

    public void option_top(int frame_x, int frame_y) {
        int textx;
        int texty;

        //TITLE

        String text = "Options";
        textx = getcenteredtext(text);
        texty = frame_y + gp.tileSize;
        g2.drawString(text, textx, texty);


        //MUSIC
        textx = frame_x + gp.tileSize / 2;// doar ptr primul se schimba x l ca sa nu mai fie anieat cu titlul options
        texty += gp.tileSize * 2;
        g2.drawString("Music", textx, texty);
        if (commandNum == 0)// declarat in KH
        {
            g2.drawString(">", textx - 15, texty);
        }

        //SE
        texty += gp.tileSize;
        g2.drawString("Sound Effect", textx, texty);
        if (commandNum == 1) {
            g2.drawString(">", textx - 15, texty);
        }


        //BACK
        texty += gp.tileSize * 3;// spatiu intre back si celelate
        g2.drawString("Back", textx, texty);
        if (commandNum == 2) {
            g2.drawString(">", textx - 15, texty);
            if (gp.keyH.enterpressed == true) {
                gp.gamestate = gp.playstate;
                commandNum = 0;
                gp.keyH.enterpressed = false;
            }
        }

        //MUSIC BOX VOL
        textx = frame_x + (int) (gp.tileSize * 4.5);
        texty = frame_y + gp.tileSize * 2 + 24;
        g2.drawRect(textx, texty, 120, 24);//120:5= 24 pixeli are o patratica
        int volumeWIdth = 24 * gp.music.volumeScale;
        g2.fillRect(textx, texty, volumeWIdth, 24);

        //SE BOX VOL
        texty += gp.tileSize;
        g2.drawRect(textx, texty, 120, 24);
        volumeWIdth = 24 * gp.se.volumeScale;
        g2.fillRect(textx, texty, volumeWIdth, 24);


    }

    public void drawcharacterscreen() {
        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(30F));

        //SUB WINDOW
        final int frame_x = gp.tileSize * 2;
        final int frame_y = gp.tileSize;
        final int frame_width = gp.tileSize * 5;
        final int frame_height = gp.tileSize * 5;

        drawSubWindow(frame_x, frame_y, frame_width, frame_height);


        int textX = frame_x + 20;
        int texty = frame_y + gp.tileSize;
        final int lineh = 40;

        //nume

        g2.drawString("Level", textX, texty);
        texty += lineh;

        g2.drawString("Life", textX, texty);
        texty += lineh;

        g2.drawString("Total levels", textX, texty);
        texty += lineh;

        g2.drawString("Total coins", textX, texty);
        texty += lineh;

        g2.drawString("Total fishes", textX, texty);
        texty += lineh;

        //valori
        int tailx = (frame_x + frame_width) - 30;

        //reset text y la poz initiala de sus
        texty = frame_y + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignRighttext(value, tailx);
        g2.drawString(value, textX, texty);
        texty += lineh;

        value = String.valueOf(gp.player.life);
        textX = getXforAlignRighttext(value, tailx);
        g2.drawString(value, textX, texty);
        texty += lineh;

        value = String.valueOf(gp.player.Total_levels);
        textX = getXforAlignRighttext(value, tailx);
        g2.drawString(value, textX, texty);
        texty += lineh;


        value = String.valueOf(gp.player.coin);
        textX = getXforAlignRighttext(value, tailx);
        g2.drawString(value, textX, texty);
        texty += lineh;

        value = String.valueOf(gp.player.pestisori);
        textX = getXforAlignRighttext(value, tailx);
        g2.drawString(value, textX, texty);
        texty += lineh;

    }

    public int getXforAlignRighttext(String text, int talix) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = talix - length;
        return x;
    }

    public void drawDialoguescreen() {
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screeenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 5;

        drawSubWindow(x, y, width, height);

        //unde asezam textul

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split(("\n")))// sa se afiseze pe 2 randuir cand avem /n
        {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);//ult nr seteaza opacitatea
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);//marginialbe

    }


    public void drawPLAYERlIFE() {
        //gp.player.life=3;

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        //LIFE EPUIZAT
        while (i < gp.player.maxLife / 2)// o inima ins 2 vieti
        {
            g2.drawImage(heart1, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        //DRAW CURRENT LIFE
        while (i < gp.player.life) {
            g2.drawImage(heart2, x, y, null);
            i++;
            if (i < gp.player.life)//schimbam jum de inima in inima intreaga
            {
                g2.drawImage(heart3, x, y, null);
            }
            i++;
            x += gp.tileSize;//ne ducem la urm heart

        }


    }

    public void drawTitleScreen() {

        g2.setColor(new Color(82, 149, 208));
        g2.fillRect(0, 0, gp.screeenWidth, gp.screeenHeight);

        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "       Go, Zglobi!";
        int x = getcenteredtext(text);
        int y = gp.tileSize * 3;


        //SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x + 7, y + 7);

        //MAIN COLOR PTR TEXT "GO, ZGLOBI!"
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //zglobi image
        x = gp.screeenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.up, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 47F));
        text = "             NEW GAME";
        x = getcenteredtext(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString("             >", x - gp.tileSize, y);
        }

        text = "            LOAD GAME";
        x = getcenteredtext(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString("             >", x - gp.tileSize, y);
        }

        text = "             EXIT";
        x = getcenteredtext(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString("             >", x - gp.tileSize, y);
        }

    }

    public void drawpausescreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Paused";
        int x;
        int y;
        x = getcenteredtext(text);
        y = gp.screeenHeight / 2;
        g2.drawString(text, x, y);
    }

    public int getcenteredtext(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screeenHeight / 2 - length / 2;
        return x;
    }






}







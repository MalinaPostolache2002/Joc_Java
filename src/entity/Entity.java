package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    protected GamePanel gp;
    public int worldX, worldY;
    public  int speed;
    public String name;
    public int actionLockcounter=0;// ca sa nu se miste random inamicul
    public BufferedImage up,down,left,right;
    public String direction="down";
    public int solidAreaDefaultX, solidAreaDefaultY;//ptr coliziuunea cu un Object
    public boolean collisionOn=false;
    public Rectangle solidArea= new Rectangle(0,0,60,60);//solid aare ptr toate
    public String[] dialogues = new String[20];
    public int dialogueIndex=0;

    //CHARACTER STATUS
    public int maxLife;
    public int life;
    public int level;
    public int Total_levels;
    public int coin;
    public int pestisori;



    public Entity(GamePanel gp)
    {
        this.gp=gp;
    }


    public void setaction(){

    }
    public void speak()
    {
        if(dialogues[dialogueIndex]==null)
        {
            dialogueIndex=0;//daca nu e text ne ducem din nou la textul 0,1,2..
        }
        gp.ui.currentDialogue= dialogues[dialogueIndex];
        dialogueIndex++;

    }

    public void update() {
        setaction();

        collisionOn=false;
        gp.cChecker.checkTile(this);//monstrul nostru nu poate trece prin tile uri
        gp.cChecker.checkObject(this,false);
        gp.cChecker.checkEntity(this, gp.monster);//monstrul nostru nu trece prin player
        //gp.cChecker.checkPlayer(this);//s a oprit inamicul daca "da"de zglobi

        //daca coliziunea e falsa playerul se poate misca

        if(collisionOn==false)//daca coliziunea e falsa playeryl poate sa mearga in cont
        {
            switch (direction) {
                case "up":
                    worldY-=speed;//x creste cand merge spre dreapta. y ul creste cand se deplaseaza in jos
                    break;
                case "down":
                    worldY+= speed;
                    break;
                case "left":
                    worldX-=speed;
                    break;
                case "right":
                    worldX+=speed;
                    break;
            }

        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image=null;
        int screenX = worldX-gp.player.worldX + gp.player.screenX;//unde pe ecran o desenam
        int screenY =  worldY-gp.player.worldY + gp.player.screenY;

        switch (direction)
        {
            case "up":
                image = up;
                break;
            case "down":
                image = down;
                break;

            case "left":
                image = left;
                break;
            case "right":
                image = right;
                break;
        }
        g2.drawImage(image,screenX,screenY, gp.tileSize, gp.tileSize,null);

    }


    public void life() {
        life=6;
    }
}

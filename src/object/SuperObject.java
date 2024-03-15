package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject
{
    public BufferedImage image;
    public BufferedImage image1, image2, image3;
    public String name;
    public boolean collision=false;
    public int worldX, worldY;
    public Rectangle solidArea=new Rectangle(0,0,48,48);//avem  solidArea pe toata suprafata obj
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;

    public void draw(Graphics2D g2, GamePanel gp)
    {
        int screenX = worldX-gp.player.worldX + gp.player.screenX;//unde pe ecran o desenam
        int screenY =  worldY-gp.player.worldY + gp.player.screenY;


        g2.drawImage(image,screenX,screenY,gp.tileSize, gp.tileSize, null);
    }
}

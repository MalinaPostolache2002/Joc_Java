package entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Player extends Entity
{

    KeyHandler keyH;//avem nev de aceste doua clase

    public final  int screenX;//unde desenam playerul in screen
    public final  int screenY;

    public int hasbanuti=0;//indica cat de multi banuti  are playerul deja


    public Player(GamePanel gp, KeyHandler keyH)//ca la GamePanel facem constructorul
    {

        super(gp);
        this.keyH=keyH;

        screenX=gp.screeenWidth/2-(gp.tileSize/2);//sa apara Zglobi pe mijlocul ecranului si am fct scaderea ca sa nu calc din dr lui Zglobi jum
        screenY=gp.screeenHeight/2-(gp.tileSize/2);

        solidArea=new Rectangle();
        solidArea.x=8;
        solidArea.y=16;

        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;//facem copii la solidArea ca pe acelea le vom schimba la un mom dat

        solidArea.width=32;
        solidArea.height=32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues()//poz initiala a pplayerului
    {
        worldX = gp.tileSize*23;//poz in worldMap a playerului la inceputul jocului si trb sa fie in mijloc
        worldY = gp.tileSize*21;
        speed = 4;
        direction="up";

        //PLAYER STATUS
        level=1;
        maxLife=6;
        life=6;
        coin=3;
        pestisori=3;
        Total_levels=3;


    }
     public void setDefaulPosition()
     {
         worldX=gp.tileSize*23;
         worldY=gp.tileSize*21;

         direction="down";
     }

     public void restorelife()
     {
         life=maxLife;
     }

    public static BufferedImage LoadImage(String path)
    {
        try {
            File file = new File(path);

            if (file.exists() && file.canRead()) {

                return ImageIO.read(file);
            }
            else
            {
                System.err.println("Could not access file: " + path);
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void getPlayerImage()
    {
        up= Player.LoadImage("res/player/penguinup.png");
        down= Player.LoadImage("res/player/penguindown.png");
        right=Player.LoadImage("res/player/penguinright.png");
        left= Player.LoadImage("res/player/penguinleft.png");


    }

    public void update()//apelam in GamePanel.java
    {
        if(keyH.upPressed==true || keyH.downPressed==true || keyH.leftPressed==true || keyH.rightPressed==true)
        {
            if(keyH.upPressed==true)
            {
                direction="up";


            }
            else if (keyH.downPressed==true)
            {
                direction="down";

            }
            else if (keyH.leftPressed==true)
            {
                direction="left";

            }
            else if (keyH.rightPressed==true)
            {
                direction="right";

            }

            //Verif coliziunea ptr Tile
            collisionOn=false;
            gp.cChecker.checkTile(this);//avem param this ca verif cu playerul daca se face coliziunea

            //Verif coliziunea ptr Obj
            int objIndex=gp.cChecker.checkObject(this,true);// returnam un index initial aveam val 999
            pickupObject(objIndex);

            //verif coliziunea ptr inamic

            int monsterindex=gp.cChecker.checkEntity(this, gp.monster);
            interactmonster(monsterindex);

            //verif coliziubnea ptr event
            gp.eHandler.checkEvent();//gestionarea evenimentelor în cadrul jocului în funcție de interacțiunile/acțiunile jucătorului.


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
        if(life <= 0)
        {
            gp.gamestate=gp.gameOverState;
            gp.ui.commandNum= -1;// ca sa nu apasam initial din greseala enter si sa dam pe restart dam val fictiva
            gp.playSE(3);
        }

    }

    public void pickupObject(int i)
    {
        if(i!=999)//daca e 999 ins ca n am atins niciun ob
        {
            String objectName=gp.obj[gp.currentMap][i].name;//noi avem declarat name ul in fiecare clasa indiv de ob

            switch (objectName)
            {
                case "Banuti":
                    gp.playSE(1);
                    hasbanuti++;
                    gp.obj[gp.currentMap][i]=null;//stergem ob pe care l am atins
                    gp.ui.showMessage("Congratulation on finding the coin!");

                    break;

                case "Pestisor":
                    if(hasbanuti>0)
                    {
                        gp.playSE(2);
                        gp.obj[gp.currentMap][i]=null;
                        hasbanuti --;
                        gp.ui.showMessage("Yummy! It is your well-deseved fish!");

                    }
                    else
                    {
                        gp.ui.showMessage("You need a coin for eating a fish...");
                    }

                    break;


                case "Licoare":
                    gp.playSE(3);
                    speed+=2;
                    gp.obj[gp.currentMap][i]=null;
                    gp.ui.showMessage("Wow! You are really speed!");
                    break;

                case "House":
                    gp.ui.gameFinished=true;
                    gp.playSE(4);

                case "Cufar":
                    if (level == 1)
                    {
                        gp.eHandler.teleport(1,21,23);
                        level=level+1;

                    }
                    else if (level == 2)
                    {

                        gp.eHandler.teleport(2,30,30);
                        level=level+1;
                        life=6;//resetez maximul de vieti

                    }



                    break;

            }
        }
    }

    public void interactmonster(int i)//I=999 e din collisionchecker
    {
        if(i!=999)//playerul atinge monstrul
        {
            gp.gamestate=gp.dialoguestate;
            gp.monster[gp.currentMap][i].speak();
            if(level==2)
            {
                life-=1 ;
            }

            if(level==3)
            {
                life-=2 ;
            }

            gp.playSE(2);
            //System.out.println("ai dat de un monstru");// cand playerul nostru da de un mostru
        }
    }


    public void draw(Graphics2D g2)//apelam in GamePanel.java
    {
        // g2.setColor(Color.white);
        //g2.fillRect(x,y,gp.tileSize,gp.tileSize);// il facem public ca sa l vedem in panel
        BufferedImage image=null;

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

}

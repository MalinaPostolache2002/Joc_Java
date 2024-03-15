package main;


import DataBase.SQLiteDatabaseCreator;
import data.saveLoad;
import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;
import java.awt.*;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable// subclasaJpanel sau se mai num GameWindow
{
    //setarile ecranului
    final int originalTileSize=16;// 16x16 pixeli ptr caractere
    final int scale=3;//dim de 16x16 o marim de 3 ori pe ecran->48x48

    public final int tileSize=originalTileSize*scale;//48x48
    public  final int maxScreenCol=16;//16 casute in matrice orizontal
    public final int maxScreenRow=12;//12 casute in matrice vertical
    public final int screeenWidth=tileSize * maxScreenCol;//768 pixeli lung fereastra----->48(caracter)*16(casute)
    public final int screeenHeight=tileSize * maxScreenRow;//576 pixeli lat


    //WORLD SETTINGS

    public final int maxWorldCol=50;//matricea noastra de world e de 50 col
    public final int maxWorldRow=50;
    public final int maxMap=3;
    public int currentMap=0;
    public tile.TileManager TileManager;

    public boolean enterterpressed=false;


    int FPS=60;// se schima de 60 de ori pe sec imaginea

    //Sistem
    public CollisionChecker cChecker= new CollisionChecker(this);
    public Asset_Setter aSetter=new Asset_Setter(this);
    TileManager tileM= new TileManager(this );
    KeyHandler keyH=new KeyHandler(this);
    public UI ui=new UI(this);

    public EventHandler eHandler=new EventHandler(this);

    Sound music= new Sound();
    Sound se = new Sound();
    //public UI ui=new UI(this);
    Thread gameThread;//timpul, merge pana opresti si trebuie sa punem sus la fctie Runnable
    saveLoad saveload= new saveLoad(this);


    //Entity+OBJ
    public Player player=new Player(this,keyH);// apelam cu this ca e vb de fctia GP
    public SuperObject obj[][]=new SuperObject[maxMap][30];//putem avea 10 ob in ac timp, nu 10 in tot jocul

    public Entity monster[][]= new Entity[maxMap][30];// putem avea 20 monstri in ac timp
    //ArrayList<Entity> entityList= new ArrayList<>();//punem totul aici si desenam entity in fctie de worldY value

    //GAME STATE
    public SQLiteDatabaseCreator  databaseCreator= new SQLiteDatabaseCreator(ui);
    public int gamestate;
    public final int titleState=0;
    public final int playstate=1;
    public final int pausestate=2;
    public final int dialoguestate=3;
    public final int characterstate=4;//cand afisam pe ecran datele curente
    public final int optionstate=5;//ptr music volume, se
    public final int gameOverState=6;


    private static GamePanel singleton=new GamePanel();

    public static GamePanel getGamePanel()//avem un sing GP
    {
        return singleton;
    }


    private GamePanel()//constructor
    {
        databaseCreator.TIMER(this);
        this.setPreferredSize(new Dimension( screeenWidth, screeenHeight));//dim ecranului si e aia calculata de noi
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);//performanta mai buna
        this.addKeyListener(keyH);
        this.setFocusable(true);//focusata pe primirea lui keyH
    }

    public void setupGame()
    {
        aSetter.setObject();//ca sa punem pe viitor alte setup uri am creat asa
        aSetter.setMonster();
       // playMusic(0);//nu vr muz la titlestate
        gamestate=titleState;
    }

    public void retry()
    {
        player.setDefaulPosition();//poz de inceput
        player.restorelife();// full viata
        eHandler.teleport(currentMap,25,25);

    }



    public void startGameThread()// thread ul e firul de executie
    {
        gameThread=new Thread(this);//this=this class(GamePanel)
        gameThread.start();
    }


    @Override
    public void run()//daca punem runnable se face automat si cum apelam thread ul se face trimitire aici
    {
        double drawInterval=1000000000/FPS;//1 sec/FPS in nanosecunde
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;
        while(gameThread!=null)//1.update info   2.deseneaza ecranu cu aj la update inf
        {
            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;//ins cat de mult timp a trecut
            timer+=currentTime-lastTime;//timpul care a trecut
            lastTime=currentTime;

            if(delta>1)//inca se mai poate desena
            {
                update();
                repaint();
                delta--;
                drawCount++;

            }
            if(timer>1000000000)//cand a trecut timpul
            {
                //System.out.println("FPS"+drawCount);
                drawCount=0;
                timer=0;
            }
        }
    }
    public void update()//update info
    {

        if (gamestate==playstate)
        {
            player.update();//fctie din Player.java

            for(int i=0; i< monster[1].length;i++)//a doua dim din monster ne trb ptr dim
            {
                if (monster[currentMap][i]!=null)
                {
                    monster[currentMap][i].update();
                }
            }
        }
    }
    public void paintComponent(Graphics g)//deseneaza info, graphics=e ca un creion
    {
        super.paintComponent(g);//se face mereu cand exista fctia asta si super e variabila a Java ului
        Graphics2D g2=(Graphics2D) g;// are mai multe functiii, de aceea convertim

        //Tile
        tileM.draw(g2);


        //Object
        for(int i=0;i<obj[1].length;i++)// la obj nu putem apela ca la tile si player
        {                            // avem un vector declarat mai sus si desenam daca luam elem cu elem si nu e nul
            if(obj[currentMap][i]!=null)
            {
                obj[currentMap][i].draw(g2,this);
            }
        }
        for(int i=0;i<monster[1].length;i++)
        {
            if(monster[currentMap][i]!=null)
            {
                monster[currentMap][i].draw(g2);
            }
        }

        //Player
        player.draw(g2);//fctie din Player.java

        //UI
        ui.draw(g2);


        g2.dispose();//salveaza memorie

    }


    public void playMusic(int i)
    {
        music.SetFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic( )
    {
        music.stop();
    }

    public void playSE(int i)//sound effect
    {
        se.SetFile(i);
        se.play(); //nu avem nev de loop ca sound effect ul e f scurt
    }



}
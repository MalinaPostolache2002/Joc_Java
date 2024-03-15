package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNumber;

    public TileManager(GamePanel gp)
    {
        this.gp=gp;
        tile = new Tile[10];
        mapTileNumber= new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];//val din GamePanel ptr mapa world de 50x50
        getTileImage();
        loadMap("/maps/world01.txt",0);// cand vrem sa schimbam mapa doar apelam dif de aici
        loadMap("/maps/world02.txt",1);
        loadMap("/maps/world03.txt",2);
    }
    public void getTileImage()
    {
        try{
            tile[0]=new Tile();
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

            tile[1]=new Tile();
            tile[1].image= ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision=true;

            tile[2]=new Tile();
            tile[2].image= ImageIO.read(getClass().getResourceAsStream("/tiles/waternormala.png"));


            tile[3]=new Tile();
            tile[3].image= ImageIO.read(getClass().getResourceAsStream("/tiles/ice.png"));
            tile[3].collision=true;

            tile[4]=new Tile();
            tile[4].image= ImageIO.read(getClass().getResourceAsStream("/tiles/ice2.png"));
            tile[4].collision=true;

            tile[5]=new Tile();
            tile[5].image= ImageIO.read(getClass().getResourceAsStream("/tiles/ice3.png"));
            tile[5].collision=true;



        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String FilePath, int map)
    {
        try{
            InputStream is = getClass().getResourceAsStream(FilePath);//importam map.txt
            BufferedReader br = new BufferedReader(new InputStreamReader((is)));//citire ce e in map.txt
            int col=0;
            int row=0;
            while (col<gp.maxWorldCol && row< gp.maxWorldRow)
            {
                String line = br.readLine(); //citeste o sing linie si  o pune in string line
                while (col<gp.maxWorldCol)
                {
                    String[] numbers = line.split(" "); //da unu cate unu
                    int num = Integer.parseInt(numbers[col]); //convertim din string in int (integer)
                    mapTileNumber[map][col][row] = num;
                    col++;
                }
                if (col== gp.maxWorldCol)
                {
                    col=0;
                    row++;
                }
            }
            br.close();
        }

        catch(IOException e)
        {
        }
    }

    public void draw(Graphics2D g2)//aici facem Camera
    {
        int worldcol=0;
        int worldrow=0;


        while(worldcol<gp.maxWorldCol && worldrow <gp.maxWorldRow)
        {
            int tileNum=mapTileNumber[gp.currentMap][worldcol][worldrow];

            int worldX = worldcol*gp.tileSize;//poz in mapa
            int worldY = worldrow*gp.tileSize;
            int screenX = worldX-gp.player.worldX + gp.player.screenX;//unde pe ecran o desenam
            int screenY =  worldY-gp.player.worldY + gp.player.screenY;

            /*if (worldX> gp.player.worldX - gp.player.screenX &&
                    worldX< gp.player.worldX + gp.player.screenX &&
                    worldY> gp.player.worldY -gp.player.screenY &&
                    worldY < gp.player.worldY + gp.player.screenY) // desenam doar ce se vede pe ecran
            {*/
            g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize, gp.tileSize, null);//prima data deseneaza tile ul la 0 0
            worldcol++;//desenam toate elem de pe prima linie pana aj la finalul col 50




            if(worldcol== gp.maxWorldCol)//daca aj la ullt col, 16
            {
                worldcol=0;//resetam

                worldrow++;//crestem linia

            }
        }



    }

}
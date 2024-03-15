package main;

import inamic.enemy;
import object.*;

public class Asset_Setter {
    GamePanel gp;
    public Asset_Setter(GamePanel gp)
    {
        this.gp = gp;
    }
    public void setObject()
    {
        //////MAPA 1 !!!!!!!!
        int mapnum=0;
        gp.obj[mapnum][8] = new OBJ_Licoare();
        gp.obj[mapnum][8].worldX = 19 * gp.tileSize;
        gp.obj[mapnum][8].worldY = 25 * gp.tileSize;

        gp.obj[mapnum][9] = new OBJ_Cufar();
        gp.obj[mapnum][9].worldX = 24 * gp.tileSize;
        gp.obj[mapnum][9].worldY = 45 * gp.tileSize;

        gp.obj[mapnum][10] = new OBJ_Banut();
        gp.obj[mapnum][10].worldX = 44 * gp.tileSize;//llungime
        gp.obj[mapnum][10].worldY = 2 * gp.tileSize;//latime

        gp.obj[mapnum][11] = new OBJ_Banut();
        gp.obj[mapnum][11].worldX = 8 * gp.tileSize;
        gp.obj[mapnum][11].worldY = 10 * gp.tileSize;

        gp.obj[mapnum][12] = new OBJ_Banut();
        gp.obj[mapnum][12].worldX = 13 * gp.tileSize;
        gp.obj[mapnum][12].worldY = 47 * gp.tileSize;

        gp.obj[mapnum][13] = new OBJ_Pestisor();
        gp.obj[mapnum][13].worldX = 24 * gp.tileSize;
        gp.obj[mapnum][13].worldY = 44 * gp.tileSize;

        gp.obj[mapnum][14] = new OBJ_Pestisor();
        gp.obj[mapnum][14].worldX = 24 * gp.tileSize;
        gp.obj[mapnum][14].worldY = 43 * gp.tileSize;

        gp.obj[mapnum][15] = new OBJ_Pestisor();
        gp.obj[mapnum][15].worldX = 24 * gp.tileSize;
        gp.obj[mapnum][15].worldY = 42 * gp.tileSize;



        //////MAPA 2 !!!!!!!!
         mapnum=1;

        gp.obj[mapnum][0] = new OBJ_Banut();
        gp.obj[mapnum][0].worldX = 41 * gp.tileSize;//col 23
        gp.obj[mapnum][0].worldY = 43 * gp.tileSize;//linia 7

        gp.obj[mapnum][1] = new OBJ_Banut();
        gp.obj[mapnum][1].worldX = 8 * gp.tileSize;
        gp.obj[mapnum][1].worldY = 10 * gp.tileSize;

        gp.obj[mapnum][2] = new OBJ_Banut();
        gp.obj[mapnum][2].worldX = 45 * gp.tileSize;
        gp.obj[mapnum][2].worldY = 8 * gp.tileSize;

        gp.obj[mapnum][3] = new OBJ_Pestisor();
        gp.obj[mapnum][3].worldX = 23 * gp.tileSize;
        gp.obj[mapnum][3].worldY = 7 * gp.tileSize;

        gp.obj[mapnum][4] = new OBJ_Pestisor();
        gp.obj[mapnum][4].worldX = 23 * gp.tileSize;
        gp.obj[mapnum][4].worldY = 6 * gp.tileSize;

        gp.obj[mapnum][5] = new OBJ_Pestisor();
        gp.obj[mapnum][5].worldX = 23 * gp.tileSize;
        gp.obj[mapnum][5].worldY = 8 * gp.tileSize;


        gp.obj[mapnum][6] = new OBJ_Cufar();
        gp.obj[mapnum][6].worldX = 23 * gp.tileSize;
        gp.obj[mapnum][6].worldY = 5 * gp.tileSize;

        gp.obj[mapnum][7] = new OBJ_Licoare();
        gp.obj[mapnum][7].worldX = 19 * gp.tileSize;
        gp.obj[mapnum][7].worldY = 20 * gp.tileSize;



        //////MAPA 3 !!!!!!!!
        mapnum=2;
        gp.obj[mapnum][16] = new OBJ_Banut();
        gp.obj[mapnum][16].worldX = 21 * gp.tileSize;//col 23
        gp.obj[mapnum][16].worldY = 45 * gp.tileSize;//linia 7

        gp.obj[mapnum][17] = new OBJ_Banut();
        gp.obj[mapnum][17].worldX = 45 * gp.tileSize;// lungime
        gp.obj[mapnum][17].worldY = 5 * gp.tileSize;//latime

        gp.obj[mapnum][18] = new OBJ_Banut();
        gp.obj[mapnum][18].worldX = 19 * gp.tileSize;
        gp.obj[mapnum][18].worldY = 36 * gp.tileSize;

        gp.obj[mapnum][19] = new OBJ_Pestisor();
        gp.obj[mapnum][19].worldX = 23 * gp.tileSize;
        gp.obj[mapnum][19].worldY = 17 * gp.tileSize;

        gp.obj[mapnum][20] = new OBJ_Pestisor();
        gp.obj[mapnum][20].worldX = 23 * gp.tileSize;
        gp.obj[mapnum][20].worldY = 18 * gp.tileSize;

        gp.obj[mapnum][21] = new OBJ_Pestisor();
        gp.obj[mapnum][21].worldX = 23 * gp.tileSize;
        gp.obj[mapnum][21].worldY = 16 * gp.tileSize;

        gp.obj[mapnum][22] = new OBJ_House();
        gp.obj[mapnum][22].worldX = 23 * gp.tileSize;//lung
        gp.obj[mapnum][22].worldY = 15 * gp.tileSize;//latime

        gp.obj[mapnum][23] = new OBJ_Licoare();
        gp.obj[mapnum][23].worldX = 11 * gp.tileSize;
        gp.obj[mapnum][23].worldY = 4 * gp.tileSize;




    }

    public void setMonster()
    {

        ////////////MAPA 2!!!!!!!!!!
        int mapnum=1;
        gp.monster[mapnum][0]= new enemy(gp);
        gp.monster[mapnum][0].worldX=gp.tileSize*24;//lungime
        gp.monster[mapnum][0].worldY=gp.tileSize*8;//latime


        gp.monster[mapnum][1]= new enemy(gp);
        gp.monster[mapnum][1].worldX=gp.tileSize*40;
        gp.monster[mapnum][1].worldY=gp.tileSize*6;

        gp.monster[mapnum][2]= new enemy(gp);
        gp.monster[mapnum][2].worldX=gp.tileSize*38;
        gp.monster[mapnum][2].worldY=gp.tileSize*43;

        gp.monster[mapnum][15]= new enemy(gp);
        gp.monster[mapnum][15].worldX=gp.tileSize*19;
        gp.monster[mapnum][15].worldY=gp.tileSize*11;

        gp.monster[mapnum][3]= new enemy(gp);
        gp.monster[mapnum][3].worldX=gp.tileSize*14;
        gp.monster[mapnum][3].worldY=gp.tileSize*15;

        gp.monster[mapnum][4]= new enemy(gp);
        gp.monster[mapnum][4].worldX=gp.tileSize*30;
        gp.monster[mapnum][4].worldY=gp.tileSize*30;

        gp.monster[mapnum][5]= new enemy(gp);
        gp.monster[mapnum][5].worldX=gp.tileSize*25;
        gp.monster[mapnum][5].worldY=gp.tileSize*25;

        gp.monster[mapnum][6]= new enemy(gp);
        gp.monster[mapnum][6].worldX=gp.tileSize*7;
        gp.monster[mapnum][6].worldY=gp.tileSize*45;



        ////////////MAPA 3!!!!!!!!!!
        mapnum=2;
        gp.monster[mapnum][7]= new enemy(gp);
        gp.monster[mapnum][7].worldX=gp.tileSize*2;//lungime
        gp.monster[mapnum][7].worldY=gp.tileSize*8;//latime


        gp.monster[mapnum][8]= new enemy(gp);
        gp.monster[mapnum][8].worldX=gp.tileSize*17;
        gp.monster[mapnum][8].worldY=gp.tileSize*8;

        gp.monster[mapnum][9]= new enemy(gp);
        gp.monster[mapnum][9].worldX=gp.tileSize*9;
        gp.monster[mapnum][9].worldY=gp.tileSize*25;


        gp.monster[mapnum][11]= new enemy(gp);
        gp.monster[mapnum][11].worldX=gp.tileSize*18;
        gp.monster[mapnum][11].worldY=gp.tileSize*42;

        gp.monster[mapnum][12]= new enemy(gp);
        gp.monster[mapnum][12].worldX=gp.tileSize*21;
        gp.monster[mapnum][12].worldY=gp.tileSize*6;

        gp.monster[mapnum][13]= new enemy(gp);
        gp.monster[mapnum][13].worldX=gp.tileSize*38;//lungime
        gp.monster[mapnum][13].worldY=gp.tileSize*10;//latime

        gp.monster[mapnum][14]= new enemy(gp);
        gp.monster[mapnum][14].worldX=gp.tileSize*7;
        gp.monster[mapnum][14].worldY=gp.tileSize*37;


        gp.monster[mapnum][16]= new enemy(gp);
        gp.monster[mapnum][16].worldX=gp.tileSize*27;
        gp.monster[mapnum][16].worldY=gp.tileSize*18;


        gp.monster[mapnum][17]= new enemy(gp);
        gp.monster[mapnum][17].worldX=gp.tileSize*20;
        gp.monster[mapnum][17].worldY=gp.tileSize*43;

        gp.monster[mapnum][18]= new enemy(gp);
        gp.monster[mapnum][18].worldX=gp.tileSize*38;
        gp.monster[mapnum][18].worldY=gp.tileSize*43;

        gp.monster[mapnum][19]= new enemy(gp);
        gp.monster[mapnum][19].worldX=gp.tileSize*44;
        gp.monster[mapnum][19].worldY=gp.tileSize*30;

        gp.monster[mapnum][20]= new enemy(gp);
        gp.monster[mapnum][20].worldX=gp.tileSize*10;
        gp.monster[mapnum][20].worldY=gp.tileSize*18;





    }

}






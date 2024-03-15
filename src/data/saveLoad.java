package data;

import main.GamePanel;

import java.io.*;

public class saveLoad {
    GamePanel gp;
    public saveLoad(GamePanel gp)
    {
        this.gp=gp;
    }

    public void save()// facem saveul ptr teleport
    {
        try {
            ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(new File("save.data")));

            dataStorage ds= new dataStorage();
            ds.level=gp.player.level;
            ds.maxLife=gp.player.maxLife;
            ds.life=gp.player.life;
            ds.currentMap=gp.currentMap;
            ds.hasbanuti=gp.player.hasbanuti;
            ds.worldy=gp.player.worldY;
            ds.worldx=gp.player.worldX;
            ds.coin=gp.player.coin;
            ds.pestisori=gp.player.pestisori;
            ds.speed=gp.player.speed;

            //OBJ

            ds.mapobj=new String[gp.maxMap][gp.obj[1].length];
            ds.mapobj_worldx=new int[gp.maxMap][gp.obj[1].length];
            ds.mapobj_worldy=new int[gp.maxMap][gp.obj[1].length];

            for(int mapNum=0; mapNum<gp.maxMap;mapNum++)
            {
                for (int i=0;i< gp.obj[1].length; i++)
                {
                    if(gp.obj[mapNum][i]== null)
                    {
                        ds.mapobj[mapNum][i]="nu";
                    }
                    else// avem obiect
                    {
                        ds.mapobj[mapNum][i]= gp.obj[mapNum][i].name;
                        ds.mapobj_worldx[mapNum][i]=gp.obj[mapNum][i].worldX;
                        ds.mapobj_worldy[mapNum][i]=gp.obj[mapNum][i].worldY;

                    }
                }
            }


            //write obj
            oos.writeObject(ds);
        }catch (Exception e)
        {
            System.out.println("save exception");
        }

    }
    public void load()//facem load ul in KeyHandler ptr al doilea buton
    {
        try {
            ObjectInputStream ois= new ObjectInputStream(new FileInputStream(new File("save.data")));

            //read
            dataStorage ds= (dataStorage)ois .readObject();
            gp.player.level=ds.level;
            gp.player.maxLife=ds.maxLife;
            gp.player.life=ds.life;
            gp.currentMap=ds.currentMap;
            gp.player.hasbanuti=ds.hasbanuti;
            gp.player.worldY=ds.worldy;
            gp.player.worldX=ds.worldx;
            gp.player.coin=ds.coin;
            gp.player.pestisori=ds.pestisori;
            gp.player.speed=ds.speed;



            //OBJ

            for(int mapNum=0; mapNum<gp.maxMap;mapNum++)
            {
                for (int i=0;i< gp.obj[1].length; i++)
                {
                    if(ds.mapobj[mapNum][i].equals("nu"))
                    {
                        gp.obj[mapNum][i]= null;
                    }
                    else// avem obiect
                    {
                       gp.obj[mapNum][i].worldX=ds.mapobj_worldx[mapNum][i];
                        gp.obj[mapNum][i].worldY=ds.mapobj_worldy[mapNum][i];

                    }
                }
            }


        }catch (Exception e)
        {
            System.out.println("load exception");
        }

    }


}

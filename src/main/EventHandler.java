package main;

public class EventHandler {
    GamePanel gp;
    EventRect[][][] eventRect;//zonele de evenimente din joc.
    int previousEventX,previousEventY;//poziția anterioară a even, verifica dacă jucătorul a depășit distanța necesară pentru a declanșa un eveniment.
    boolean cantouchevent=true;//pecifică dacă jucătorul poate declanșa un eveniment
    public EventHandler(GamePanel gp)
    {
        this.gp=gp;
        eventRect= new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];//obiecte EventRect pentru fiecare combinație posibilă de hărți, coloane și rânduri.
        int map=0;
        int col=0;
        int row=0;
        while (map< gp.maxMap && col< gp.maxWorldCol && row< gp.maxWorldRow)
        {
            eventRect[map][col][row]=new EventRect();
            eventRect[map][col][row].x=48;// dimensiunea unui tile
            eventRect[map][col][row].y=48;
            eventRect[map][col][row].width=2;
            eventRect[map][col][row].height=2;
            eventRect[map][col][row].eventRectDefaultX= eventRect[map][col][row].x;//COPII
            eventRect[map][col][row].eventRectDefaultY= eventRect[map][col][row].y;


            col++;
            if(col== gp.maxWorldRow)
            {
                col=0;
                row++;
            }

            if(row== gp.maxWorldRow)
            {
                row=0;
                map++;
            }
        }
    }

    public void checkEvent()//Verifică dacă jucătorul a depășit distanța necesară pentru a declanșa un eveniment
    {
        int xdistance=Math.abs(gp.player.worldX -previousEventX);
        int ydistance=Math.abs(gp.player.worldY -previousEventY);//Calculează distanța pe axele X și Y între poziția jucătorului și poziția anterioară a evenimentului
        int distance=Math.max(xdistance,ydistance);//distanța maximă
        if (distance>gp.tileSize)//jucătorul a depășit distanța necesară pentru a declanșa evenimentul.

        {
            cantouchevent=true;// indică faptul că evenimentul poate fi declanșat
        }
        gp.saveload.save();//salveaza informații despre locația jucătorului pe hartă și ce a colectat acesta.


    }


    public void teleport(int map, int col, int row)
    {
        ///System.out.println("INtra in teleport");

        gp.currentMap=map;
        gp.player.worldX=gp.tileSize *col;
        gp.player.worldY=gp.tileSize *row;

        previousEventX=gp.player.worldX;
        previousEventY=gp.player.worldY;

        cantouchevent=false;//jucătorul nu poate declanșa un eveniment

        gp.saveload.save();//SALVEAZA MAPA/NIVELUL CURENT
    }
}

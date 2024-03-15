package data;

import java.io.Serializable;

public class dataStorage implements Serializable// ac clasa poate fi scrisa si citita
{
    //punem toate varibilele care vrem sa fie load si salvate
    //PLAYER STATUS
    int level;
    int maxLife;
    int life;
    int currentMap;//ptr a schimba mapa inn fctie de nivel
    int hasbanuti;
    int worldy;
    int worldx;
    int coin;
    int pestisori;
    int speed;

    //OBJECT ON MAP
    String mapobj[][];//nume
    int mapobj_worldx[][];//coord x
    int mapobj_worldy[][];//coord y



}

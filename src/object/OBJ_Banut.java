package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Banut extends SuperObject {

    public OBJ_Banut()
    {

        name= "Banuti";
        try{

            image= ImageIO.read(getClass().getResourceAsStream("/objects/banut.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }


}

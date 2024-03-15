package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Pestisor extends SuperObject{

    public OBJ_Pestisor()
    {
        name= "Pestisor";
        try{

            image= ImageIO.read(getClass().getResourceAsStream("/objects/peste.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision=true;//facem solizi pestisorii daca nu avem banuti ptr ei
    }
}

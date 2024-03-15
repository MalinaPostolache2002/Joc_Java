package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;


public class OBJ_Licoare extends SuperObject{

    public OBJ_Licoare( )
    {

        name= "Licoare";
        try{

            image= ImageIO.read(getClass().getResourceAsStream("/objects/speed.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

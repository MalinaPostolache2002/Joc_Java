package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Cufar extends SuperObject {

    public OBJ_Cufar() {

        name = "Cufar";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

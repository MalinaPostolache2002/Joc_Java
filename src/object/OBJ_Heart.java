package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject
{
    public OBJ_Heart() {

        name = "Heart";
        try {

            image1 = ImageIO.read(getClass().getResourceAsStream("/objects/inima1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/inima2.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/inima3.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_House extends SuperObject
{
    public OBJ_House() {

        name = "House";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/house.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

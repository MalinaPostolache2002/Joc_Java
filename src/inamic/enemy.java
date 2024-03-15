package inamic;
import entity.Entity;
import entity.Player;
import main.GamePanel;

import java.util.Random;

public class enemy extends Entity {
    //GamePanel gp;----declata gamepanel in entity
    // private String[] dialogues;


    public enemy(GamePanel gp)
    {
        super(gp);
        name = "inamic";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up = Player.LoadImage("res/inamic/inamicup.png");
        down = Player.LoadImage("res/inamic/inamicdown.png");
        right = Player.LoadImage("res/inamic/inamicright.png");
        left = Player.LoadImage("res/inamic/inamicleft.png");
    }

    public void setDialogue() {
        dialogues[0] = "You should be careful who you hit.";
        dialogues[1] = "For your own good, avoid me.";
        dialogues[2] = "From now, you will collect \nthe coins much harder.";

    }


    public void setaction() {
        actionLockcounter++;
        if (actionLockcounter == 150)// vom alege o directie, 120=2 secunde
        {
            Random random = new Random();// se alege random o directie
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }

            if (i > 25 && i <= 50) {
                direction = "down";

            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockcounter = 0;


        }
    }

    public void speak() //fctia e scrisa+declarata in Entity si are la baza dialogue state
    {
        super.speak();
    }
}

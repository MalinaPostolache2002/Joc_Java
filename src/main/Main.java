package main;

import javax.swing.JFrame;
import java.io.IOException;
public class Main {
    public static void main(String[] args)
    {
        JFrame window=new JFrame();//cream fereastra
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//inchidem fereastra
        window.setResizable(false);//nu putem sa i schimbam dim
        window.setTitle("Go,Zglobi");


        GamePanel gamePanel= GamePanel.getGamePanel();//singleton metoda apelata
        window.add(gamePanel);//adaugam set ecranului la fereastra creata

        window.pack();//setare sa vedem ecranu complet

        window.setLocationRelativeTo(null);//fereastra apare pe mijlocull ecranului
        window.setVisible(true);// o putem vedea

        gamePanel.setupGame();
        gamePanel.startGameThread();




    }
}
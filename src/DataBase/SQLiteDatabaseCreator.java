package DataBase;

import entity.Player;
import main.GamePanel;
import main.UI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.* ;
public class SQLiteDatabaseCreator {
    GamePanel gp;
    UI ui;
    public SQLiteDatabaseCreator(UI ui)
    {
        this.ui=ui;
    }


    public void TIMER(GamePanel gp)
    {
        this.gp = gp;

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");//conexiunea la baza de date SQLite
            c = DriverManager.getConnection("jdbc:sqlite:TIMERDB.db");//Numele bazei de date este "TIMERDB.db".
            //Se utilizează clasa "DriverManager" pentru a obține o conexiune la baza de date SQLite.

            System.out.println("Conexiunea la baza de date a fost stabilită cu succes.");

            stmt = c.createStatement();//Obiectul permite executarea instrucțiunilor SQL precum crearea de tabele, inserarea de date,
            String sql = "CREATE TABLE IF NOT EXISTS JOC (TIMER INT PRIMARY KEY NOT NULL)";
            //Tabelul "JOC" are o coloană numită "TIMER" de tip întreg

            stmt.executeUpdate(sql);

            stmt.executeUpdate(sql);// executa instrucțiuni SQL care modifică baza de date

            System.out.println("Tabela 'TIMER' a fost creată cu succes.");


            sql = "INSERT INTO TIMER (TIMER) " +
                    "VALUES("+ ui.playtime+ ");";
            stmt.executeUpdate(sql);
            stmt.close();//Se închide obiectul "Statement" și conexiunea la baza de date.
            c.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Driverul SQLite JDBC nu a fost găsit.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Eroare la conectarea la baza de date sau la crearea tabelei.");
            e.printStackTrace();
        }
    }
}





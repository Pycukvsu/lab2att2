package company;

import company.dbutils.CRUDUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("C:/Users/User/Documents/lab2crudbd-main/untitled9/players.json");
        Player[] players = (objectMapper.readValue(file, Player[].class));
        CRUDUtils.savePlayer(players);
        JsonUpdate.ReadToMap(players);

        System.out.println("Что хотите сделать?");
        System.out.println("1 - Добавить плеера | 2 - Удалить плеера | 3 - Изменить данные плеера | 4 - выгрузить данные из БД");
        int a = scanner().nextInt();
        if (a == 1) {
            Player player = CRUDUtils.addPlayer();
            JsonUpdate.addPlayerInMap(player);

            System.out.println("Данные из БД: ");
            List<Player> dop = CRUDUtils.getPlayerData();
            Iterator itr = dop.iterator();
            while (itr.hasNext()) {
                System.out.println(itr.next());
            }
        } else if (a == 2) {
            int playerId = CRUDUtils.deletePlayer();
            JsonUpdate.deletePlayerFromMap(playerId);
            System.out.println("Данные из БД: ");
            List<Player> dop = CRUDUtils.getPlayerData();
            Iterator itr = dop.iterator();
            while (itr.hasNext()) {
                System.out.println(itr.next());
            }
        } else if (a == 3) {
            CRUDUtils.updatePlayer();
            System.out.println("Данные из БД: ");
            List<Player> dop = CRUDUtils.getPlayerData();
            /*Iterator itr = dop.iterator();
            while (itr.hasNext()) {
                System.out.println(itr.next());
            }*/
        } else {
            System.out.println("Данные из БД: ");
            List<Player> dop = CRUDUtils.getPlayerData();
            Iterator itr = dop.iterator();
            while (itr.hasNext()) {
                System.out.println(itr.next());
            }
        }
        JsonUpdate.JsonInFile(new File("C:/Users/User/Documents/lab2crudbd-main/untitled9/players.json"), objectMapper);
    }

    public static Scanner scanner(){
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }
}
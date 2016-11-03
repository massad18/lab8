package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    Hashtable<String, SoccerPlayer> database = new Hashtable<>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        if (database.containsKey(firstName+"##"+lastName)) {
            Log.i("add player", firstName+" "+lastName+" ERROR");
            return false;
        }
        else {
            SoccerPlayer player = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
            database.put(firstName+"##"+lastName, player);
            Log.i("add player", firstName+" "+lastName+" has been added");
            return true;
        }
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        if (database.containsKey(firstName + "##" + lastName)) {
            database.remove(firstName + "##" + lastName);
            Log.i("remove player", firstName+" "+lastName+" has been removed");
            return true;
        } else {
            Log.i("remove player", firstName+" "+lastName+" ERROR");
            return false;
        }
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        return database.get(firstName+"##"+lastName);
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        if (database.containsKey(firstName + "##" + lastName)) {
            database.get(firstName + "##" + lastName).bumpGoals();
            Log.i("bumpGoals", "+");
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        if (database.containsKey(firstName + "##" + lastName)) {
            database.get(firstName + "##" + lastName).bumpAssists();
            Log.i("bumpAssists", "+");
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        if (database.containsKey(firstName + "##" + lastName)) {
            database.get(firstName + "##" + lastName).bumpShots();
            Log.i("bumpShots", "+");
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        if (database.containsKey(firstName + "##" + lastName)) {
            database.get(firstName + "##" + lastName).bumpSaves();
            Log.i("bumpSaves", "+");
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        if (database.containsKey(firstName + "##" + lastName)) {
            database.get(firstName + "##" + lastName).bumpFouls();
            Log.i("bumpFouls", "+");
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        if (database.containsKey(firstName + "##" + lastName)) {
            database.get(firstName + "##" + lastName).bumpYellowCards();
            Log.i("bumpYellowCards", "+");
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        if (database.containsKey(firstName + "##" + lastName)) {
            database.get(firstName + "##" + lastName).bumpRedCards();
            Log.i("bumpRedCards", "+");
            return true;
        } else {
            return false;
        }
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        if (teamName == null) {
            return database.size();
        }
        else {
            int num = 0;
            Enumeration <SoccerPlayer> players = database.elements();
            while (players.hasMoreElements()) {
                if (players.nextElement().getTeamName().equals(teamName)) {
                    num++;
                }
            }
            return num;
        }
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        if (teamName == null) {
            int num = 0;
            Enumeration <SoccerPlayer> players = database.elements();
            if (!players.hasMoreElements()) {
                return null;
            }
            else {
                SoccerPlayer player = players.nextElement();
                while (players.hasMoreElements()) {
                    num++;
                    if (num == idx+1) {
                        break;
                    }
                    player = players.nextElement();
                }
                return player;
            }
        }
        else {
            int num = 0;
            Enumeration <SoccerPlayer> players = database.elements();
            if (!players.hasMoreElements()) {
                return null;
            }
            else {
                SoccerPlayer player = players.nextElement();
                while (players.hasMoreElements()) {
                    if (player.getTeamName().equals(teamName)) {
                        num++;
                        if (num == idx+1) {
                            break;
                        }
                    }
                    player = players.nextElement();
                }
                return player;
            }
        }
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        Scanner scanner;
        Log.i("readData", "Method Called");
        try {
            scanner = new Scanner(file);
        } catch(FileNotFoundException e) {
            Log.i("readData", "File Not Found");
            return false;
        }
        while (scanner.hasNextLine()) {
            Log.i("readData", "Data Read");
            String firstName = scanner.nextLine();
            if (firstName.equals("")) {
                firstName = scanner.nextLine();
            }
            Log.i("sample", firstName);
            String lastName = scanner.nextLine();
            String teamName = scanner.nextLine();
            int uniform = scanner.nextInt();
            int goals = scanner.nextInt();
            int assists = scanner.nextInt();
            int shots = scanner.nextInt();
            int saves = scanner.nextInt();
            int fouls = scanner.nextInt();
            int yellow = scanner.nextInt();
            int red = scanner.nextInt();

            SoccerPlayer player = new SoccerPlayer(firstName, lastName, uniform, teamName);
            database.put(firstName+"##"+lastName, player);
            while (player.getGoals() != goals) {
                player.bumpGoals();
            }
            while (player.getAssists() != assists) {
                player.bumpAssists();
            }
            while (player.getShots() != shots) {
                player.bumpShots();
            }
            while (player.getSaves() != saves) {
                player.bumpSaves();
            }
            while (player.getFouls() != fouls) {
                player.bumpFouls();
            }
            while (player.getYellowCards() != yellow) {
                player.bumpYellowCards();
            }
            while (player.getRedCards() != red) {
                player.bumpRedCards();
            }
        }
        scanner.close();
        return true;
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        int num = 1;
        PrintWriter pw;
        try {
            pw = new PrintWriter(file);
        }catch(FileNotFoundException e){
            Log.i("writeData", "File Not Found");
            return false;
        }
        Enumeration <SoccerPlayer> players = database.elements();
        while (players.hasMoreElements()) {
            SoccerPlayer player = players.nextElement();
            pw.println(logString(player.getFirstName()));
            pw.println(logString(player.getLastName()));
            pw.println(logString("" + player.getTeamName()));
            pw.println(logString("" + player.getUniform()));
            pw.println(logString("" + player.getGoals()));
            pw.println(logString("" + player.getAssists()));
            pw.println(logString("" + player.getShots()));
            pw.println(logString("" + player.getSaves()));
            pw.println(logString("" + player.getFouls()));
            pw.println(logString("" + player.getYellowCards()));
            if (num < database.size()) {
                pw.println(logString("" + player.getRedCards()));
            }
            else {
                pw.print(logString("" + player.getRedCards()));
            }
            num++;
        }
        pw.close();
        return true;
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        HashSet<String> teamNames = new HashSet<>();
        Enumeration <SoccerPlayer> players = database.elements();
        while (players.hasMoreElements()) {
            SoccerPlayer player = players.nextElement();
            String teamName = player.getTeamName();
            teamNames.add(teamName);
        }
        return teamNames;
    }

}

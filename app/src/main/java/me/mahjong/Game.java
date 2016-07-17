package me.mahjong;

/**
 * Created by liyingjiao on 24/5/16.
 * Game class for each Mahjong game.
 * Each game has 4 players
 */
public class Game {
    private Players[] players = new Players[4];
    private double incr; //let default be 0.1 cents
    private int maxtai;

    //Param: takes in an array of names of 4 players
    Game (String[] p, int maxtai, Double incr) {
        //initialise the players
        for (int i = 0; i < 4; i++) {
            players[i] = new Players(p[i]);
        }
        this.incr = incr;
        this.maxtai = maxtai;
    }

    //returns amount of money player has
    public double getMoney (int playerid) {
        if (playerid < 0 || playerid > 3) {
            return 0;
        } else
            if (players[playerid] != null)
                return players[playerid].getMoney();
        else return 0;
    }

    //returns name of player
    public String getName (int playerid) {
        if (playerid< 0 || playerid > 3) {
            return null;
        } else {
            return players[playerid].getName();
        }
    }

    public void updateGameState(int winnerid, int loserid, boolean zimou, int tai) {
        tai = Math.min(tai, maxtai); //cannot go beyond limit
        if (zimou) {
            //zimo
            for (int i = 0; i < 4; i++) {
                if (i == winnerid)
                    continue;
                else {
                    players[i].pay(incr, tai + 1, players[winnerid]);
                }
            }
        } else {
            //not zimo
            if (loserid == -1 || winnerid == -1) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                if (i == winnerid)
                    continue;
                else if (i == loserid) {
                    players[i].pay(incr, tai + 1, players[winnerid]);
                } else {
                    players[i].pay(incr, tai, players[winnerid]);
                }
            }
        }
    }
}

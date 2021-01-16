/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premierleague;

import java.util.ArrayList;

/**
 *
 * @author ignat
 */
class FootballClub extends SportsClub {
    
    
    int numWin;
    int numDraw;
    int numDefeat;
    int goalsScored;
    int goalsReceived;
    int points;
    int matchesPlayed;
    

    FootballClub(String name, String location) {
        super(name, location);
        numWin = 0;
        numDraw = 0;
        numDefeat = 0;
        goalsScored = 0;
        goalsReceived = 0;
        points = 0;
        matchesPlayed = 0;
    }

    FootballClub(String name, String location, int numWin, int numDraw, int numDefeat, int goalsScored, int goalsReceived) {
        super(name, location);
        this.numWin = numWin;
        this.numDraw = numDraw;
        this.numDefeat = numDefeat;
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
    }

    public int getNumWin() {return numWin;}

    public int getNumDraw() {return numDraw;}

    public int getNumDefeat() {return numDefeat;}

    public int getGoalsScored() {return goalsScored;}

    public int getGoalsReceived() {return goalsReceived;}
    
    public int getPoints() {return points;}
    
    public int getMatchesPlayed() {return matchesPlayed;}
    
    public void incrementWin(){numWin += 1;}
    public void incrementDraw(){numDraw += 1;}
    public void incrementDefeat(){numDefeat += 1;}
    public void incrementMatches() {matchesPlayed += 1;}
     
    public void addGoalsScored(int i){goalsScored += i;}
    public void addGoalsReceived(int i){goalsReceived += i;} 
    public void addPoints(int i) {points += i;}
     
     
     void display() {
        System.out.printf( "%-24s %-20s %-7d %-7d %-7d %-7d %-7d %-11d %-14d %n",
                name, location, points, matchesPlayed, numWin, numDraw, numDefeat, goalsScored, goalsReceived);
    }
   
    
 
}

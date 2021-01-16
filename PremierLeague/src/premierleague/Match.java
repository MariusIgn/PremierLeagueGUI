/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premierleague;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import static premierleague.MatchList.matchArray;
import static premierleague.PLList.array;

/**
 *
 * @author ignat
 */
public final class Match {

    public final LocalDate startDate = LocalDate.of(2020, 9, 12);
    public final LocalDate endDate = LocalDate.of(2021, 5, 23);

    public final long startDays = startDate.toEpochDay();
    public final long endDays = endDate.toEpochDay();

    LocalDate date;
    String clubA;
    String clubB;
    int scoreA;
    int scoreB;

    // Constructor with given variables creats the instance and updates the football clubs
    Match(LocalDate inputDate, String name1, String name2, int score1, int score2) {
        this.date = inputDate;
        this.clubA = name1;
        this.clubB = name2;
        this.scoreA = score1;
        this.scoreB = score2;
        
        updateClubs();
    }
    
    // When variables are not given, a random match is created
    Match(){
        int[] indexes = getIndexes();
        LocalDate randDate = getRandDate();
        
        this.date = randDate;
        this.clubA = array.get(indexes[0]).getName();
        this.clubB = array.get(indexes[1]).getName();
        this.scoreA = getScore();
        this.scoreB = getScore();
        
        updateClubs();
    }

    LocalDate getRandDate() {
        long randomDay = ThreadLocalRandom.current().nextLong(startDays, endDays);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }

    int getScore() {
        int score = (int) (Math.random() * 9);
        return score;
    }
    
    //Generates two different indexes of the matches list
    int[] getIndexes() {

        int index1 = (int) (Math.random() * array.size());
        int index2;

        do {
            index2 = (int) (Math.random() * array.size());
        } while (index1 == index2);

        int[] indexes = {index1, index2};

        return indexes;

    }


    private void updateClubs() {
        int indexA = 0;
        int indexB = 0;

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getName().equals(clubA)) {
                indexA = i;
            } else if (array.get(i).getName().equals(clubB)) {
                indexB = i;
            }
        }

        FootballClub FcA = array.get(indexA);
        FootballClub FcB = array.get(indexB);

        FcA.incrementMatches();
        FcB.incrementMatches();

        FcA.addGoalsReceived(scoreB);
        FcB.addGoalsReceived(scoreA);

        FcA.addGoalsScored(scoreA);
        FcB.addGoalsScored(scoreB);

        if (scoreA > scoreB) {
            FcA.incrementWin();
            FcA.addPoints(3);
            FcB.incrementDefeat();
        } else if (scoreB > scoreA) {
            FcB.incrementWin();
            FcB.addPoints(3);
            FcA.incrementDefeat();
        } else {
            FcA.incrementDraw();
            FcB.incrementDraw();

            FcA.addPoints(1);
            FcB.addPoints(1);
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premierleague;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import static premierleague.PLList.array;

/**
 *
 * @author ignat
 */
public class MatchList {

    static final ArrayList<Match> matchArray = new ArrayList<>();

    void add(Match m) {
        matchArray.add(m);
    }

    void displayTable() {
        System.out.printf("%-15s %-28s %-28s %-18s %-18s %n",
                "Date", "Home Team", "Away Team", "Home Team Score", "Away Team Score");
        
        Collections.sort(matchArray, new MatchComparator());
        
        for (Match m : matchArray) {
            displayTableRow(m);
        }
    }

    void displayTableRow(Match m) {

        System.out.printf("%-15s %-28s %-28s %-18d %-18d %n",
                m.date, m.clubA, m.clubB, m.scoreA, m.scoreB);

    }

}

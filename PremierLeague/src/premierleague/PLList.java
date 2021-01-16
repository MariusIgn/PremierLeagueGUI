/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premierleague;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ignat
 */
class PLList {

    static final ArrayList<FootballClub> array = new ArrayList<>();
    // Array of the names of the clubs
    static final ArrayList<String> nameArray = new ArrayList<>();

    void add(FootballClub next, String name) {

        array.add(next);
        nameArray.add(name);

    }

    void remove(FootballClub next) {
        if (array.size() > 0) {
            array.remove(next);
            nameArray.remove(next);
        }
    }
    
    //Display table of clubs in the console
    void display(int mode) {
        System.out.printf("%-24s %-20s %-7s %-7s %-7s %-7s %-7s %-11s %-14s %n",
                "Name", "Location", "Points", "Matches", "Wins", "Draws", "Defeats", "Goals Scored", "Goals Received");

        sortArray(mode);

        for (FootballClub FC : array) {
            FC.display();
        }
    }

    public final void sortArray(int mode) {
        if (mode == 0) {
            Collections.sort(array, new PointsComparator());
        } else if (mode == 1) {
            Collections.sort(array, new GoalsComparator());
        } else if (mode == 2) {
            Collections.sort(array, new WinsComparator());
        }

    }

    void delete() {

        if (!Empty()) {

            int i = findMatchingClub("Enter the name of the club:  ");

            if (i != -1) {
                System.out.println(array.get(i).getName() + " club relegated");
                array.remove(i);
            }
        }
    }

    void viewStats() {
        if (!Empty()) {

            int i = findMatchingClub("Enter the name of the club:  ");

            if (i != -1) {

                // Add all of the getters!
                System.out.println("Showing statisctics for " + array.get(i).getName() + "\n"
                        + "Wins: " + array.get(i).getNumWin() + "\n"
                        + "Draws: " + array.get(i).getNumDraw() + "\n"
                        + "Defeats: " + array.get(i).getNumDefeat() + "\n"
                        + "Goals scored: " + array.get(i).getGoalsScored() + "\n"
                        + "Goals received: " + array.get(i).getGoalsReceived() + "\n"
                        + "Points: " + array.get(i).getPoints() + "\n"
                        + "Matches played: " + array.get(i).getMatchesPlayed() + "\n");
            }
        }
    }

    int findMatchingClub(String message) {

        boolean match = false;
        int index = -1;

        Scanner input = new Scanner(System.in);

        System.out.print(message);
        String name = input.nextLine();

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getName().equals(name)) {
                match = true;
                index = i;
            }
        }

        if (match) {
            System.out.println("Club found");
        } else {
            System.out.println("No club found");
        }
        return index;
    }

    boolean Empty() {

        boolean empty = true;

        if (array.size() > 0) {
            empty = false;
        } else {
            System.out.println("List is empty.");
        }

        return empty;

    }

}
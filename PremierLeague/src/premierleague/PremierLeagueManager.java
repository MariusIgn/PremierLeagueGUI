/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premierleague;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import static premierleague.MatchList.matchArray;
import static premierleague.PLList.array;
import static premierleague.PLList.nameArray;

/**
 *
 * @author ignat
 */
public class PremierLeagueManager implements LeagueManager {

    public final PLList list = new PLList();
    public final MatchList matches = new MatchList();
    //private static final ArrayList<FootballClub> PLClubs = new ArrayList<>();

    public final LocalDate startDate = LocalDate.of(2020, 9, 12);
    public final LocalDate endDate = LocalDate.of(2021, 5, 23);

    public final long startDays = startDate.toEpochDay();
    public final long endDays = endDate.toEpochDay();
    
    //Next two arrays are used for autmaticly adding these clubs
    static String[] teams = new String[]{"Arsenal", "Aston Villa", "Brighton & Hove Albion", "Burnley", "Chelsea", "Crystal Palace", "Everton", "Fulham",
        "Leeds United", "Leicester City", "Liverpool", "Manchester City", "Manchester United", "Newcastle United",
        "Sheffield United", "Southampton", "Tottenham Hotspur", "West Bromwich Albion", "West Ham United",
        "Wolverhampton Wanderers"};

    static String[] locations = new String[]{"Holloway", "Birmingham", "Brighton", "Burnley", "Fulham", "Selhurst",
        "Walton", "Fulham", "Leeds", "Leicester", "Anfield", "Clayton", "Old Trafford", "Newcastle upon Tyne",
        "Sheffield", "Southampton", "Tottenham", "West Bromwich", "Stratford", "Wolverhampton"};

    public static void main(String[] args) {

        PremierLeagueManager Manager = new PremierLeagueManager();

        //The past data is loaded every time it starts
        Manager.loadData();
        Manager.displayMenu();

        //Scanner set up
        Scanner in = new Scanner(System.in);

        //Variable that stores the choice of the user
        char choice;

        do {

            //Ask for an input
            System.out.println("Enter a command letter, M to show the menu again or Q to quit.\n");

            choice = in.next().toUpperCase().charAt(0);

            //Process input
            switch (choice) {
                case 'K':
                    Manager.getRandomMatch();
                    break;
                case 'G':
                    Manager.openGUI();
                    break;
                case 'P':
                    Manager.displayMatchesTable();
                    break;
                case 'O':
                    Manager.showTableGUI();
                    break;
                case 'M':
                    Manager.displayMenu();
                    break;
                case 'A':
                    System.out.println("Creating new football club...");
                    Manager.createClub();
                    break;
                case 'D':
                    System.out.println("Relegating football club...");
                    Manager.deleteClub();
                    break;
                case 'V':
                    System.out.println("Viewing club statistics...");
                    Manager.viewClubStats();
                    break;
                case 'T':
                    System.out.println("Dispalying Premier League table...");
                    Manager.displayTable();
                    break;
                case 'W':
                    System.out.println("Added all of the clubs");
                    Manager.addAll();
                    break;
                case 'H':
                    System.out.println("Adding a new match");
                    Manager.addMatch();
                    break;
                case 'S':
                    System.out.println("You chose to store the data in a file.");
                    Manager.storeData();
                    break;
                case 'L':
                    System.out.println("You chose to load data from a file.");
                    Manager.loadData();
                    break;
                case 'Q':
                    //Auto-save data
                    Manager.storeData();
                    //Exit message before closing
                    System.out.println("Thank you for using our software.");
                    break;
                default:
                    System.out.println("You entered an invalid choice.\n"
                            + "Please try again.");
            }
            //Program stops one the user selects "Q"
        } while (choice != 'Q');

    }

    public void displayMenu() {

        System.out.println("Welcome to our Premier League Manager software!\n"
                + "These are the different commands you can use:\n"
                + "___________________________________________\n"
                + " A: Create a new football club\n"
                + " D: Relegate a football club\n"
                + " V: View club statistics\n"
                + " T: View Premier League table\n"
                + " S: Store Premier League data into file\n"
                + " L: Load Premier League data from file\n"
                + " G: Open the GUI\n");
    }

    public void createClub() {

        final String name = getString("Enter the name of the club:  ");
        final String location = getString("Enter the location of the club:  ");

        if (!nameArray.contains(name)) {

            list.add(new FootballClub(name, location), name);

            System.out.println("Club " + name + " with location " + location + " added.");
        } else {
            System.out.println("Club is already registered.");
        }
    }

    //Method that checks that the input string only contains A to Z charachters 
    public String getString(String message) {
        String input = null;
        Scanner scan = new Scanner(System.in);

        System.out.println(message);

        if (scan.hasNext("[A-Za-z]*")) {
            input = scan.nextLine();

        } else {
            System.out.println("Please only use charachers A-Z.");
            scan.nextLine();

        }
        return input;
    }

    //Method that insures that the input integer is positive 
    public int getInt(String message) {
        int num = -1;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print(message);
            if (scan.hasNextInt()) {
                num = scan.nextInt();
            } else {
                System.out.println("Please enter a positive number.");
                scan.nextLine();
            }
        } while (num <= 0);

        return num;
    }

    public void deleteClub() {
        list.delete();
    }

    public void viewClubStats() {
        list.viewStats(); //Input for the club name is handeled inside the method
    }

    public void displayTable() {
        list.display(0); // 0 is used to tell the sortArray method to sort the teams by points
    }

    public void addMatch() {
        LocalDate date;
        String name1;
        String name2;
        int score1;
        int score2;

        ArrayList<String> validNames = findTeam();

        int numNames = validNames.size();

        if (numNames == 2) {

            date = getDate();

            if (checkValidDate(date)) {

                score1 = getInt("Enter the goals for first team:  ");
                score2 = getInt("Enter the goals for second team:  ");

                name1 = validNames.get(0);
                name2 = validNames.get(1);

                try {
                    matches.add(new Match(date, name1, name2, score1, score2));

                    System.out.println("\nNew Match was added\n"
                            + "Date: " + date + "\n"
                            + name1 + ": " + score1 + "\n"
                            + name2 + ": " + score2 + "\n");

                } catch (InputMismatchException e) {
                    System.out.println("Input types did not match.");
                }
            } else {
                System.out.println("Did not pass the date in range");
            }

        } else {
            System.out.println("Club names were invalid. Please try again.");
        }
    }

    ArrayList<String> findTeam() {

        ArrayList<String> validNames = new ArrayList<>();

        String team1 = getString("Enter the name of the first team:  ");
        String team2 = getString("Enter the name of the second team:  ");


        if (nameArray.contains(team1) && nameArray.contains(team2)) {
            if (!team1.equals(team2)) {

                validNames.add(team1);
                validNames.add(team2);
            } else {
                System.out.println("Please enter two different teams.");
            }
        } else {
            System.out.println("Please enter the correct team names.");
        }
        return validNames;
    }

    private LocalDate getDate() {

        LocalDate date;

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the day of the match:  ");
        int day = input.nextInt();

        System.out.print("Enter the month of the match:  ");
        int month = input.nextInt();

        System.out.print("Enter the year of the match:  ");
        int year = input.nextInt();

        try {
            date = LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
            date = getDate();

        }

        return date;
    }

    boolean checkValidDate(LocalDate testDate) {
        return !(testDate.isBefore(startDate) || testDate.isAfter(endDate));
    }

    public void storeData() {

        try {
            // A text file with the given name is created if it doesn't exist already 
            File savedStatistics = new File("PL_Data.txt");

            FileWriter fileWriter = new FileWriter(savedStatistics.getName());
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Each of the strings from the main array is written to a different line of the text file
            for (int i = 0; i < array.size(); i++) {
                printWriter.write("club," + array.get(i).name + "," + array.get(i).location + "\n");
            }
            for (int i = 0; i < matchArray.size(); i++) {
                printWriter.write("match," + matchArray.get(i).date + "," + matchArray.get(i).clubA + "," + matchArray.get(i).clubB + "," + matchArray.get(i).scoreA + "," + matchArray.get(i).scoreB + "\n");
            }

            // Stop the data input and confirm stop
            printWriter.close();
            System.out.println("Statistics have been saved.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void loadData() {

        try {
            File savedQueue = new File("PL_Data.txt");
            Scanner myReader = new Scanner(savedQueue);

            // Look through each of the lines of the file and store them in the respective slots of the array
            while (myReader.hasNextLine()) {

                String input = myReader.nextLine();
                String[] splitStr = input.split(",");

                if (splitStr[0].equals("club")) {
                    String name = splitStr[1];
                    String location = splitStr[2];

                    list.add(new FootballClub(name, location), name);

                } else if (splitStr[0].equals("match")) {

                    LocalDate date = LocalDate.parse(splitStr[1]);
                    String name1 = splitStr[2];
                    String name2 = splitStr[3];
                    int score1 = Integer.parseInt(splitStr[4]);
                    int score2 = Integer.parseInt(splitStr[5]);

                    matchArray.add(new Match(date, name1, name2, score1, score2));
                }

            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Save file was not found.");

        }
    }

    private void showTableGUI() {
        PLTable leagueTable = new PLTable();
        leagueTable.createAndShowGUI(0);
    }

    private void openGUI() {
        MainMenu menu = new MainMenu();
    }

    private void displayMatchesTable() {
        matches.displayTable();
    }

    private void addAll() {

        for (int i = 0; i < 20; i++) {
            list.add(new FootballClub(teams[i], locations[i]), teams[i]);
        }
    }

    private void getRandomMatch() {
        Match m = new Match();
        matchArray.add(m);
    }


}

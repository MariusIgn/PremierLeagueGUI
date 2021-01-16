/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premierleague;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import static premierleague.MatchList.matchArray;

/**
 *
 * @author ignat
 */
public class MainMenu extends JFrame implements ActionListener {

    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton button5 = new JButton();
    JButton button6 = new JButton();
    JTextField field = new JTextField();
    JLabel alertLabel = new JLabel();
    JButton[] buttons = {button1, button2, button3, button4, button5, button6};

    MainMenu() {
        
        //Create all GUI elements
        for (JButton b : buttons) {
            b.addActionListener(this);
            b.setFocusable(false);
            b.setHorizontalTextPosition(JButton.CENTER);
            b.setVerticalTextPosition(JButton.BOTTOM);
            b.setFont(new Font("Oswald", Font.PLAIN, 18));
            b.setForeground(Color.black);
            b.setBackground(Color.lightGray);
            b.setBorder(BorderFactory.createEtchedBorder());
        }

        button1.setBounds(100, 50, 300, 70);
        button1.setText("Display Table Ordered By Points");

        button2.setBounds(100, 170, 300, 70);
        button2.setText("Display Table Ordered By Goals Scored");

        button3.setBounds(100, 290, 300, 70);
        button3.setText("Display Table Ordered By Number of Wins");

        button4.setBounds(100, 410, 300, 70);
        button4.setText("Generate Random Match");

        button5.setBounds(100, 530, 300, 70);
        button5.setText("Display Matches Table");

        field.addActionListener(this);
        field.setFont(new Font("Oswald", Font.PLAIN, 25));
        field.setForeground(Color.gray);
        field.setBorder(BorderFactory.createEtchedBorder());
        field.setBounds(100, 650, 195, 70);

        button6.setBounds(300, 650, 100, 70);
        button6.setText("Filter Matches");

        alertLabel.setFocusable(false);
        alertLabel.setBounds(100, 710, 300, 70);
        alertLabel.setHorizontalTextPosition(JButton.CENTER);
        alertLabel.setVerticalTextPosition(JButton.BOTTOM);
        alertLabel.setFont(new Font("Oswald", Font.PLAIN, 15));
        alertLabel.setForeground(Color.red);
        alertLabel.setText("Please enter a date in YYYY-MM-DD format.");
        alertLabel.setVisible(false);

        
        //Create frame and add elements
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 800);
        this.setVisible(true);
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        this.add(field);
        this.add(button6);
        this.add(alertLabel);
        this.setAlwaysOnTop(true);

    }
    
    //Event listeners for buttons and field
    @Override
    public void actionPerformed(ActionEvent e) {
        //Create PL table and sort by pints
        if (e.getSource() == button1) {
            PLTable leagueTable = new PLTable();
            leagueTable.createAndShowGUI(0);

        //Create PL table and sort by goals
        } else if (e.getSource() == button2) {
            PLTable leagueTable = new PLTable();
            leagueTable.createAndShowGUI(1);

        //Create PL table and sort by wins
        } else if (e.getSource() == button3) {
            PLTable leagueTable = new PLTable();
            leagueTable.createAndShowGUI(2);

        //Create new random match and show it's detasils
        } else if (e.getSource() == button4) {
            Match m = new Match();
            matchArray.add(m);
            MatchDetails details = new MatchDetails(m);
            
        //Show matches table
        } else if (e.getSource() == button5) {
            MatchesTable mTable = new MatchesTable();
            mTable.createAndShowGUI();

        //Show searc result for matches
        } else if (e.getSource() == button6) {

            String date = field.getText();
            LocalDate formattedDate = null;

            if (date.isEmpty()) {
                alertLabel.setVisible(true);
            } else {
                try {
                    formattedDate = getDateIput(date);
                    alertLabel.setVisible(false);
                    MatchesTable mTable = new MatchesTable();
                    mTable.filterAndShowGUI(formattedDate);
                } catch (DateTimeParseException | ParseException ex) {

                    alertLabel.setVisible(true);
                }
            }
        }
    }

    static LocalDate getDateIput(String date) throws ParseException {
        LocalDate validDate = LocalDate.parse(date);
        return validDate;

    }
}

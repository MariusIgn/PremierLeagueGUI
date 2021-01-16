/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premierleague;

import java.awt.Dimension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import static premierleague.MatchList.matchArray;
import static premierleague.PLList.array;

/**
 *
 * @author ignat
 */

public class MatchesTable extends JPanel {
    
    //Table constructor when no filter is given
    public MatchesTable() {
        try {
            int n = matchArray.size();
            int m = 5;

            
            String[] columnNames = {"Date", "Home Team", "Away Team", "Home Team Score", "Away Team Score"};
            Object[][] data = new Object[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {

                    if (j == 0) {
                        data[i][j] = matchArray.get(i).date;
                    } else if (j == 1) {
                        data[i][j] = matchArray.get(i).clubA;
                    } else if (j == 2) {
                        data[i][j] = matchArray.get(i).clubB;
                    } else if (j == 3) {
                        data[i][j] = matchArray.get(i).scoreA;
                    } else if (j == 4) {
                        data[i][j] = matchArray.get(i).scoreB;
                    } 
                }
            }

            final JTable table = new JTable(data, columnNames);

            table.setFillsViewportHeight(true);

            TableColumnModel columnModel = table.getColumnModel();

            columnModel.getColumn(0).setPreferredWidth(40);
            columnModel.getColumn(1).setPreferredWidth(15);
            columnModel.getColumn(2).setPreferredWidth(15);
            columnModel.getColumn(3).setPreferredWidth(10);
            columnModel.getColumn(4).setPreferredWidth(10);
            

            // Create the scroll pane and add the table to it.
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(700, 600));

            // Add the scroll pane to this panel .
            add(scrollPane);

        } catch (NullPointerException e) {
            System.out.println("Exception was " + e.getLocalizedMessage());
        }
    }
    
    
    //Table constructor when filter is given
    public MatchesTable(LocalDate date) {
        try {
            ArrayList<Match> sortedArray = new ArrayList<>();
            
            for (Match match : matchArray) {
                if (match.date.isEqual(date)) {
                    sortedArray.add(match);
                }
            }
            
            int n = sortedArray.size();
            int m = 5;
            
            String[] columnNames = {"Date", "Home Team", "Away Team", "Home Team Score", "Away Team Score"};
            Object[][] data = new Object[n][m];
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    
                    if (j == 0) {
                        data[i][j] = sortedArray.get(i).date;
                    } else if (j == 1) {
                        data[i][j] = sortedArray.get(i).clubA;
                    } else if (j == 2) {
                        data[i][j] = sortedArray.get(i).clubB;
                    } else if (j == 3) {
                        data[i][j] = sortedArray.get(i).scoreA;
                    } else if (j == 4) {
                        data[i][j] = sortedArray.get(i).scoreB;
                    }
                }
            }
            
            final JTable table = new JTable(data, columnNames);
            
            table.setFillsViewportHeight(true);
            
            TableColumnModel columnModel = table.getColumnModel();
            
            //columnModel.getColumn(0).setPreferredWidth(10);
            columnModel.getColumn(1).setPreferredWidth(30);
            columnModel.getColumn(2).setPreferredWidth(30);
            columnModel.getColumn(3).setPreferredWidth(10);
            columnModel.getColumn(4).setPreferredWidth(10);
            
            columnModel.getColumn(0).setMinWidth(30);

            // Create the scroll pane and add the table to it.
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(700, 600));

            // Add the scroll pane to this panel .
            add(scrollPane);
            
        } catch (NullPointerException e) {
            System.out.println("Exception was " + e.getLocalizedMessage());
        }
        
    }
    

    public void createAndShowGUI() {
        // Create the frame window
        JFrame frame = new JFrame("Premier League");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
       
        Collections.sort(matchArray, new MatchComparator());
        
        
        // Create and set up the content pane .
        MatchesTable ContentPane = new MatchesTable();

        ContentPane.setOpaque(true); // content panes must be opaque

        frame.setContentPane(ContentPane);

        // Display the window .
        frame.pack();
        frame.setSize(800, 700);
        frame.setVisible(true);

    }
    
    public void filterAndShowGUI(LocalDate date) {
        // Create the frame window
        JFrame frame = new JFrame("Premier League");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        Collections.sort(matchArray, new MatchComparator());

        // Create and set up the content pane .
        MatchesTable ContentPane = new MatchesTable(date);
        
        ContentPane.setOpaque(true); // content panes must be opaque

        frame.setContentPane(ContentPane);

        // Display the window .
        frame.pack();
        frame.setSize(800, 700);
        frame.setVisible(true);
    }

}

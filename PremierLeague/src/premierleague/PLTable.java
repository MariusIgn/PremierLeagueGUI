/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premierleague;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.TableColumnModel;
import static premierleague.PLList.array;

/**
 *
 * @author ignat
 */
public class PLTable extends JPanel {

    public PLTable() {
        try {
            int n = array.size();
            int m = 9;

            String[] columnNames = {"Club Name", "Club Location", "Points", "Matches Played", "Wins", "Draws",
                "Defeat", "Goals Scored", "Goals Received"};
            Object[][] data = new Object[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {

                    if (j == 0) {
                        data[i][j] = array.get(i).getName();
                    } else if (j == 1) {
                        data[i][j] = array.get(i).getLocation();
                    } else if (j == 2) {
                        data[i][j] = array.get(i).getPoints();
                    } else if (j == 3) {
                        data[i][j] = array.get(i).getMatchesPlayed();
                    } else if (j == 4) {
                        data[i][j] = array.get(i).getNumWin();
                    } else if (j == 5) {
                        data[i][j] = array.get(i).getNumDraw();
                    } else if (j == 6) {
                        data[i][j] = array.get(i).getNumDefeat();
                    } else if (j == 7) {
                        data[i][j] = array.get(i).getGoalsScored();
                    } else if (j == 8) {
                        data[i][j] = array.get(i).getGoalsReceived();
                    }
                }
            }

            final JTable table = new JTable(data, columnNames);

            table.setFillsViewportHeight(true);

            TableColumnModel columnModel = table.getColumnModel();

            columnModel.getColumn(0).setPreferredWidth(27);
            columnModel.getColumn(1).setPreferredWidth(27);
            columnModel.getColumn(2).setPreferredWidth(4);
            columnModel.getColumn(3).setPreferredWidth(10);
            columnModel.getColumn(4).setPreferredWidth(4);
            columnModel.getColumn(5).setPreferredWidth(4);
            columnModel.getColumn(6).setPreferredWidth(4);
            columnModel.getColumn(7).setPreferredWidth(10);
            columnModel.getColumn(8).setPreferredWidth(10);

            // Create the scroll pane and add the table to it.
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(900, 600));

            // Add the scroll pane to this panel .
            add(scrollPane);

        } catch (NullPointerException e) {
            System.out.println("Exception was " + e.getLocalizedMessage());
        }
    }

    public void createAndShowGUI(int mode) {
        // Create the frame window
        JFrame frame = new JFrame("Premier League");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Sort the table and change frame name depending on given sorting criteria
        if (mode == 0) {
            array.sort(new PointsComparator());
            frame.setTitle("Premier League Table Sorted By Points");
        } else if (mode == 1) {
            //Collections.sort(array, new GoalsComparator());
            array.sort(new GoalsComparator());
            frame.setTitle("Premier League Table Sorted By Number of Goals");
        } else if (mode == 2) {
            //Collections.sort(array, new WinsComparator());
            array.sort(new WinsComparator());
            frame.setTitle("Premier League Table Sorted By Number of Wins");
        }

        // Create and set up the content pane .
        PLTable ContentPane = new PLTable();
       
        

        ContentPane.setOpaque(true); // content panes must be opaque

        frame.setContentPane(ContentPane);

        // Display the window .
        frame.pack();
        frame.setSize(1000, 700);
        frame.setVisible(true);

    }

}

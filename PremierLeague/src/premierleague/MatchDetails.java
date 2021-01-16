package premierleague;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class MatchDetails extends JFrame {
    
    //Window to show the details fo the generated match
    JFrame frame = new JFrame();
    JTextArea textArea = new JTextArea();
    
    MatchDetails(Match m){
        
       
        
        textArea.setText("Date: " + m.date + "\n"
        +"Home Team: " + m.clubA + "\n"
        +"Away Team: " + m.clubB + "\n"
        +"Home Team Score: " + m.scoreA + "\n"
        +"Away Team Score: " + m.scoreB + "\n");
        
        textArea.setBounds(20, 20, 500, 400);
        textArea.setFont(new Font("Oswald", Font.PLAIN, 18));
        textArea.setBackground(null);
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(600, 400);
        this.add(textArea);
        this.setVisible(true);
    
    }
    
}

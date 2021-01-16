
package premierleague;

import java.util.Comparator;

class PointsComparator implements Comparator<FootballClub> {
    
    @Override
    public int compare(FootballClub fc1, FootballClub fc2) {
        if (fc1.points < fc2.points) {
            return 1;
        } else if (fc1.points > fc2.points) {
            return -1;
        } else {
            if (fc1.goalsScored < fc2.goalsScored) {
                return 1;
            } else if (fc1.goalsScored > fc2.goalsScored) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

class GoalsComparator implements Comparator<FootballClub> {
    
    @Override
    public int compare(FootballClub fc1, FootballClub fc2) {
        if (fc1.goalsScored < fc2.goalsScored) {
            return 1;
        } else if (fc1.goalsScored > fc2.goalsScored) {
            return -1;
        } else {
            if (fc1.points < fc2.points) {
                return 1;
            } else if (fc1.points > fc2.points) {
                return -1;
            } else {
                return 0;
            }
        }
    }

}

class WinsComparator implements Comparator<FootballClub> {
    
    @Override
    public int compare(FootballClub fc1, FootballClub fc2) {
        if (fc1.numWin < fc2.numWin) {
            return 1;
        } else if (fc1.numWin > fc2.numWin) {
            return -1;
        } else {
            if (fc1.points < fc2.points) {
                return 1;
            } else if (fc1.points > fc2.points) {
                return -1;
            } else {
                return 0;
            }
        }
    }

}

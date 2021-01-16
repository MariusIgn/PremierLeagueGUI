
package premierleague;

import java.util.Comparator;

class MatchComparator implements Comparator<Match> {

    @Override
    public int compare(Match m1, Match m2) {
        if (m1.date.isBefore(m2.date)) {
            return 1;
        } else if (m2.date.isBefore(m1.date)) {
            return -1;
        } else {
            return 0;
        }
    }

}

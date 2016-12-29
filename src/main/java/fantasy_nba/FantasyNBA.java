package fantasy_nba;

import stats.Statistic;
import stats.StatsFactory;

public class FantasyNBA {

	public static void main(String args[]) {
		StatsFactory.establishConnection();
//		Statistic playbyplay = StatsFactory.getPlayByPlay("0021600485");
//		playbyplay.printItems(0);
		Statistic score = StatsFactory.getTodayScoreboard();
		score.printItem(0);
	}
}

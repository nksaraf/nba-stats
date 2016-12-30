package fantasy_nba;

import stats.Player;
import stats.PlayerDashboard;
import stats.Statistic;
import stats.StatsFactory;

public class FantasyNBA {

	public static void main(String args[]) {
		StatsFactory.establishConnection();
//		Statistic playbyplay = StatsFactory.getPlayByPlay("0021600485");
//		playbyplay.printItems(0);
//		Statistic score = StatsFactory.getTodayScoreboard();
//		score.printItem(0);
		Player player = StatsFactory.getPlayer("ID", "201939");
		player.loadDashboard(PlayerDashboard.Type.DEFENSE_TRACKING, StatsFactory.getConnection());
		player.getDashboard().print();
		//System.out.println(StatsFactory.getPlayers("FULL_NAME", "CURRY").toString());
		//System.out.println(StatsFactory.getPlayerList().toString());
	}
}

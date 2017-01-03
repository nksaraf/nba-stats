package fantasy_nba;

import stats.api.StatsFactory;
import stats.api.Team;

public class FantasyNBA {

	public static void main(String args[]) {
		StatsFactory.establishConnection();
		Team team = new Team("1610612744");
		team.load(StatsFactory.getConnection());
		team.getFeature(Team.Feature.ROSTER).print();
		team.getFeature(Team.Feature.ROSTER).dumpJSON();
		
//		
		
	}
}

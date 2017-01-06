package stats.cli;


import stats.api.StatsFactory;
import stats.api.league.League;

public class LeagueCLI extends ElementCLI {

	static League league;
	
	public static void get(String args[]) {
		try {
			league = StatsFactory.getLeague();
			if(league == null) throw new IllegalArgumentException("League not loaded");
			leagueOptions(args);
			options(args);
			
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void leagueOptions(String args[]) {
		switch(args[0]) {
			case "-leaders": {
				stat = league.getFeature(League.Feature.LEADERS);
				break;
			}
			case "-leadertiles": {
				stat = league.getFeature(League.Feature.LEADERS_TILES);
				break;
			}
			case "-players": {
				stat = league.getFeature(League.Feature.PLAYER_STATS);
				break;
			}
			case "-log": {
				stat = league.getFeature(League.Feature.GAME_LOG);
				break;
			}
			case "-lineups": {
				stat = league.getFeature(League.Feature.LINEUPS);
			}
			default: {
				if(stat == null) throw new IllegalArgumentException("Illegal game element option provided");
			}
		}
		
	}
	
}

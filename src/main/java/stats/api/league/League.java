package stats.api.league;


import stats.api.connection.Connection;
import stats.api.util.Element;

public class League extends Element {

	public League() {
		super("00");
		features.put(Feature.LEADERS, new LeagueLeaders(fields));
		features.put(Feature.LEADERS_TILES, new LeagueLeadersTiles(fields));
		features.put(Feature.GAME_LOG, new LeagueGameLog(fields));
		features.put(Feature.PLAYER_STATS, new PlayerStats(fields));
		features.put(Feature.LINEUPS,  new LeagueLineups(fields));
	}
	
	public League(Connection c) {
		this();
		load(c);
	}

	public enum Feature implements Element.Feature {
		
		LEADERS("leaders"),
		LEADERS_TILES("leaderstiles"),
		GAME_LOG("gamelog"),
		PLAYER_STATS("playerstats"),
		LINEUPS("lineups");
		
		private String description;
		
		private Feature(String d) {
			description = d;
		}
		
		public String toString() {
			return description;
		}
	}
	
}

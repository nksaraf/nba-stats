/**
 * 
 */
package stats.api;

/**
 * @author nikhilsaraf
 *
 */
public enum Constants {
	
	API_URL("http://stats.nba.com/stats/{endpoint}"),
	SCORES_REFERER("http://stats.nba.com/scores/"),
	PLAYER_REFERER("http://stats.nba.com/player/");
	
	private String value;
	
	private Constants(String v) {
		value = v;
	}
	
	public String toString() {
		return value;
	}
	
	enum Endpoints {
		BOX_SCORE("boxscore"),
		PLAY_BY_PLAY("playbyplay"),
		PLAYER_TRACKING("boxscoreplayertrackv2"),
		PLAYER("player"),
		PLAYER_GAMELOG("playergamelog"),
		PLAYER_SUMMARY("commonplayerinfo"),
		PLAYER_LIST("commonallplayers"),
		SCOREBOARD("scoreboard"),
		TEAM("team"),
		TEAM_DETAILS("teamdetails"),
		TEAM_ROSTER("commonteamroster"),
		TEAM_SUMMARY("teaminfocommon"),
		TEAM_GAMELOG("teamgamelog"),
		TEAM_SEASONS("teamyearbyyearstats");
		
		private String value;
		
		private Endpoints(String v) {
			value = v;
		}
		
		public String toString() {
			return value;
		}
		
	}
}

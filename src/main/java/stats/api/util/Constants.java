/**
 * 
 */
package stats.api.util;

/**
 * @author nikhilsaraf
 *
 */
public enum Constants {

	API_URL("http://stats.nba.com/stats/{endpoint}"), SCORES_REFERER("http://stats.nba.com/scores/"), PLAYER_REFERER(
			"http://stats.nba.com/player/");

	private String value;

	private Constants(String v) {
		value = v;
	}

	public String toString() {
		return value;
	}

	public enum Endpoints {
		BOX_SCORE("boxscore"), PLAY_BY_PLAY("playbyplay"), PLAYER_TRACKING("boxscoreplayertrackv2"), PLAYER(
				"player"), PLAYER_GAMELOG("playergamelog"), PLAYER_SUMMARY("commonplayerinfo"), PLAYER_LIST(
						"commonallplayers"), PLAYER_CAREER("playerprofilev2"), PLAYER_VS_PLAYER(
								"playervsplayer"), SHOT_CHART("shotchartdetail"), SCOREBOARD("scoreboard"), TEAM_LIST(
										"commonteamyears"), TEAM("team"), TEAM_DETAILS("teamdetails"), TEAM_ROSTER(
												"commonteamroster"), TEAM_SUMMARY("teaminfocommon"), TEAM_GAMELOG(
														"teamgamelog"), TEAM_SEASONS(
																"teamyearbyyearstats"), TEAM_LINEUPS(
																		"teamdashlineups"), TEAM_VS_PLAYER(
																				"teamvsplayer"), LEAGUE_LEADERS(
																						"leagueleaders"), LEAGUE_LEADERS_TILES(
																								"leaderstiles"), LEAGUE_LINEUPS(
																										"leaguedashlineups"), LEAGUE_PLAYER_STATS(
																												"leaguedashplayerstats"), LEAGUE_GAME_LOG(
																														"leaguegamelog"), COMBINE_SUMMARY(
																																"draftcombinestats"), DRILL_RESULTS(
																																		"draftcombinedrillresults"), SPOT_SHOOTING(
																																				"draftcombinespotshooting");

		private String value;

		private Endpoints(String v) {
			value = v;
		}

		public String toString() {
			return value;
		}

	}
}

package stats.api.league;

import java.util.Map;

import stats.api.connection.Connection;
import stats.api.util.Constants;
import stats.api.util.FieldType;
import stats.api.util.Statistic;

public class LeagueLineups extends Statistic {

	
	private static FieldType[] required_fields = {
			FieldType.GROUP_QUANTITY,
			FieldType.MEASURE_TYPE,
			FieldType.PER_MODE,
			FieldType.PLUS_MINUS,
			FieldType.PACE_ADJUST,
			FieldType.RANK,
			FieldType.SEASON,
			FieldType.SEASON_TYPE,
			FieldType.OUTCOME,
			FieldType.LOCATION,
			FieldType.MONTH,
			FieldType.SEASON_SEGMENT,
			FieldType.DATE_FROM,
			FieldType.DATE_TO,
			FieldType.OPPONENT_TEAM_ID,
			FieldType.VS_CONFERENCE,
			FieldType.VS_DIVISION,
			FieldType.GAME_SEGMENT,
			FieldType.PERIOD,
			FieldType.LAST_N_GAMES
		};
	
	public LeagueLineups(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.LEAGUE_LINEUPS.toString(), fields, required_fields);
	}
	
	public LeagueLineups(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.LEAGUE_LINEUPS.toString(), fields, required_fields, c);
	}
	
	public enum ItemType implements Statistic.ItemType{
		OVERALL(0);
		
		public int index;
		
		private ItemType(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
 	}
	
}

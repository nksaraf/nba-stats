package stats.api;

import java.util.Map;

import stats.connection.Connection;

public class TeamLineups extends Statistic {
	
	private static FieldType[] required_fields = {
			FieldType.TEAM_ID, 
			FieldType.GAME_ID,
			FieldType.GROUP_QUANTITY,
			FieldType.MEASURE_TYPE,
			FieldType.PER_MODE,
			FieldType.PLUS_MINUS,
			FieldType.PACE_ADJUST,
			FieldType.RANK,
			FieldType.SEASON,
			FieldType.SEASON_TYPE,
			FieldType.PLAYOFF_ROUND,
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
	/**
	 * @param endpoint
	 * @param fields
	 * @param required_fields
	 */
	public TeamLineups(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.TEAM_SEASONS.toString(), fields, required_fields);
	}
	
	public TeamLineups(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.TEAM_SEASONS.toString(), fields, required_fields, c);
	}
	
	public enum ItemType implements Statistic.ItemType {
		INFO(0);
		
		private int index;
		
		private ItemType(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
	}

}

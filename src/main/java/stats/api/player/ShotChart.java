package stats.api.player;

import java.util.Map;

import stats.api.connection.Connection;
import stats.api.util.Constants;
import stats.api.util.FieldType;
import stats.api.util.Statistic;

public class ShotChart extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.PLAYER_ID,
			FieldType.TEAM_ID,
			FieldType.GAME_ID,
			FieldType.LEAGUE,
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
			FieldType.PLAYER_POSITION,
			FieldType.GAME_SEGMENT,
			FieldType.PERIOD,
			FieldType.LAST_N_GAMES,
			FieldType.AHEAD_BEHIND,
			FieldType.CONTEXT_MEASURE,
			FieldType.CLUTCH_TIME,
			FieldType.ROOKIE_YEAR
		};
	
	/**
	 * @param fields
	 * @param required_fields
	 */
	public ShotChart(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.SHOT_CHART.toString(), fields, required_fields);
	}
	
	public ShotChart(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.SHOT_CHART.toString(), fields, required_fields, c);
	}
	
	public enum ItemType implements Statistic.ItemType {
		SHOT_CHART(0),
		LEAGURE_AVG(1);
		
		private int index;
		
		private ItemType(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
	}
}

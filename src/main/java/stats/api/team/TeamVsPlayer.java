package stats.api.team;

import java.util.Map;

import stats.api.connection.Connection;
import stats.api.util.Constants;
import stats.api.util.FieldType;
import stats.api.util.Statistic;

public class TeamVsPlayer extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.VS_PLAYER_ID,
			FieldType.TEAM_ID,
			FieldType.MEASURE_TYPE,
			FieldType.PER_MODE,
			FieldType.PLUS_MINUS,
			FieldType.PACE_ADJUST,
			FieldType.RANK,
			FieldType.LEAGUE,
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
			FieldType.LAST_N_GAMES,
			FieldType.SHOTCLOCK_RANGE
		};
	/**
	 * @param endpoint
	 * @param fields
	 * @param required_fields
	 */
	public TeamVsPlayer(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.TEAM_VS_PLAYER.toString(), fields, required_fields);
	}
	
	public TeamVsPlayer(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.TEAM_VS_PLAYER.toString(), fields, required_fields, c);
	}
	
	public enum ItemType implements Statistic.ItemType {
		OVERALL(0),
		ON_OFF_COURT(1),
		SHOT_DISTANCE(2),
		SHOT_DISTANCE_ON_COURT(3),
		SHOT_DISTANCE_OFF_COURT(4),
		SHOT_AREA(5),
		SHOT_AREA_ON_COURT(6),
		SHOT_AREA_OFF_COURT(7);
		
		private int index;
		
		private ItemType(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
	}
	
}

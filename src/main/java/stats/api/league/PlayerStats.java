package stats.api.league;

import java.util.Map;

import stats.api.connection.Connection;
import stats.api.util.Constants;
import stats.api.util.FieldType;
import stats.api.util.Statistic;

public class PlayerStats extends Statistic {

	private static FieldType[] required_fields = { FieldType.MEASURE_TYPE, FieldType.PER_MODE, FieldType.PLUS_MINUS,
			FieldType.PACE_ADJUST, FieldType.RANK, FieldType.SEASON, FieldType.PLAYOFF_ROUND, FieldType.SEASON_TYPE,
			FieldType.OUTCOME, FieldType.LOCATION, FieldType.MONTH, FieldType.SEASON_SEGMENT, FieldType.DATE_FROM,
			FieldType.DATE_TO, FieldType.OPPONENT_TEAM_ID, FieldType.VS_CONFERENCE, FieldType.VS_DIVISION,
			FieldType.TEAM_ID, FieldType.CONFERENCE, FieldType.DIVISION, FieldType.GAME_SEGMENT, FieldType.PERIOD,
			FieldType.SHOTCLOCK_RANGE, FieldType.GAMESCOPE, FieldType.LAST_N_GAMES, FieldType.PLAYER_POSITION,
			FieldType.PLAYER_EXPERIENCE, FieldType.STARTER_BENCH, FieldType.DRAFT_YEAR, FieldType.DRAFT_PICK,
			FieldType.COLLEGE, FieldType.COUNTRY, FieldType.HEIGHT, FieldType.WEIGHT };

	public PlayerStats(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.LEAGUE_PLAYER_STATS.toString(), fields, required_fields);
	}

	public PlayerStats(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.LEAGUE_PLAYER_STATS.toString(), fields, required_fields, c);
	}

	public enum ItemType implements Statistic.ItemType {
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

package stats.api.util;

import java.util.Map;

import stats.api.connection.Connection;

public abstract class Dashboard extends Statistic {

	private static FieldType[] required_fields = { FieldType.PLAYER_ID, FieldType.TEAM_ID, FieldType.MEASURE_TYPE,
			FieldType.PER_MODE, FieldType.PLUS_MINUS, FieldType.PACE_ADJUST, FieldType.RANK, FieldType.LEAGUE,
			FieldType.SEASON, FieldType.SEASON_TYPE, FieldType.PLAYOFF_ROUND, FieldType.OUTCOME, FieldType.LOCATION,
			FieldType.MONTH, FieldType.SEASON_SEGMENT, FieldType.DATE_FROM, FieldType.DATE_TO,
			FieldType.OPPONENT_TEAM_ID, FieldType.VS_CONFERENCE, FieldType.VS_DIVISION, FieldType.GAME_SEGMENT,
			FieldType.PERIOD, FieldType.SHOTCLOCK_RANGE, FieldType.LAST_N_GAMES };

	protected Type type;

	public Dashboard(String endpoint, Map<FieldType, Object> fields) {
		super(endpoint, fields, required_fields);

	}

	public Dashboard(String endpoint, Map<FieldType, Object> fields, Connection c) {
		super(endpoint, fields, required_fields, c);
	}

	public Type getType() {
		return type;
	}

	public interface Type {

		public String toString();
	}

	public interface ItemType extends Statistic.ItemType {

		public Type getType();
	}

}

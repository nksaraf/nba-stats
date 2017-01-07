package stats.api.league;

import java.util.Map;

import stats.api.connection.Connection;
import stats.api.util.Constants;
import stats.api.util.FieldType;
import stats.api.util.Statistic;

public class LeagueLeaders extends Statistic {

	private static FieldType[] required_fields = { FieldType.LEAGUE, FieldType.STAT_CATEGORY, FieldType.SEASON,
			FieldType.SEASON_TYPE, FieldType.SCOPE, FieldType.PER_MODE };

	public LeagueLeaders(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.LEAGUE_LEADERS.toString(), fields, required_fields);
	}

	public LeagueLeaders(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.LEAGUE_LEADERS.toString(), fields, required_fields, c);
	}

	public enum ItemType implements Statistic.ItemType {
		RESULTS(0);

		public int index;

		private ItemType(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
	}

}

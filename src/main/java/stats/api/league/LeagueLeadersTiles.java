package stats.api.league;

import java.util.Map;

import stats.api.connection.Connection;
import stats.api.util.Constants;
import stats.api.util.FieldType;
import stats.api.util.Statistic;

public class LeagueLeadersTiles extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.LEAGUE,
			FieldType.STAT,
			FieldType.SEASON,
			FieldType.SEASON_TYPE,
			FieldType.GAMESCOPE,
			FieldType.PLAYER_SCOPE,
			FieldType.PLAYER_OR_TEAM
		};
	
	public LeagueLeadersTiles(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.LEAGUE_LEADERS_TILES.toString(), fields, required_fields);
	}
	
	public LeagueLeadersTiles(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.LEAGUE_LEADERS_TILES.toString(), fields, required_fields, c);
	}
	
	public enum ItemType implements Statistic.ItemType{
		CURRENT_SEASON_HIGH(0),
		ALLTIME_SEASON_HIGH(1),
		LAST_SEASON_HIGH(2),
		LOW_SEASON_HIGH(3);
		
		public int index;
		
		private ItemType(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
 	}
}


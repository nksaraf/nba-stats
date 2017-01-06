package stats.api.league;

import java.util.Map;

import stats.api.connection.Connection;
import stats.api.util.Constants;
import stats.api.util.FieldType;
import stats.api.util.Statistic;

public class LeagueGameLog extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.LEAGUE,
			FieldType.SEASON,
			FieldType.SEASON_TYPE,
			FieldType.PLAYER_OR_TEAM,
			FieldType.COUNTER,
			FieldType.SORTER,
			FieldType.DIRECTION
		};
	
	public LeagueGameLog(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.LEAGUE_GAME_LOG.toString(), fields, required_fields);
	}
	
	public LeagueGameLog(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.LEAGUE_GAME_LOG.toString(), fields, required_fields, c);
	}
	
	public enum ItemType implements Statistic.ItemType{
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

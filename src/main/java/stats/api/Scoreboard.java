/**
 * 
 */
package stats.api;

import java.util.Map;

import stats.connection.Connection;

/**
 * @author nikhilsaraf
 *
 */
public class Scoreboard extends Statistic {
	
	private static FieldType[] required_fields = {
			FieldType.LEAGUE, 
			FieldType.GAME_DATE, 
			FieldType.DAY_OFFSET };
	
	public Scoreboard(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.SCOREBOARD.toString(), fields, required_fields);
	}
	
	public Scoreboard(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.SCOREBOARD.toString(), fields, required_fields, c);
	}
	
	public enum ItemType implements Statistic.ItemType {
		GAME_HEADER(0),
		LINE_SCORING(1),
		SERIES_STANDINGS(2),
		LAST_MEETING(3),
		EAST_CONF_STANDINGS_BY_DAY(4),
		WEST_CONF_STANDINGS_BY_DAY(5),
		AVAILABLE(6);
		
		private int index;
		
		private ItemType(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
 	}
	
}

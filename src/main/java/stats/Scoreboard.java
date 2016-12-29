/**
 * 
 */
package stats;

import java.util.Map;

import api.Connection;

/**
 * @author nikhilsaraf
 *
 */
public class Scoreboard extends Statistic {
	
	private static FieldType[] required_fields = {
			FieldType.LEAGUE, 
			FieldType.GAME_DATE, 
			FieldType.DAY_OFFSET };
	
	public Scoreboard(Map<String, Object> fields) {
		super("scoreboard", fields, required_fields);
	}
	
	public Scoreboard(Map<String, Object> fields, Connection c) {
		super("scoreboard", fields, required_fields, c);
	}
	
	@Override
	public void load(String endpoint, Connection c) {
		throw new UnsupportedOperationException();
	}
	
	public StatItem getItem(ItemType t) {
		return getItem(t.index);
	}
	
	public enum ItemType {
		GAME_HEADER(0),
		LINE_SCORING(1),
		SERIES_STANDINGS(2),
		LAST_MEETING(3),
		EAST_CONF_STANDINGS_BY_DAY(4),
		WEST_CONF_STANDINGS_BY_DAY(5),
		AVAILABLE(6);
		
		public int index;
		
		private ItemType(int index) {
			this.index = index;
		}
	}
	
}

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
public class PlayerGameLog extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.PLAYER_ID,
			FieldType.LEAGUE,
			FieldType.SEASON,
			FieldType.SEASON_TYPE
		};
	/**
	 * @param endpoint
	 * @param fields
	 * @param required_fields
	 */
	public PlayerGameLog(Map<String, Object> fields) {
		super("playergamelog", fields, required_fields);
	}

	/**
	 * @param endpoint
	 * @param fields
	 * @param required_fields
	 * @param c
	 */
	public PlayerGameLog(Map<String, Object> fields, Connection c) {
		super("playergamelog", fields, required_fields, c);
	}
	
	@Override
	public void load(String endpoint, Connection c) {
		throw new UnsupportedOperationException();
	}
	
	public StatItem getItem(ItemType t) {
		return getItem(t.index);
	}
	
	public enum ItemType {
		INFO(0);
		
		public int index;
		
		private ItemType(int index) {
			this.index = index;
		}
	}

}

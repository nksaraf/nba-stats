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
public class PlayerTracking extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.GAME_ID };
	/**
	 * @param fields
	 * @param required_fields
	 */
	public PlayerTracking(Map<String, Object> fields) {
		super("boxscoreplayertrackingv2", fields, required_fields);
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

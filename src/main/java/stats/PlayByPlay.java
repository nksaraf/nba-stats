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
public class PlayByPlay extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.GAME_ID,
			FieldType.START_PERIOD,
			FieldType.END_PERIOD };
	/**
	 * @param fields
	 * @param required_fields
	 */
	public PlayByPlay(Map<String, Object> fields) {
		super("playbyplay", fields, required_fields);
	}
	
	@Override
	public void load(String endpoint, Connection c) {
		throw new UnsupportedOperationException();
	}
	
	public StatItem getItem(ItemType t) {
		return getItem(t.index);
	}
	
	public enum ItemType {
		INFO(0),
		AVAILABLE_VIDEO(1);
		
		public int index;
		
		private ItemType(int index) {
			this.index = index;
		}
	}

}

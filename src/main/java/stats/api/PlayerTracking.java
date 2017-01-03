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
public class PlayerTracking extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.GAME_ID };
	/**
	 * @param fields
	 * @param required_fields
	 */
	public PlayerTracking(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.PLAYER_TRACKING.toString(), fields, required_fields);
	}
	
	public PlayerTracking(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.PLAYER_TRACKING.toString(), fields, required_fields, c);
	}
	
	public enum ItemType implements Statistic.ItemType {
		INFO(0);
		
		private int index;
		
		private ItemType(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
	}

}

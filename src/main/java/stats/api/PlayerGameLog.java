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
	public PlayerGameLog(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.PLAYER_GAMELOG.toString(), fields, required_fields);
	}
	
	public PlayerGameLog(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.PLAYER_GAMELOG.toString(), fields, required_fields, c);
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

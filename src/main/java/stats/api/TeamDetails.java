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
public class TeamDetails extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.TEAM_ID
		};
	/**
	 * 
	 */
	public TeamDetails(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.TEAM_DETAILS.toString(), fields, required_fields);
	}
	
	public TeamDetails(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.TEAM_DETAILS.toString(), fields, required_fields, c);
	}
	
	public enum ItemType implements Statistic.ItemType {
		BACKGROUND(0),
		HISTORY(1),
		SOCIAL_SITES(2),
		AWARDS_CHAMPIONSHIPS(3),
		AWARDS_CONF(4),
		AWARDS_DIV(5),
		HOF(6),
		RETIRED(7);
		
		private int index;
		
		private ItemType(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
	}


}

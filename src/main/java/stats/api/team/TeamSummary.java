/**
 * 
 */
package stats.api.team;

import java.util.Map;

import stats.api.connection.Connection;
import stats.api.util.Constants;
import stats.api.util.FieldType;
import stats.api.util.Statistic;

/**
 * @author nikhilsaraf
 *
 */
public class TeamSummary extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.TEAM_ID,
			FieldType.SEASON,
			FieldType.LEAGUE,
			FieldType.SEASON_TYPE
		};
	
	/**
	 * @param endpoint
	 * @param fields
	 * @param required_fields
	 */
	public TeamSummary(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.TEAM_SUMMARY.toString(), fields, required_fields);
	}

	/**
	 * @param endpoint
	 * @param fields
	 * @param required_fields
	 * @param c
	 */
	public TeamSummary(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.TEAM_SUMMARY.toString(), fields, required_fields, c);
	}
	
	public enum ItemType {
		INFO(0),
		SEASON_RANKS(1);
		
		private int index;
		
		private ItemType(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
 	}

}

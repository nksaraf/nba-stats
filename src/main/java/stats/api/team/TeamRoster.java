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
public class TeamRoster extends Statistic {

	private static FieldType[] required_fields = { FieldType.TEAM_ID, FieldType.SEASON };

	/**
	 * 
	 */
	public TeamRoster(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.TEAM_ROSTER.toString(), fields, required_fields);
	}

	public TeamRoster(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.TEAM_ROSTER.toString(), fields, required_fields, c);
	}

	public enum ItemType {
		ROSTER(0), COACHES(1);

		private int index;

		private ItemType(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
	}

}

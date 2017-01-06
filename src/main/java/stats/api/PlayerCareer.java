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
public class PlayerCareer extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.PLAYER_ID, 
			FieldType.LEAGUE, 
			FieldType.PER_MODE };
	
	public PlayerCareer(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.PLAYER_CAREER.toString(), fields, required_fields);
	}
	
	public PlayerCareer(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.PLAYER_CAREER.toString(), fields, required_fields, c);
	}
	
	public enum ItemType implements Statistic.ItemType {
		
		REG_SEASON_TOTALS(0),
		REG_SEASON_CAREER_TOTALS(1),
		POST_SEASON_TOTALS(2),
		POST_SEASON_CAREER_TOTALS(3),
		ALLSTAR_SEASON_TOTALS(4),
		ALLSTAR_SEASON_CAREER_TOTALS(5),
		COLLEGE_SEASON_TOTALS(6),
		COLLEGE_SEASON_CAREER_TOTALS(7),
		PRESEASON_TOTALS(8),
		PRESEASON_CAREER_TOTALS(9),
		REG_SEASON_RANKINGS(10),
		POST_SEASON_RANKINGS(11),
		SEASON_HIGHS(12),
		CAREER_HIGHS(13),
		NEXT_GAME(14);
		
		private int index;
		
		private ItemType(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
	}


}

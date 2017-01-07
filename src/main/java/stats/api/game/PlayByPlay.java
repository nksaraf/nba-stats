/**
 * 
 */
package stats.api.game;

import java.util.Map;

import stats.api.connection.Connection;
import stats.api.util.Constants;
import stats.api.util.FieldType;
import stats.api.util.Statistic;

/**
 * @author nikhilsaraf
 *
 */
public class PlayByPlay extends Statistic {

	private static FieldType[] required_fields = { FieldType.GAME_ID, FieldType.START_PERIOD, FieldType.END_PERIOD };

	/**
	 * @param fields
	 * @param required_fields
	 */
	public PlayByPlay(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.PLAY_BY_PLAY.toString(), fields, required_fields);
	}

	public PlayByPlay(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.PLAY_BY_PLAY.toString(), fields, required_fields, c);
	}

	public enum ItemType implements Statistic.ItemType {
		INFO(0), AVAILABLE_VIDEO(1);

		private int index;

		private ItemType(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
	}

}

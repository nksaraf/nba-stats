package stats.api.player;

import java.util.Map;

import stats.api.connection.Connection;
import stats.api.util.Constants;
import stats.api.util.FieldType;
import stats.api.util.Statistic;

public class PlayerSummary extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.PLAYER_ID
		};
	
	public PlayerSummary(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.PLAYER_SUMMARY.toString(), fields, required_fields);
	}
	
	public PlayerSummary(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.PLAYER_SUMMARY.toString(), fields, required_fields, c);
	}
	
	public enum ItemType implements Statistic.ItemType{
		INFO(0),
		HEADLINE_STATS(1);
		
		public int index;
		
		private ItemType(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
 	}

}

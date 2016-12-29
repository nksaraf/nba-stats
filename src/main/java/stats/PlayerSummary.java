package stats;

import java.util.Map;

import api.Connection;

public class PlayerSummary extends Statistic {

	
	private static FieldType[] required_fields = {
			FieldType.PLAYER_ID
		};
	
	public PlayerSummary(Map<String, Object> fields) {
		super("commonplayerinfo", fields, required_fields);
	}
	
	public PlayerSummary(Map<String, Object> fields, Connection c) {
		super("commonplayerinfo", fields, required_fields, c);
	}
	
	public StatItem getItem(ItemType t) {
		return getItem(t.index);
	}
	
	public enum ItemType {
		INFO(0),
		HEADLINE_STATS(1);
		
		public int index;
		
		private ItemType(int index) {
			this.index = index;
		}
	}

}

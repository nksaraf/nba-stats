package stats.api.combine;

import java.util.Map;

import stats.api.connection.Connection;
import stats.api.util.Constants;
import stats.api.util.FieldType;
import stats.api.util.Statistic;

public class CombineSummary extends Statistic {
	
	private static FieldType[] required_fields = {
			FieldType.LEAGUE,
			FieldType.SEASON_YEAR
		};
	
	public CombineSummary(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.COMBINE_SUMMARY.toString(), fields, required_fields);
	}
	
	public CombineSummary(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.COMBINE_SUMMARY.toString(), fields, required_fields, c);
	}
	
	public enum ItemType implements Statistic.ItemType{
		OVERALL(0);
		
		public int index;
		
		private ItemType(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
 	}
}

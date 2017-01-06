package stats.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

public class Player extends Element {

	private Map<String, String> details;
	
	public Player(JSONArray details_array) {
		super(details_array.get(0).toString());
		String[] headers = {"ID", "FORMAL_NAME", "FULL_NAME", "ROSTER_STATUS", "FROM_YEAR", "TO_YEAR", "PLAYER_CODE", "TEAM_ID", "TEAM_CITY", "TEAM_NAME", "TEAM_ABBR", "TEAM_CODE", "GAMES_PLAYED_FLAG"};
		details = new HashMap<String, String>();
		for(int i = 0; i < headers.length; i++) {
			details.put(headers[i], details_array.get(i).toString());
		}
		Map<FieldType, Object> fields = new HashMap<FieldType, Object>();
		fields.put(FieldType.PLAYER_ID, getID());
		features.put(Feature.SUMMARY, new PlayerSummary(fields));
		features.put(Feature.CAREER, new PlayerCareer(fields));
		features.put(Feature.GAME_LOG, new PlayerGameLog(fields));
		features.put(Feature.DASHBOARD, new PlayerDashboard(fields));
		features.put(Feature.SHOT_CHART, new ShotChart(fields));
	}
	
	public String getDetail(String header) {
		return details.get(header);
	}
	
	public StatItem getDashboardItem(PlayerDashboard.ItemType item) {
		PlayerDashboard dashboard = getDashboard();
		dashboard.setType(item.getType());
		return dashboard.getItem(item);
	}
	
	public PlayerDashboard getDashboard(PlayerDashboard.Type type) {
		PlayerDashboard dashboard = getDashboard();
		dashboard.setType(type);
		dashboard.load(StatsFactory.getConnection());
		return dashboard;
	}
	
	public PlayerDashboard getDashboard() {
		return ((PlayerDashboard)getFeature(Feature.DASHBOARD));
	}
	
	public void print() {
		for(String key: details.keySet()) {
			System.out.println(key + "\t" + details.get(key));
		}
		if(getFeature(Feature.SUMMARY).loaded) 
			getFeature(Feature.SUMMARY).print();
	}
	
	public String toString() {
		return getID() + " - " + getDetail("FULL_NAME");
	}
	
	public enum Feature implements Element.Feature {
		
		SUMMARY("summary"),
		GAME_LOG("game_log"),
		DASHBOARD("dashboard"),
		CAREER("career"), 
		SHOT_CHART("shotchart");
		
		private String description;
		
		private Feature(String d) {
			description = d;
		}
		
		public String toString() {
			return description;
		}
	}
	
	
}

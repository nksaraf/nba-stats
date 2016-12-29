package stats;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

import api.Connection;

public class Player {

	private Map<String, String> details;
	private PlayerSummary summary;
	
	public Player(JSONArray details_array) {
		String[] headers = {"ID", "FORMAL_NAME", "FULL_NAME", "ROSTER_STATUS", "FROM_YEAR", "TO_YEAR", "PLAYER_CODE", "TEAM_ID", "TEAM_CITY", "TEAM_NAME", "TEAM_ABBR", "TEAM_CODE", "GAMES_PLAYED_FLAG"};
		details = new HashMap<String, String>();
		for(int i = 0; i < headers.length; i++) {
			details.put(headers[i], details_array.get(i).toString());
		}
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("PlayerID", getID());
		summary = new PlayerSummary(fields);
	}
	
	public String getDetail(String header) {
		return details.get(header);
	}
	
	public String getID() {
		return getDetail("ID");
	}
	
	public void loadSummary(Connection c) {
		summary.load(c);
	}
	
	public PlayerSummary getSummary() {
		return summary;
	}
	
	public void print() {
		for(String key: details.keySet()) {
			System.out.println(key + "\t" + details.get(key));
		}
		if(summary.loaded) 
			summary.print();
	}
	
	public String toString() {
		return getID() + " - " + getDetail("FULL_NAME");
	}
	
	
}

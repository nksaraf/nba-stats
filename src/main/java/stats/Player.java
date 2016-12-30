package stats;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

import api.Connection;

public class Player {

	private Map<String, String> details;
	private PlayerSummary summary;
	private PlayerCareer career;
	private PlayerGameLog game_log;
	private PlayerDashboard dashboard;
	
	public Player(JSONArray details_array) {
		String[] headers = {"ID", "FORMAL_NAME", "FULL_NAME", "ROSTER_STATUS", "FROM_YEAR", "TO_YEAR", "PLAYER_CODE", "TEAM_ID", "TEAM_CITY", "TEAM_NAME", "TEAM_ABBR", "TEAM_CODE", "GAMES_PLAYED_FLAG"};
		details = new HashMap<String, String>();
		for(int i = 0; i < headers.length; i++) {
			details.put(headers[i], details_array.get(i).toString());
		}
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("PlayerID", getID());
		summary = new PlayerSummary(fields);
		career = new PlayerCareer(fields);
		game_log = new PlayerGameLog(fields);
		dashboard = new PlayerDashboard(fields);
	}
	
	public String getDetail(String header) {
		return details.get(header);
	}
	
	public String getID() {
		return getDetail("ID");
	}
	
	public void load(Connection c) {
		loadSummary(c);
		loadCareer(c);
		loadGameLog(c);
		loadDashboard(c);
	}
	
	public void load(PlayerDashboard.Type type, Connection c) {
		loadSummary(c);
		loadCareer(c);
		loadGameLog(c);
		loadDashboard(type, c);
	}
	
	public void loadSummary(Connection c) {
		summary.load(c);
	}
	
	public void loadCareer(Connection c) {
		career.load(c);
	}
	
	public void loadGameLog(Connection c) {
		game_log.load(c);
	}
	
	public void loadDashboard(Connection c) {
		dashboard.load(c);
	}
	
	public void loadDashboard(PlayerDashboard.Type type, Connection c) {
		dashboard.load(type, c);
	}
	
	public void loadCareer(PlayerCareer.Type type, Connection c) {
		career.load(type, c);
	}
	
	public StatItem getCareerItem(PlayerCareer.ItemType item, Connection c) {
		loadCareer(item.type, c);
		return career.getItem(item);
	}
	
	public StatItem getDashboardItem(PlayerDashboard.ItemType item, Connection c) {
		loadDashboard(item.type, c);
		return dashboard.getItem(item);
	}
	
	public PlayerCareer getCareer() {
		return career;
	}
	
	public PlayerSummary getSummary() {
		return summary;
	}
	
	public PlayerGameLog getGameLog() {
		return game_log;
	}
	
	public PlayerDashboard getDashboard() {
		return dashboard;
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

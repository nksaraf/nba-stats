package stats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import api.Connection;

public class StatsFactory {

	private static Connection connection;
	private static PlayerList playerList;
	
	public static void establishConnection() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("referer", "http://stats.nba.com/scores/");
		connection = new Connection("http://stats.nba.com/stats/{endpoint}", headers);
		setUpdatedDefaults();
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static void setUpdatedDefaults() {
		LocalDate date = LocalDate.now();
		int year = date.getYear();
		String seasonYear = year + "-" + (year%100 + 1);
		String month = "" +date.getMonthValue();
		String day = "" + date.getDayOfMonth();
		if(day.length() == 1){
			day = "0" + day;
		}
		if(month.length() == 1) {
			month = "0" + month;
		}
		String dateToday = month + "/" + day + "/" + year;
		FieldType.GAME_DATE.setDefault(dateToday);
		FieldType.SEASON.setDefault(seasonYear);
		FieldType.SEASON_YEAR.setDefault(seasonYear);
	}
	
	public static Statistic getTodayScoreboard() {
		Map<String, Object> fields = new HashMap<String, Object>();
		Scoreboard score = new Scoreboard(fields, connection);
		return score;
	}
	
	public static Game getGame(String game_id) {
		Game game = new Game(game_id, connection);
		return game;
	}
	
	public static PlayerList getPlayerList() {
		if(playerList == null) {
			Map<String, Object> fields = new HashMap<String, Object>();
			playerList = new PlayerList(fields, connection);
		}
		return playerList;
	}
	
	public static Player getPlayer(String category, String value) {
		Player p = getPlayerList().getPlayerBy(category, value);
		p.load(connection);
		return p;
	}
	
	public static List<Player> getPlayers(String category, String value) {
		List<Player> pl= getPlayerList().getPlayersBy(category, value);
		return pl;
	}
}



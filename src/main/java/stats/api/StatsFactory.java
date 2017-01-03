package stats.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import stats.connection.Connection;

public class StatsFactory {

	private static Connection connection;
	private static PlayerList player_list;
	
	public static void establishConnection() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("referer", Constants.SCORES_REFERER.toString());
		connection = new Connection(Constants.API_URL.toString(), headers);
		setUpdatedDefaults();
	}
	
	public static Connection getConnection() {
		if(connection == null) {
			establishConnection();
		}
		return connection;
	}
	
	public static void setUpdatedDefaults() {
		LocalDate date = LocalDate.now();
		int year = date.getYear();
		int month_val = date.getMonthValue();
		if(month_val <= 8) {
			year = year - 1;
		}
		String seasonYear = year + "-" + (year%100 + 1);
		String month = "" +date.getMonthValue();
		String day = "" + date.getDayOfMonth();
		if(day.length() == 1){
			day = "0" + day;
		}
		if(month.length() == 1) {
			month = "0" + month;
		}
		String dateToday = month + "/" + day + "/" + date.getYear();
		FieldType.GAME_DATE.setDefault(dateToday);
		FieldType.SEASON.setDefault(seasonYear);
		FieldType.SEASON_YEAR.setDefault(seasonYear);
	}
	
	public static Statistic getTodayScoreboard() {
		Map<FieldType, Object> fields = new HashMap<FieldType, Object>();
		Scoreboard score = new Scoreboard(fields, connection);
		return score;
	}
	
	public static Statistic getScoreboard(String game_date) {
		Map<FieldType, Object> fields = new HashMap<FieldType, Object>();
		fields.put(FieldType.GAME_DATE, game_date);
		Scoreboard score = new Scoreboard(fields, connection);
		return score;
	}
	
	public static Game getGame(String game_id) {
		Game game = new Game(game_id, connection);
		return game;
	}
	
	public static PlayerList getPlayerList() {
		if(player_list == null) {
			Map<FieldType, Object> fields = new HashMap<FieldType, Object>();
			player_list = new PlayerList(fields, connection);
		}
		return player_list;
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



/**
 * 
 */
package stats;

import java.util.HashMap;
import java.util.Map;

import api.Connection;

/**
 * @author nikhilsaraf
 *
 */
public class Game {

	private BoxScore boxScore;
	private PlayByPlay playByPlay;
	private PlayerTracking playerTracking;
	private String id;
	
	public Game(String game_id) {
		id = game_id;
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("GameId", id);
		boxScore = new BoxScore(fields);
		playByPlay = new PlayByPlay(fields);
		playerTracking = new PlayerTracking(fields);
	}
	
	public void load(BoxScore.Type type, Connection c) {
		loadBoxScore(type, c);
		loadPlayByPlay(c);
		loadPlayerTracking(c);
	}
	
	public void load(Connection c) {
		loadBoxScore(c);
		loadPlayByPlay(c);
		loadPlayerTracking(c);
	}
	
	public void loadPlayByPlay(Connection c) {
		playByPlay.load(c);
	}
	
	public void loadPlayerTracking(Connection c) {
		playerTracking.load(c);
	}
	
	public void loadBoxScore(BoxScore.Type type, Connection c) {
		boxScore.load(type,c);
	}
	
	public void loadBoxScore(Connection c) {
		boxScore.load(c);
	}
	
	public StatItem getBoxScoreItem(BoxScore.ItemType item, Connection c) {
		loadBoxScore(item.type, c);
		return boxScore.getItem(item);
	}
	
	public BoxScore getBoxScore() {
		return boxScore;
	}
	
	public PlayByPlay getPlayByPlay() {
		return playByPlay;
	}
	
	public PlayerTracking getPlayerTracking() {
		return playerTracking;
	}
	
}

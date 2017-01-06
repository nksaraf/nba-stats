/**
 * 
 */
package stats.api.game;

import stats.api.StatsFactory;
import stats.api.connection.Connection;
import stats.api.util.Element;
import stats.api.util.FieldType;
import stats.api.util.StatItem;

/**
 * @author nikhilsaraf
 *
 */
public class Game extends Element{
	
	public Game(String game_id) {
		super(game_id);
		fields.put(FieldType.GAME_ID, game_id);
		features.put(Feature.BOXSCORE, new BoxScore(fields));
		features.put(Feature.PLAY_BY_PLAY, new PlayByPlay(fields));
		features.put(Feature.PLAYER_TRACKING, new PlayerTracking(fields));
	}
	
	public Game(String game_id, Connection c) {
		this(game_id);
		load(c);
	}
	
	public BoxScore getBoxScore(BoxScore.Type type) {
		BoxScore box_score = getBoxScore();
		box_score.setType(type);
		box_score.load(StatsFactory.getConnection());
		return box_score;
	}
	
	public BoxScore getBoxScore() {
		return ((BoxScore)getFeature(Feature.BOXSCORE));
	}
	
	public StatItem getBoxScoreItem(BoxScore.ItemType item) {
		BoxScore box_score = ((BoxScore)getFeature(Feature.BOXSCORE));
		return box_score.getItem(item);
	}

	public enum Feature implements Element.Feature {
		
		BOXSCORE("box_score"),
		PLAY_BY_PLAY("play_by_play"),
		PLAYER_TRACKING("player_tracking");
		
		private String description;
		
		private Feature(String d) {
			description = d;
		}
		
		public String toString() {
			return description;
		}
	}
	
}

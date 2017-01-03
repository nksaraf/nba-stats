/**
 * 
 */
package stats.api;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import stats.connection.Connection;

/**
 * @author nikhilsaraf
 *
 */
public class PlayerList extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.LEAGUE,
			FieldType.SEASON,
			FieldType.ONLY_CURRENT
		};
	
	private List<Player> players;
	/**
	 * @param endpoint
	 * @param fields
	 * @param required_fields
	 */
	public PlayerList(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.PLAYER_LIST.toString(), fields, required_fields);
	}
	
	public PlayerList(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.PLAYER_LIST.toString(), fields, required_fields, c);
		load(c);
	}
	
	@Override
	public void load(Connection c) {
		super.load(c);
		players = new LinkedList<Player>();
		for(JSONArray row: statItems.get(0).rows) {
			players.add(new Player(row));
		}
	}
	
	public Player getPlayerByName(String name) {
		Player p = getPlayerBy("FORMAL_NAME", name);
		if(p == null) {
			p = getPlayerBy("FULL_NAME", name);
		}
		return p;
	}
	
	public Player getPlayerById(String id) {
		return getPlayerBy("ID", id);
	}
	
	public Player getPlayerBy(String category, String value) {
		Player player = null;
		for(Player p: players) {
			if(p.getDetail(category).equals(value)) 
				player = p;
		}
		return player;
	}
	
	public List<Player> getPlayersBy(String category, String value) {
		List<Player> playerList = new LinkedList<Player>();
		for(Player p: players) {
			if(category.equals("FORMAL_NAME") || category.equals("FULL_NAME")) {
				if(p.getDetail(category).toLowerCase().contains(value.toLowerCase())) playerList.add(p);
			}
			else {
				if(p.getDetail(category).equals(value)) 
					playerList.add(p);
			}
		}
		return playerList;
	}
	
	public String toString() {
		return players.toString();
	}

}

package stats.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import stats.connection.Connection;
      
/**
 * @author nikhilsaraf
 *
 */
public class BoxScore extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.GAME_ID, 
			FieldType.SEASON, 
			FieldType.SEASON_TYPE,
			FieldType.RANGE_TYPE,
			FieldType.START_PERIOD,
			FieldType.END_PERIOD,
			FieldType.START_RANGE,
			FieldType.END_RANGE };
	
	private Type type;
	
	public BoxScore(String boxscore_type, Map<FieldType, Object> fields) {
		super(Constants.Endpoints.BOX_SCORE.toString() + boxscore_type, fields, required_fields);
		type = Type.getTypeFromString(boxscore_type);
	}
	
	public BoxScore(String boxscore_type, Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.BOX_SCORE.toString(), fields, required_fields, c);
		type = Type.getTypeFromString(boxscore_type);
	}

	public BoxScore(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.BOX_SCORE.toString() + Type.SUMMARY.toString(), fields, required_fields);
		type = Type.SUMMARY;
	}
	
	public BoxScore(Type type, Map<FieldType, Object> fields) {
		super(Constants.Endpoints.BOX_SCORE.toString() + type.toString(), fields, required_fields);
		this.type = type;
	}
	
	public BoxScore(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.BOX_SCORE.toString() + Type.SUMMARY.toString(), fields, required_fields, c);
		type = Type.SUMMARY;
	}
	
	public BoxScore(Type type,Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.BOX_SCORE.toString() + type.toString(), fields, required_fields, c);
		this.type = type;
	}
	
	public void setType(String boxscore_type) {
		type = Type.getTypeFromString(boxscore_type);
		loaded = false;
	}
	
	public void setType(Type t) {
		type = t;
		loaded = false;
	}

	public StatItem getItem(ItemType item) {
		if(type == item.getType()) {
			return getItem(item.getIndex());
		} else return null;
	}

	public Type getType() {
		return type;
	}
	
	public enum Type {
		SUMMARY("summaryv2"),
		TRADITIONAL("traditionalv2"),
		SCORING("scoringv2"),
		ADVANCED("advancedv2"),
		MISC("miscv2"),
		USAGE("usagev2"),
		FOUR_FACTORS("fourfactorsv2");
		
		private String description;
		private static Map<String, Type> mapStringToType;
		static {
			Map<String, Type> temp = new HashMap<String, Type>();
			for (Type t: Type.values()) {
				temp.put(t.description, t);
			}
			mapStringToType = Collections.unmodifiableMap(temp);
			
		}
		private Type(String desc) {
			this.description = desc;
		}
		
		public String toString() {
			return description;
		}
		
		public static Type getTypeFromString(String type) {
			return mapStringToType.get(type);
		}	
	}
	
	public enum ItemType implements Statistic.ItemType{
		GAME_SUMMARY(Type.SUMMARY, 0),
		OTHER_STATS(Type.SUMMARY, 1),
		OFFICIALS(Type.SUMMARY, 2),
		INACTIVE_PLAYERS(Type.SUMMARY, 3),
		GAME_INFO(Type.SUMMARY, 4),
		LINE_SCORE(Type.SUMMARY, 5),
		LAST_MEETING(Type.SUMMARY, 6),
		SEASON_SERIES(Type.SUMMARY, 7),
		AVAILABLE_VIDEO(Type.SUMMARY, 8),
		
		PLAYER_STATS(Type.TRADITIONAL, 0),
		TEAM_STATS(Type.TRADITIONAL, 1),
		TEAM_STARTER_BENCH_STATS(Type.TRADITIONAL, 2),
		
		SQL_PLAYERS_SCORING(Type.SCORING, 0),
		SQL_TEAM_SCORING(Type.SCORING, 1),
		
		SQL_PLAYERS_USAGE(Type.USAGE, 0),
		SQL_TEAM_USAGE(Type.USAGE, 1),
		
		SQL_PLAYERS_ADVANCED(Type.ADVANCED, 0),
		SQL_TEAM_FOUR_ADVANCED(Type.ADVANCED, 1),
		
		SQL_PLAYERS_MISC(Type.MISC, 0),
		SQL_TEAM_MISC(Type.MISC, 1),
		
		SQL_PLAYERS_FOUR_FACTORS(Type.FOUR_FACTORS, 0),
		SQL_TEAM_FOUR_FACTORS(Type.FOUR_FACTORS, 1);
		
		private Type type;
		private int index;
		private static Map<Type, List<ItemType>> mapTypeToItemTypes;
		
		static {
			Map<Type, List<ItemType>> temp = new HashMap<Type, List<ItemType>>();
			for(Type t : Type.values()) {
				temp.put(t, new LinkedList<ItemType>());
			}
			for(ItemType item: ItemType.values()) {
				temp.get(item.type).add(item);
			}
			mapTypeToItemTypes = Collections.unmodifiableMap(temp);
		}
		
		private ItemType(Type type, int index) {
			this.index = index;
			this.type = type;
		}
		
		public List<ItemType> getItemTypes(Type t) {
			return mapTypeToItemTypes.get(t);
		}
		
		public Type getType() {
			return type;
		}
		
		public int getIndex() {
			return index;
		}
	}
	
	

}

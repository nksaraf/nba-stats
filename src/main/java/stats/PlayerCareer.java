/**
 * 
 */
package stats;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import api.Connection;

/**
 * @author nikhilsaraf
 *
 */
public class PlayerCareer extends Statistic {

	private static FieldType[] required_fields = {
			FieldType.PLAYER_ID, 
			FieldType.LEAGUE, 
			FieldType.PER_MODE };
	
	private Type type;
	/**
	 * @param endpoint
	 * @param fields
	 * @param required_fields
	 */
	public PlayerCareer(String stat_type, Map<String, Object> fields) {
		super("player" + stat_type, fields, required_fields);
		type = Type.getTypeFromString(stat_type);
	}
	
	public PlayerCareer(String stat_type, Map<String, Object> fields, Connection c) {
		super("player" + stat_type, fields, required_fields);
		type = Type.getTypeFromString(stat_type);
		load(c);
	}

	/**
	 * @param fields
	 * @param required_fields
	 */
	public PlayerCareer(Map<String, Object> fields) {
		super("playercareerstats", fields, required_fields);
		type = Type.CAREER;
	}
	
	public PlayerCareer(Type type, Map<String, Object> fields) {
		super("player" + type.description, fields, required_fields);
		this.type = type;
	}
	
	public PlayerCareer(Map<String, Object> fields, Connection c) {
		super("playercareerstats", fields, required_fields, c);
		type = Type.CAREER;
	}
	
	public PlayerCareer(Type type, Map<String, Object> fields, Connection c) {
		super("player" + type.description, fields, required_fields, c);
		this.type = type;
	}
	
	@Override 
	public void load(String stat_type, Connection c) {
		type = Type.getTypeFromString(stat_type);
		super.load(stat_type, c);
	}
	
	public void load(Type t, Connection c) {
		type = t;
		super.load(t.description, c);
	}
	
	public StatItem getItem(ItemType item) {
		if(type == item.type) {
			return getItem(item.index);
		} else return null;
	}

	public Type getType() {
		return type;
	}
	
	public enum Type {
		CAREER("careerstats"),
		PROFILE("profilev2");
		
		public String description;
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
		
		public static Type getTypeFromString(String type) {
			return mapStringToType.get(type);
		}	
	}
	
	public enum ItemType {
		REG_SEASON_TOTALS(Type.CAREER, 0),
		REG_SEASON_CAREER_TOTALS(Type.CAREER, 1),
		POST_SEASON_TOTALS(Type.CAREER, 2),
		POST_SEASON_CAREER_TOTALS(Type.CAREER, 3),
		ALLSTAR_SEASON_TOTALS(Type.CAREER, 4),
		ALLSTAR_SEASON_CAREER_TOTALS(Type.CAREER, 5),
		COLLEGE_SEASON_TOTALS(Type.CAREER, 6),
		COLLEGE_SEASON_CAREER_TOTALS(Type.CAREER, 7),
		REG_SEASON_RANKINGS(Type.CAREER, 8),
		POST_SEASON_RANKINGS(Type.CAREER, 9),
		
		SEASON_HIGHS(Type.PROFILE, 0),
		CAREER_HIGHS(Type.PROFILE, 1),
		NEXT_GAME(Type.PROFILE, 2);
		
		public Type type;
		public int index;
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
	}

}

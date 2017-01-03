/**
 * 
 */
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
	public PlayerCareer(String stat_type, Map<FieldType, Object> fields) {
		super(Constants.Endpoints.PLAYER.toString() + stat_type, fields, required_fields);
		type = Type.getTypeFromString(stat_type);
	}
	
	public PlayerCareer(String stat_type, Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.PLAYER.toString() + stat_type, fields, required_fields, c);
		type = Type.getTypeFromString(stat_type);
	}

	/**
	 * @param fields
	 * @param required_fields
	 */
	public PlayerCareer(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.PLAYER.toString() + Type.CAREER.toString(), fields, required_fields);
		type = Type.CAREER;
	}
	
	public PlayerCareer(Type type, Map<FieldType, Object> fields) {
		super(Constants.Endpoints.PLAYER.toString() + type.toString(), fields, required_fields);
		this.type = type;
	}
	
	public PlayerCareer(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.PLAYER.toString() + Type.CAREER.toString(), fields, required_fields, c);
		type = Type.CAREER;
	}
	
	public PlayerCareer(Type type, Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.PLAYER.toString() + type.toString(), fields, required_fields, c);
		this.type = type;
	}
	
	public void setType(String stat_type) {
		type = Type.getTypeFromString(stat_type);
		loaded = false;
	}
	
	public void setType(Type t) {
		type = t;
		loaded = false;
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

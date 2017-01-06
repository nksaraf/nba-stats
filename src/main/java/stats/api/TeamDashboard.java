package stats.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import stats.connection.Connection;

public class TeamDashboard extends Dashboard {

	public TeamDashboard(Map<FieldType, Object> fields) {
		super(Constants.Endpoints.TEAM.toString() + Type.GENERAL_SPLITS.toString(), fields);
		type = Type.GENERAL_SPLITS;
	}
	
	public TeamDashboard(Type type, Map<FieldType, Object> fields) {
		super(Constants.Endpoints.TEAM.toString() + type.toString(), fields);
		this.type = type;
	}
	
	public TeamDashboard(Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.TEAM.toString() + Type.GENERAL_SPLITS.toString(), fields, c);
		type = Type.GENERAL_SPLITS;
	}
	
	public TeamDashboard(Type type, Map<FieldType, Object> fields, Connection c) {
		super(Constants.Endpoints.PLAYER.toString() + type.toString(), fields, c);
		this.type = type;
	}
	
	public void setType(String dashboard_type) {
		type = Type.getTypeFromString(dashboard_type);
		setEndpoint(Constants.Endpoints.TEAM.toString() + type.toString());
		loaded = false;
	}
	
	public void setType(Type t) {
		type = t;
		setEndpoint(Constants.Endpoints.TEAM.toString() + type.toString());
		loaded = false;
	}
	
	public enum Type implements Dashboard.Type {
		GENERAL_SPLITS("dashboardbygeneralsplits"),
		OPPONENT_SPLITS("dashboardbyopponent"),
		LAST_N_GAMES_SPLITS("dashboardbylastngames"),
		SHOOTING_SPLITS("dashboardbyshootingsplits"),
		IN_GAME_SPLITS("dashboardbygamesplits"),
		CLUTCH_SPLITS("dashboardbyclutch"),
		TEAM_PERFORMANCE_SPLITS("dashboardbyteamperformace"),
		YEAR_OVER_YEAR_SPLITS("dashboardbyyearoveryear"),
		PLAYERS("playerdashboard"),
		PLAYER_ON_OFF_DETAILS("playeronoffdetails"),
		PLAYER_ON_OFF_SUMMARY("playeronoffsummary"),
		SHOT_TRACKING("dashptshots"),
		PASS_TRACKING("dashptpass"),
		REBOUND_TRACKING("dashptreb");
		
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
	
	public enum ItemType implements Dashboard.ItemType {
		OVERALL_GENERAL(Type.GENERAL_SPLITS, 0),
		LOCATION(Type.GENERAL_SPLITS, 1),
		WIN_LOSSES(Type.GENERAL_SPLITS, 2),
		MONTHLY(Type.GENERAL_SPLITS, 3),
		PRE_POST_ALLSTAR(Type.GENERAL_SPLITS, 4),
		DAYS_REST(Type.GENERAL_SPLITS, 5),
		
		OVERALL_OPPONENT(Type.OPPONENT_SPLITS, 0),
		BY_CONFERENCE(Type.OPPONENT_SPLITS, 1),
		BY_DIVISION(Type.OPPONENT_SPLITS, 2),
		BY_OPPONENT(Type.OPPONENT_SPLITS, 3),
		
		OVERALL_LAST_N_GAMES(Type.LAST_N_GAMES_SPLITS, 0),
		LAST_5(Type.LAST_N_GAMES_SPLITS, 1),
		LAST_10(Type.LAST_N_GAMES_SPLITS, 2),
		LAST_15(Type.LAST_N_GAMES_SPLITS, 3),
		LAST_20(Type.LAST_N_GAMES_SPLITS, 4),
		GAME_NUMBER(Type.LAST_N_GAMES_SPLITS, 5),
		
		OVERALL_IN_GAME(Type.IN_GAME_SPLITS, 0),
		BY_HALF(Type.IN_GAME_SPLITS, 1),
		BY_PERIOD(Type.IN_GAME_SPLITS, 2), 
		BY_SCORE_MARGIN(Type.IN_GAME_SPLITS, 3),
		BY_ACTUAL_MARGIn(Type.IN_GAME_SPLITS, 4),
		
		OVERALL_SHOOTING(Type.SHOOTING_SPLITS, 0),
		SHOT_5FT(Type.SHOOTING_SPLITS, 1),
		SHOT_8FT(Type.SHOOTING_SPLITS, 2),
		SHOT_AREAS(Type.SHOOTING_SPLITS, 3),
		ASSISSTED_SHOTS(Type.SHOOTING_SPLITS, 4),
		SHOT_TYPES_SUMMARY(Type.SHOOTING_SPLITS, 5),
		ASSISSTED_BY(Type.SHOOTING_SPLITS, 6),
		
		OVERALL_TEAM_PERFORMANCE(Type.TEAM_PERFORMANCE_SPLITS, 0),
		SCORE_DIFFERENTIAL(Type.TEAM_PERFORMANCE_SPLITS, 1),
		POINTS_SCORED(Type.TEAM_PERFORMANCE_SPLITS, 2),
		POITNS_AGAINST(Type.TEAM_PERFORMANCE_SPLITS, 3),
		
		OVERALL_YEAR_OVER_YEAR(Type.YEAR_OVER_YEAR_SPLITS, 0),
		BY_YEAR(Type.YEAR_OVER_YEAR_SPLITS, 1),
		
		OVERALL_CLUTCH(Type.CLUTCH_SPLITS, 0),
		LAST5MIN_DEFICIT_5PT(Type.CLUTCH_SPLITS, 1),
		LAST3MIN_DEFICIT_5PT(Type.CLUTCH_SPLITS, 2),
		LAST1MIN_DEFICIT_5PT(Type.CLUTCH_SPLITS, 3),
		LAST30SEC_DEFICIT_3PT(Type.CLUTCH_SPLITS, 4),
		LAST10SEC_DEFICIT_3PT(Type.CLUTCH_SPLITS, 5),
		LAST5MIN_PLUSMINUS_5PT(Type.CLUTCH_SPLITS, 6),
		LAST3MIN_PLUSMINUS_5PT(Type.CLUTCH_SPLITS, 7),
		LAST1MIN_PLUSMINUS_5PT(Type.CLUTCH_SPLITS, 8),
		LAST30SEC_PLUSMINUS_3PT(Type.CLUTCH_SPLITS, 9),
		//LAST10SEC_PLUSMINUS_3PT(Type.CLUTCH_SPLITS, 10),
		
		OVERALL_PLAYERS(Type.PLAYERS, 0),
		SEASON_TOTALS_PLAYERS(Type.PLAYERS, 1),
		
		OVERALL_ON_OFF_SUMMARY(Type.PLAYER_ON_OFF_SUMMARY, 0),
		ON_COURT_SUMMARY(Type.PLAYER_ON_OFF_SUMMARY, 1),
		OFF_COURT_SUMMARY(Type.PLAYER_ON_OFF_SUMMARY, 2),
		
		OVERALL_ON_OFF_DETAILS(Type.PLAYER_ON_OFF_DETAILS, 0),
		ON_COURT_DETAILS(Type.PLAYER_ON_OFF_DETAILS, 1),
		OFF_COURT_DETAILS(Type.PLAYER_ON_OFF_DETAILS, 2),
		
		OVERALL_SHOT_TRACKING(Type.SHOT_TRACKING, 0),
		SHOT_CLOCK_SHOOTING(Type.SHOT_TRACKING, 1),
		DRIBBLE_SHOOTING(Type.SHOT_TRACKING, 2),
		CLOSEST_DEFENDER_SHOOTING(Type.SHOT_TRACKING, 3),
		CLOSEST_DEFENDER_SHOOTING_LONG(Type.SHOT_TRACKING, 4),
		TOUCH_TIME_SHOOTING(Type.SHOT_TRACKING, 5),
		
		OVERALL_REB_TRACKING(Type.REBOUND_TRACKING, 0),
		SHOT_TYPE_REBOUNDING(Type.REBOUND_TRACKING, 1),
		CONTESTED_REBOUNDING(Type.REBOUND_TRACKING, 2),
		SHOT_DISTANCE_REBOUNDING(Type.REBOUND_TRACKING, 3),
		REBOUND_DISTANCE_REBOUNDING(Type.REBOUND_TRACKING, 4),
		
		OVERALL_PASS_TRACKING(Type.PASS_TRACKING, 0),
		PASSES_MADE(Type.PASS_TRACKING, 1),
		PASSES_RECEIVED(Type.PASS_TRACKING, 2);
		
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

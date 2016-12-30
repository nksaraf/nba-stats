package stats;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum FieldType {
	
	SEASON_YEAR("SeasonYear", ""),
	SEASON("Season", ""),
	LEAGUE("LeagueID", "00", "01"),
	PLAYER_ID("PlayerID", ""),
	PER_MODE("PerMode", "PerGame", "Totals", "MinutesPer", "Per48", "Per40", "Per36", "PerMinute", "PerPossession", "PerPlay", "Per100Possessions", "Per100Plays"),
	SEASON_TYPE("SeasonType", "Regular Season", "Pre Season", "Playoffs", "All-Star", "All Star", "Preseason"),
	MEASURE_TYPE("MeasureType", "Base", "Advanced", "Misc", "Four Factors", "Scoring", "Opponent", "Usage"),
	PT_MEASURE_TYPE("PtMeasureType", "SpeedDistance"),
	GROUP_QUANTITY("GroupQuantity", "5"),
	OUTCOME("Outcome", "", "W", "L"),
	LOCATION("Location", "", "Home", "Away"),
	SEASON_SEGMENT("SeasonSegment", "", "Pre All-Star", "Post All-Star"),
	DATE_FROM("DateFrom", ""),
	DATE_TO("DateTo", ""),
	VS_CONFERENCE("VsConference", "", "East", "West"),
	VS_DIVISION("VsDivision", "", "Atlantic", "Central", "NorthWest", "Pacific", "Southeast", "Southwest"),
	GAME_SEGMENT("GameSegment", "", "First Half", "Second Half", "Overtime"),
	CLUTCH_TIME("ClutchTime", "", "Last 5 Minutes", "Last 4 Minutes", "Last 3 Minutes", "Last 2 Minutes", "Last 1 Minutes", "Last 30 Seconds", "Last 10 Seconds"),
	SHOTCLOCK_RANGE("ShotClockRange", "", "ShotClock Off", "24-22", "22-18 Very Early", "18-15 Early", "15-7 Average", "7-4 Late", "4-0 Very Late"),
	AHEAD_BEHIND("AheadBehind", "", "Ahead or Behind", "Ahead or Tied", "Behind or Tied"),
	PLUS_MINUS("PlusMinus", "N", "Y"),
	PACE_ADJUST("PaceAdjust", "N", "Y"),
	RANK("Rank", "N", "Y"),
	STAT_TYPE("StatType", "Traditional", "Advanced", "Tracking"),
	OPPONENT_TEAM_ID("OpponentTeamID", "0"),
	PERIOD("Period", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"),
	LAST_N_GAMES("LastNGames", "0"),
	PLAYOFF_ROUND("PORound", "0", "1", "2", "3", "4"),
	MONTH("Month", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"),
	RANGE_TYPE("RangeType", "0"),
	START_RANGE("StartRange", "0"),
	END_RANGE("EndRange", "0"),
	START_PERIOD("StartPeriod", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"),
	END_PERIOD("EndPeriod", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"),
	STAT_CATEGORY("StatCategory", "PTS", "FGM", "FGA", "FG%", "3PM", "3PA", "3P%", "FTM", "FTA", "OREB", "DREB", "REB", "AST", "STL", "BLK", "TOV", "EFF", "AST/TO", "STL/TOV", "PF"),
	CONTEXT_MEASURE("ContextMeasure", "FGM", "FGA", "FG_PCT", "FG3M", "FG3A", "FG3_PCT", "PF", "EFG_PCT", "TS_PCT", "PTS_FB", "PTS_OFF_TOV", "PTS_2ND_CHANCE"),
	SCOPE("Scope", "S", "Rookies"),
	GAMESCOPE("GameScope", "Season", "Last 10", "Yesterday", "Finals"),
	//GAME_SCOPE("")
	PLAYER_SCOPE("PlayerScope", "All Players", "Rookies"),
	PLAYER_OR_TEAM("PlayerOrTeam", "Player", "Team"),
	CONFERENCE("Conference", "", "East", "West"),
	DIVISION("Division", "", "Atlantic", "Central", "NorthWest", "Pacific", "Southeast", "Southwest"),
	TEAM_ID("TeamID", "0"),
	GAME_ID("GameID", ""),
    GAME_DATE("GameDate", ""),
    DAY_OFFSET("DayOffSet", "0"),
	COUNTER("Counter", "1000"),
	SORTER("Sorter", "PTS", "FGM", "FGA", "FG_PCT", "FG3M", "FG3A", "FG3_PCT", "FTM", "FTA", "FT_PCT", "OREB", "DREB", "REB", "AST", "STL", "BLK", "TOV"),
	DIRECTION("Direction", "DESC", "ASC"),
	HEIGHT("Height", ""),
	WEIGHT("Weight", ""),
	COUNTRY("Country", ""),
	COLLEGE("College", ""),
	DRAFT_YEAR("DraftYear", ""),
	DRAFT_PICK("DraftPick", "", "1st Round", "2nd Round", "1st Pick", "Lottery Pick", "Top 5 Pick", "Top 10 Pick", "Top 15 Pick", "Top 20 Pick", "Top 25 Pick", "Picks 11 Thru 20", "Picks 21 Thru 30", "Undrafted"),
	PLAYER_POSITION("PlayerPosition", "", "F", "C", "G"),
	PLAYER_EXPERIENCE("PlayerExperience", "", "Rookie", "Sophomore", "Veteran"),
	STARTER_BENCH("StarterBench", "", "Starter", "Bench"),
	ONLY_CURRENT("IsOnlyCurrentSeason", "1", "0");
	
	private String description;
	private String default_value;
	private String[] values;
	
	private static Map<String, FieldType> mapStringToFieldType;
	static {
		Map<String, FieldType> temp = new HashMap<String, FieldType>();
		for (FieldType t: FieldType.values()) {
			temp.put(t.description, t);
		}
		mapStringToFieldType = Collections.unmodifiableMap(temp);
		
	}
	
	private FieldType(String s, String...v) {
		this.description = s;
		this.default_value = v[0];
		this.values = v;
	}
	
	public String toString() {
		return this.description;
	}
	
	public String getDefault() {
		return this.default_value;
	}
	
	public void setDefault(String value) {
		this.default_value = value;
	}
	
	public static FieldType getFieldTypeFromString(String value) {
		return mapStringToFieldType.get(value);
	}
	
	public String getValue(int index) {
		return values[index];
	}
	
	public boolean possibleValue(String value) {
		if(values.length == 1) {
			return true;
		}
		else {
			if(Arrays.asList(values).contains(value)) {
				return true;
			}
			else {
				return false;
			}
		}
	}
}



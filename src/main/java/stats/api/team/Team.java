/**
 * Classes dealing with organizing information gathered from the NBA Stats API. 
 * 
 * Contains classes for all the different types of information groups that
 * can be gathered from the API. Also contains classes that makes it simpler
 * to make requests from the API and retrieve required information from the 
 * data gathered from the API. 
 */
package stats.api.team;

import stats.api.connection.Connection;
import stats.api.util.Element;
import stats.api.util.FieldType;

/**
 * @author nikhilsaraf
 *
 */
public class Team extends Element{
	
	public Team(String team_id) {
		super(team_id);
		fields.put(FieldType.TEAM_ID, team_id);
		features.put(Feature.DETAILS, new TeamDetails(fields));
		features.put(Feature.SUMMARY, new TeamSummary(fields));
		features.put(Feature.ROSTER, new TeamRoster(fields));
		features.put(Feature.SEASONS, new TeamSeasons(fields));
		features.put(Feature.GAMELOG,  new TeamGameLog(fields));
		features.put(Feature.DASHBOARD,  new TeamDashboard(fields));
		features.put(Feature.LINEUPS, new TeamLineups(fields));
	}
	
	public Team(String team_id, Connection c) {
		this(team_id);
		load(c);
	}
	
//	public StatItem getBoxScoreItem(BoxScore.ItemType item) {
//		BoxScore box_score = ((BoxScore)loadElement(Feature.BOXSCORE.toString()));
//		box_score.setType(item.getType());
//		return box_score.getItem(item);
//	}

	public enum Feature implements Element.Feature {
		
		DETAILS("details"),
		SUMMARY("summary"),
		ROSTER("roster"),
		LINEUPS("lineups"),
		DASHBOARD("dashboard"),
		SEASONS("seasons"),
		GAMELOG("gamelog");
		
		private String description;
		
		private Feature(String d) {
			description = d;
		}
		
		public String toString() {
			return description;
		}
	}
	
}

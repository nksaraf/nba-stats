package stats.cli;

import java.util.Arrays;

import stats.api.StatsFactory;
import stats.api.team.Team;

public class TeamCLI extends ElementCLI {

	static Team team;
	public static void get(String args[]) {
		try {
			if(args[0].equals("-id"))
				team = StatsFactory.getTeam(args[1]);
			else if(args[0].equals("-code"))
				team = StatsFactory.getTeam(StatsFactory.getTeamList().getIDFromCode(args[1]));
			else throw new IllegalArgumentException("No player argument provided [id/name]");
			teamOptions(Arrays.copyOfRange(args, 2, args.length));
			options(Arrays.copyOfRange(args, 2, args.length));
			
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void teamOptions(String args[]) {
		switch(args[0]) {
			case "-sum": {
				stat = team.getFeature(Team.Feature.SUMMARY);
				break;
			}
			case "-seasons": {
				stat = team.getFeature(Team.Feature.SEASONS);
				break;
			}
			case "-roster": {
				stat = team.getFeature(Team.Feature.ROSTER);
				break;
			}
			case "-lineups": {
				stat = team.getFeature(Team.Feature.LINEUPS);
				break;
			}
			case "-log": {
				stat = team.getFeature(Team.Feature.GAMELOG);
				break;
			}
			case "-details": {
				stat = team.getFeature(Team.Feature.DETAILS);
				break;
			}
			case "-dash": {
				stat = team.getFeature(Team.Feature.DASHBOARD);
				break;
			}
			default: {
				//dashboardOptions(args[0]);
			}
		}
	}
//	
//	static void dashboardOptions(String arg) {
//		Map<String, PlayerDashboard.Type> dash = new HashMap<String, PlayerDashboard.Type>();
//		dash.put("-dash-gen", PlayerDashboard.Type.GENERAL_SPLITS);
//		dash.put("-dash-opp", PlayerDashboard.Type.OPPONENT_SPLITS);
//		dash.put("-dash-lastn", PlayerDashboard.Type.LAST_N_GAMES_SPLITS);
//		dash.put("-dash-shoot", PlayerDashboard.Type.SHOOTING_SPLITS);
//		dash.put("-dash-ingame", PlayerDashboard.Type.IN_GAME_SPLITS);
//		dash.put("-dash-clutch", PlayerDashboard.Type.CLUTCH_SPLITS);
//		dash.put("-dash-perf", PlayerDashboard.Type.TEAM_PERFORMANCE_SPLITS);
//		dash.put("-dash-year", PlayerDashboard.Type.YEAR_OVER_YEAR_SPLITS);
//		dash.put("-dash-shottr", PlayerDashboard.Type.SHOT_TRACKING);
//		dash.put("-dash-passtr", PlayerDashboard.Type.PASS_TRACKING);
//		dash.put("-dash-rebtr", PlayerDashboard.Type.REBOUND_TRACKING);
//		dash.put("-dash-deftr", PlayerDashboard.Type.DEFENSE_TRACKING);
//		dash.put("-dash-shotlogtr", PlayerDashboard.Type.SHOT_LOG_TRACKING);
//		dash.put("-dash-reblogtr", PlayerDashboard.Type.REBOUND_LOG_TRACKING);
//		
//		stat = player.getDashboard(dash.get(arg));
//		
//	}
}

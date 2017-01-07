package stats.cli;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import stats.api.StatsFactory;
import stats.api.team.Team;
import stats.api.team.TeamDashboard;
import stats.api.team.Teams;

public class TeamCLI extends ElementCLI {

	static Team team;

	static Map<String, TeamDashboard.Type> dash;
	static {
		dash = new HashMap<String, TeamDashboard.Type>();
		dash.put("-dash-gen", TeamDashboard.Type.GENERAL_SPLITS);
		dash.put("-dash-opp", TeamDashboard.Type.OPPONENT_SPLITS);
		dash.put("-dash-lastn", TeamDashboard.Type.LAST_N_GAMES_SPLITS);
		dash.put("-dash-shoot", TeamDashboard.Type.SHOOTING_SPLITS);
		dash.put("-dash-ingame", TeamDashboard.Type.IN_GAME_SPLITS);
		dash.put("-dash-clutch", TeamDashboard.Type.CLUTCH_SPLITS);
		dash.put("-dash-perf", TeamDashboard.Type.TEAM_PERFORMANCE_SPLITS);
		dash.put("-dash-year", TeamDashboard.Type.YEAR_OVER_YEAR_SPLITS);
		dash.put("-dash-shotpt", TeamDashboard.Type.SHOT_TRACKING);
		dash.put("-dash-passpt", TeamDashboard.Type.PASS_TRACKING);
		dash.put("-dash-rebpt", TeamDashboard.Type.REBOUND_TRACKING);
		dash.put("-dash-players", TeamDashboard.Type.PLAYERS);
		dash.put("-dash-onoffsum", TeamDashboard.Type.PLAYER_ON_OFF_SUMMARY);
		dash.put("-dash-onoffdet", TeamDashboard.Type.PLAYER_ON_OFF_DETAILS);
	}

	public static void get(String args[]) {
		try {
			if (args[0].equals("-id"))
				team = StatsFactory.getTeam(args[1]);
			else if (args[0].equals("-code"))
				team = StatsFactory.getTeam(Teams.getTeamFromCode(args[1]).getID());
			else
				throw new IllegalArgumentException("No player argument provided [id/name]");
			teamOptions(Arrays.copyOfRange(args, 2, args.length));
			options(Arrays.copyOfRange(args, 2, args.length));

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	static void teamOptions(String args[]) {
		switch (args[0]) {
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
			if (dash.get(args[0]) == null)
				throw new IllegalArgumentException("Illegal player element option provided.");
			stat = team.getDashboard(dash.get(args[0]));
		}
		}
	}
}

package stats.cli;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import stats.api.StatsFactory;
import stats.api.player.Player;
import stats.api.player.PlayerDashboard;

public class PlayerCLI extends ElementCLI {

	static Player player;
	static Map<String, PlayerDashboard.Type> dash;
	static {
		dash = new HashMap<String, PlayerDashboard.Type>();
		dash.put("-dash-gen", PlayerDashboard.Type.GENERAL_SPLITS);
		dash.put("-dash-opp", PlayerDashboard.Type.OPPONENT_SPLITS);
		dash.put("-dash-lastn", PlayerDashboard.Type.LAST_N_GAMES_SPLITS);
		dash.put("-dash-shoot", PlayerDashboard.Type.SHOOTING_SPLITS);
		dash.put("-dash-ingame", PlayerDashboard.Type.IN_GAME_SPLITS);
		dash.put("-dash-clutch", PlayerDashboard.Type.CLUTCH_SPLITS);
		dash.put("-dash-perf", PlayerDashboard.Type.TEAM_PERFORMANCE_SPLITS);
		dash.put("-dash-year", PlayerDashboard.Type.YEAR_OVER_YEAR_SPLITS);
		dash.put("-dash-shotpt", PlayerDashboard.Type.SHOT_TRACKING);
		dash.put("-dash-passpt", PlayerDashboard.Type.PASS_TRACKING);
		dash.put("-dash-rebpt", PlayerDashboard.Type.REBOUND_TRACKING);
		dash.put("-dash-defpt", PlayerDashboard.Type.DEFENSE_TRACKING);
		dash.put("-dash-shotlogpt", PlayerDashboard.Type.SHOT_LOG_TRACKING);
		dash.put("-dash-reblogpt", PlayerDashboard.Type.REBOUND_LOG_TRACKING);
	}
	public static void get(String args[]) {
		try {
			if(args[0].equals("-id"))
				player = StatsFactory.getPlayer("ID", args[1]);
			else if(args[0].equals("-code"))
				player = StatsFactory.getPlayer("PLAYER_CODE", args[1]);
			else throw new IllegalArgumentException("No player argument provided [id/code]");
			if(player == null) throw new IllegalArgumentException("The player id/code was not recognized");
			playerOptions(Arrays.copyOfRange(args, 2, args.length));
			options(Arrays.copyOfRange(args, 2, args.length));
			
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void playerOptions(String args[]) {
		switch(args[0]) {
			case "-sum": {
				stat = player.getFeature(Player.Feature.SUMMARY);
				break;
			}
			case "-log": {
				stat = player.getFeature(Player.Feature.GAME_LOG);
				break;
			}
			case "-dash": {
				stat = player.getDashboard();
				break;
			}
			case "-career": {
				stat = player.getFeature(Player.Feature.CAREER);
				break;
			}
			case "-chart": {
				stat = player.getFeature(Player.Feature.SHOT_CHART);
				break;
			}
			default: {
				if(dash.get(args[0]) == null) throw new IllegalArgumentException("Illegal player element option provided.");
				stat = player.getDashboard(dash.get(args[0]));
				
			}
		}
		if(stat == null) {
			throw new IllegalArgumentException("Illegal player element option provided.");
		}
	}
	
}

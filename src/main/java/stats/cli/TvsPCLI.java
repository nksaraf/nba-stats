package stats.cli;

import java.util.Arrays;

import stats.api.StatsFactory;
import stats.api.team.TeamVsPlayer;

public class TvsPCLI extends ElementCLI {

public TeamVsPlayer tvp;
	
	public static void get(String args[]) {
		try {
			String t, p;
			if(args[0].equals("-id"))
				t = args[1];
			else if(args[0].equals("-code"))
				t = StatsFactory.getTeamList().getIDFromCode(args[1]);
			else throw new IllegalArgumentException("No player argument provided [id/code]");
			
			if(args[2].equals("-id"))
				p = args[3];
			else if(args[2].equals("-code"))
				p = StatsFactory.getPlayer("PLAYER_CODE", args[3]).getID();
			else throw new IllegalArgumentException("No player argument provided [id/code]");
			if(t == null || p == null) throw new IllegalArgumentException("The player id/code was not recognized");
			stat = StatsFactory.getTeamVsPlayer(t, p);
			options(Arrays.copyOfRange(args, 4, args.length));
			
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
}

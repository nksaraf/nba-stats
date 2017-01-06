package stats.cli;

import java.util.Arrays;

import stats.api.StatsFactory;
import stats.api.player.PlayerVsPlayer;

public class PvsPCLI extends ElementCLI {

	public PlayerVsPlayer pvp;
	
	public static void get(String args[]) {
		try {
			String p1, p2;
			if(args[0].equals("-id"))
				p1 = args[1];
			else if(args[0].equals("-code"))
				p1 = StatsFactory.getPlayer("PLAYER_CODE", args[1]).getID();
			else throw new IllegalArgumentException("No player argument provided [id/code]");
			
			if(args[2].equals("-id"))
				p2 = args[3];
			else if(args[2].equals("-code"))
				p2 = StatsFactory.getPlayer("PLAYER_CODE", args[3]).getID();
			else throw new IllegalArgumentException("No player argument provided [id/code]");
			if(p1 == null || p2 == null) throw new IllegalArgumentException("The player id/code was not recognized");
			stat = StatsFactory.getPlayerVsPlayer(p1, p2);
			options(Arrays.copyOfRange(args, 4, args.length));
			
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
}

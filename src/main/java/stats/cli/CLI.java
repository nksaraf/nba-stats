package stats.cli;

import java.util.Arrays;

import stats.api.StatsFactory;

public class CLI {
	
	public static void main(String args[]) {
		StatsFactory.establishConnection();
		try {
			if(args.length == 0) throw new IllegalArgumentException("No arguments provided");
			switch(args[0]) {
			case "score": {
				ScoreboardCLI.get(Arrays.copyOfRange(args, 1, args.length));
				break;
			}
			case "player": {
				
			}
			case "game": {
				
			}
			case "team": {
				
			}
			default: {
				throw new IllegalArgumentException("Illegul element argument. [score/player/game/team expected]");
			}
			
				
			}
			
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
}

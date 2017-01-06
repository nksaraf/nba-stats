package stats.cli;

import java.util.Arrays;

import stats.api.Game;
import stats.api.StatsFactory;

public class GameCLI extends ElementCLI {

	static Game game;
	public static void get(String args[]) {
		try {
			
			game = StatsFactory.getGame(args[0]);
			gameOptions(Arrays.copyOfRange(args, 1, args.length));
			
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void gameOptions(String args[]) {
		switch(args[0]) {
		case "-box": {
			stat = game.
		}
		}
	}
	
	static void boxScoreOptions(String arg) {
		switch(arg) {
		
		}
	}
	
	
	
	
}

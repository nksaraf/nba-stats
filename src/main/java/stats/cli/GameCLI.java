package stats.cli;

import java.util.Arrays;

import stats.api.BoxScore;
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
			case "-pbp": {
				stat = game.getFeature(Game.Feature.PLAY_BY_PLAY);
				options(args);
				break;
			}
			case "-pt": {
				stat = game.getFeature(Game.Feature.PLAYER_TRACKING);
				options(args);
				break;
			}
			case "-box": {
				stat = game.getBoxScore();
				options(args);
				break;
			}
			default: {
				boxScoreOptions(args[0]);
				options(args);
			}
		}
	}
	
	static void boxScoreOptions(String arg) {
		switch(arg) {
		case "-box-sum": {
			stat = game.getBoxScore(BoxScore.Type.SUMMARY);
			break;
		}
		case "-box-trad": {
			stat = game.getBoxScore(BoxScore.Type.TRADITIONAL);
			break;
		}
		case "-box-scor": {
			stat = game.getBoxScore(BoxScore.Type.SCORING);
			break;
		}
		case "-box-adv": {
			stat = game.getBoxScore(BoxScore.Type.ADVANCED);
			break;
		}
		case "-box-misc": {
			stat = game.getBoxScore(BoxScore.Type.MISC);
			break;
		}
		case "-box-usage": {
			stat = game.getBoxScore(BoxScore.Type.USAGE);
			break;
		}
		case "-box-four": {
			stat = game.getBoxScore(BoxScore.Type.FOUR_FACTORS);
			break;
		}
		}
	}
	
	
	
	
}

package stats.cli;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import stats.api.StatsFactory;
import stats.api.game.BoxScore;
import stats.api.game.Game;

public class GameCLI extends ElementCLI {

	static Game game;

	static Map<String, BoxScore.Type> box;
	static {
		box = new HashMap<String, BoxScore.Type>();
		box.put("-box-sum", BoxScore.Type.SUMMARY);
		box.put("-box-trad", BoxScore.Type.TRADITIONAL);
		box.put("-box-score", BoxScore.Type.SCORING);
		box.put("-box-adv", BoxScore.Type.ADVANCED);
		box.put("-box-misc", BoxScore.Type.MISC);
		box.put("-box-usage", BoxScore.Type.USAGE);
		box.put("-box-four", BoxScore.Type.FOUR_FACTORS);
	}

	public static void get(String args[]) {
		try {
			game = StatsFactory.getGame(args[0]);
			if (game == null)
				throw new IllegalArgumentException("Wrong game id provided");
			gameOptions(Arrays.copyOfRange(args, 1, args.length));
			options(Arrays.copyOfRange(args, 1, args.length));

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	static void gameOptions(String args[]) {
		switch (args[0]) {
		case "-pbp": {
			stat = game.getFeature(Game.Feature.PLAY_BY_PLAY);
			break;
		}
		case "-pt": {
			stat = game.getFeature(Game.Feature.PLAYER_TRACKING);
			break;
		}
		case "-box": {
			stat = game.getBoxScore();
			break;
		}
		default: {
			if (box.get(args[0]) == null)
				throw new IllegalArgumentException("Illegal game element option provided");
			stat = game.getBoxScore(box.get(args[0]));

		}
		}
		if (stat == null)
			throw new IllegalArgumentException("Illegal game element option provided");
	}

}

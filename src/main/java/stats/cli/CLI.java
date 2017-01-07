package stats.cli;

import java.util.Arrays;

import stats.api.StatsFactory;

public class CLI {

	public static void main(String args[]) {
		StatsFactory.establishConnection();
		try {
			if (args.length == 0)
				throw new IllegalArgumentException("No arguments provided");
			switch (args[0]) {
			case "score": {
				ScoreboardCLI.get(Arrays.copyOfRange(args, 1, args.length));
				break;
			}
			case "player": {
				PlayerCLI.get(Arrays.copyOfRange(args, 1, args.length));
				break;
			}
			case "game": {
				GameCLI.get(Arrays.copyOfRange(args, 1, args.length));
				break;
			}
			case "team": {
				TeamCLI.get(Arrays.copyOfRange(args, 1, args.length));
				break;
			}
			case "teamlist": {
				StatsFactory.getTeamList().print();
				break;
			}
			case "league": {
				LeagueCLI.get(Arrays.copyOfRange(args, 1, args.length));
				break;
			}
			case "combine": {
				CombineCLI.get(Arrays.copyOfRange(args, 1, args.length));
				break;
			}
			case "pvp": {
				PvsPCLI.get(Arrays.copyOfRange(args, 1, args.length));
				break;
			}
			case "tvp": {
				TvsPCLI.get(Arrays.copyOfRange(args, 1, args.length));
				break;
			}
			default: {
				throw new IllegalArgumentException("Illegul element argument. [score/player/game/team expected]");
			}

			}

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

}

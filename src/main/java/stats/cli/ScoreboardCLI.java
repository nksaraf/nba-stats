package stats.cli;

import java.util.Arrays;

import stats.api.StatsFactory;

public class ScoreboardCLI extends ElementCLI {

	public static void get(String args[]) {
		try {
			if (args.length == 0) {
				stat = StatsFactory.getTodayScoreboard();
				options(args);
			} else if (args[0].length() == 10 && args[0].charAt(2) == '/' && args[0].charAt(5) == '/') {
				stat = StatsFactory.getScoreboard(args[0]);
				options(Arrays.copyOfRange(args, 1, args.length));
			} else {
				stat = StatsFactory.getTodayScoreboard();
				options(args);
			}

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

}

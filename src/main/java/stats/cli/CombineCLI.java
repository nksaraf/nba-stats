package stats.cli;

import java.util.Arrays;

import stats.api.StatsFactory;
import stats.api.combine.DraftCombine;
import stats.api.util.FieldType;

public class CombineCLI extends ElementCLI {

	static DraftCombine combine;

	public static void get(String args[]) {
		try {
			if (args[0].charAt(0) == '-') {
				combine = StatsFactory.getCombine(FieldType.SEASON_YEAR.getDefault());
			} else {
				combine = StatsFactory.getCombine(args[0]);
				args = Arrays.copyOfRange(args, 1, args.length);
			}
			if (combine == null)
				throw new IllegalArgumentException("Combine not loaded");
			combineOptions(args);
			options(args);

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	static void combineOptions(String args[]) {
		switch (args[0]) {
		case "-sum": {
			stat = combine.getFeature(DraftCombine.Feature.SUMMARY);
			break;
		}
		case "-drill": {
			stat = combine.getFeature(DraftCombine.Feature.DRILL_RESULTS);
			break;
		}
		case "-shoot": {
			stat = combine.getFeature(DraftCombine.Feature.SPOT_SHOOTING);
			break;
		}
		default: {
			if (stat == null)
				throw new IllegalArgumentException("Illegal combine element option provided");
		}
		}

	}

}

package stats.api.combine;

import stats.api.connection.Connection;
import stats.api.util.Element;
import stats.api.util.FieldType;

public class DraftCombine extends Element {

	public DraftCombine(String season) {
		super("00");
		fields.put(FieldType.SEASON_YEAR, season);
		features.put(Feature.SUMMARY, new CombineSummary(fields));
		features.put(Feature.DRILL_RESULTS, new DrillResults(fields));
		features.put(Feature.SPOT_SHOOTING, new SpotShooting(fields));
	}

	public DraftCombine(String season, Connection c) {
		this(season);
		load(c);
	}

	public enum Feature implements Element.Feature {

		SUMMARY("summary"), DRILL_RESULTS("drillresults"), SPOT_SHOOTING("spotshooting");

		private String description;

		private Feature(String d) {
			description = d;
		}

		public String toString() {
			return description;
		}
	}
}

package stats.api.team;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Teams {

	ATL("ATL", "1610612737"),
	BOS("BOS", "1610612738"),
	BKN("BKN", "1610612751"),
	CHA("CHA", "1610612766"),
	CHI("CHI", "1610612741"),
	CLE("CLE", "1610612739"),
	DAL("DAL", "1610612742"),
	DEN("DEN", "1610612743"),
	DET("DET", "1610612765"),
	GSW("GSW", "1610612744"),
	HOU("HOU", "1610612745"),
	IND("IND", "1610612754"),
	LAC("LAC", "1610612746"),
	LAL("LAL", "1610612746"),
	MEM("MEM", "1610612746"),
	MIA("MIA", "1610612748"),
	MIL("MIL", "1610612748"),
	MIN("MIN", "1610612750"),
	NOP("NOP", "1610612740"),
	NYK("NYK", "1610612752"),
	OKC("OKC", "1610612760"),
	ORL("ORL", "1610612753"),
	PHI("PHI", "1610612755"),
	PHX("PHX", "1610612756"),
	POR("POR", "1610612757"),
	SAC("SAC", "1610612758"),
	SAS("SAS", "1610612759"),
	TOR("TOR", "1610612761"),
	UTA("UTA", "1610612762"),
	WAS("WAS", "1610612764");
	
	private String code;
	private String id;
	private static Map<String, Teams> mapStringToTeams;
	static {
		Map<String, Teams> temp = new HashMap<String, Teams>();
		for(Teams t: Teams.values()) {
			temp.put(t.getCode(), t);
		}
		mapStringToTeams = Collections.unmodifiableMap(temp);
	}
	
	
	private Teams(String c, String i) {
		code = c;
		id = i;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getID() {
		return id;
	}
	
	public static Teams getTeamFromCode(String code) {
		return mapStringToTeams.get(code);
	}
	
}

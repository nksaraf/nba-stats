package stats.api.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;
import stats.api.StatsFactory;
import stats.api.connection.Connection;


/**
 * A <code>Statistic</code> is the data receieved from the NBA Stats API 
 * by requesting a specific endpoint with specific fields. The data comprises
 * of various tables which are represented via <code>statItems</code>, 
 * a List of {@code StatItem}.
 * 
 * <p>It holds all the essential information required to make a request to the 
 * API. It adds all the default values for fields not provided by the user. 
 * It loads the data from the API, processes the <code>JSON</code> that is 
 * returned and parses it to produce a list of StatItem objects. 
 * It also has the original json dump. It also has functionality to print the
 * data on the provided <code>PrintStream</code>.
 * 
 * <p>Clients do not usually deal with this class directly. This class is extended
 * by all the other classes that represent different data endpoints. 
 * 
 * @author nikhilsaraf
 */
public class Statistic {

	/** The dump of the data received from the API request */
	public JSONObject json;
	
	/** The description of what this endpoint gives us information about */
	public String description;
	private JSONArray items;
	private Map<String, Object> api_fields;
	private String api_endpoint;
	private Map<FieldType, Object> fields;
	private List<FieldType> required_fields;
	
	/** 
	 * The list of {@code StatItem} objects that represents every table of data
	 */
	public List<StatItem> statItems;
	
	/** 
	 * True, if the <code>load</code> method has been called successfully after
	 * any changes to the fields or endpoint. False, otherwise.
	 */
	public boolean loaded;

	/**
	 * Class Constructor. Creates a new Statistic object, with the information to
	 * load the data from the API. Establishes the <code>api_endpoint</code> and
	 * calls the {@code fixFields} method. Saves the list of required fields with 
	 * the associated endpoint, to make a successful request.
	 * 
	 * <p>This constructor does not load the data automatically. Remember to 
	 * call the {@code load} method before trying to access any of the data.
	 * 
	 * @param endpoint			string representing the endpoint to request the API with
	 * @param fields			map of {@code FieldType}-Object representing the fields(params) to request the api with
	 * @param required_fields	list of FieldType which are required to make successful request for the given endpoint
	 */
	public Statistic(String endpoint, Map<FieldType, Object> fields, FieldType[] required_fields) {
		this.fields = fields;
		this.api_endpoint = endpoint;
		this.required_fields = Arrays.asList(required_fields);
		loaded = false;
		fixFields();
	}

	public Statistic(String endpoint, Map<FieldType, Object> fields, FieldType[] required_fields, Connection c) {
		this.fields = fields;
		this.api_endpoint = endpoint;
		this.required_fields = Arrays.asList(required_fields);
		loaded = false;
		fixFields();
		load(c);
	}

	public Statistic(String filename) throws FileNotFoundException {
		// Code taken from Crunchify
		// http://crunchify.com/java-how-to-parse-jsonobject-and-jsonarrays/
		String jsonData = "";
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				jsonData += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		JSONObject json = new JSONObject(jsonData);
		retrieveResults(json);
	}

	public void fixFields() {
		if (api_fields == null) {
			api_fields = new HashMap<String, Object>();
		}
		for (FieldType type : required_fields) {
			if (!fields.containsKey(type)) {
				fields.put(type, type.getDefault());
			}
		}
		for (FieldType type : fields.keySet()) {
			if (!(type.possibleValue((String) fields.get(type)))) {
				fields.put(type, type.getDefault());
			}
			api_fields.put(type.toString(), fields.get(type));
		}

	}

	public void addFields(Map<FieldType, Object> additionalFields) {
		for (FieldType type : additionalFields.keySet()) {
			fields.put(type, additionalFields.get(type));
		}
		fixFields();
		loaded = false;
	}

	public void retrieveResults(JSONObject data) {
		json = data;
		description = data.getString("resource");
		if (data.has("resultSets")) {
			this.items = data.getJSONArray("resultSets");
		} else if (data.has("resultSet")) {
			Object x = data.get("resultSet");
			if (x instanceof JSONArray)
				this.items = data.getJSONArray("resultSet");
			else if (x instanceof JSONObject) {
				items = new JSONArray();
				items.put((JSONObject) x);
			}
		} else {
			this.items = null;
		}
	}

	public void load(Connection c) {
		if (api_endpoint == "") {
			System.out.println("Endpoint missing");
		} else {
			JSONObject data = c.get(api_endpoint, api_fields);
			retrieveResults(data);
		}
		loaded = true;
		loadStatItems();

	}

	public void loadWithHeader(Connection c, Map<String, String> headers) {
		if (api_endpoint == "") {
			System.out.println("Endpoint missing");
		} else {
			JSONObject data = c.get(api_endpoint, api_fields, headers);
			retrieveResults(data);
		}
		loaded = true;
		loadStatItems();
	}

	public void loadStatItems() {
		statItems = new LinkedList<StatItem>();
		for (Object item : items) {
			statItems.add(new StatItem((JSONObject) item));
		}
	}

	public void printItemDescriptions() {
		checkLoad();
		for (StatItem item : statItems) {
			System.out.println(item.description);
		}
	}

	public StatItem getItem(int index) {
		checkLoad();
		return statItems.get(index);
	}

	public void print() {
		checkLoad();
		for (StatItem item : statItems)
			item.print(System.out);
	}

	public void printItem(PrintStream ps, int index) {
		checkLoad();
		statItems.get(index).print(ps);
	}

	public void printItems(PrintStream ps, int... indices) {
		checkLoad();
		if (indices.length == 0) {
			for (StatItem item : statItems) {
				item.print(ps);
			}
		} else {
			for (int i : indices) {
				if (!(i >= statItems.size() || i < 0)) {
					printItem(ps, i);
				}
			}
		}

	}

	public boolean isRequiredField(String field) {
		if (required_fields.contains(FieldType.getFieldTypeFromString(field))) {
			return true;
		}
		return false;
	}

	public boolean isRequiredField(FieldType field) {
		if (required_fields.contains(field)) {
			return true;
		}
		return false;
	}

	public void dumpJSON() {
		String filename = description + "_" + LocalDate.now().toString() + "_" + new Random().nextInt(100);
		try {
			FileWriter w = new FileWriter("samples/" + filename);
			w.write(json.toString());
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkLoad() {
		if (!loaded)
			load(StatsFactory.getConnection());
	}

	public StatItem getItem(ItemType t) {
		return getItem(t.getIndex());
	}

	public void setEndpoint(String value) {
		api_endpoint = value;
	}

	public interface ItemType {

		int getIndex();
	}

}

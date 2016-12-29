package stats;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import api.Connection;

public class Statistic {
	
	public String description;
	private JSONArray items;
	private Map<String, Object> fields;
	private String api_endpoint;
	private FieldType[] required_fields;
	public List<StatItem> statItems;
	public boolean loaded;
	
	public Statistic(String endpoint, Map<String, Object> fields, FieldType[] required_fields) {
		this.fields = fields;
		this.api_endpoint = endpoint;
		this.required_fields = required_fields;
		loaded = false;
		
	}
	
	public Statistic(String endpoint, Map<String, Object> fields, FieldType[] required_fields, Connection c) {
		this.fields = fields;
		this.api_endpoint = endpoint;
		this.required_fields = required_fields;
		loaded = false;
		load(c);
	}
	
//	public Statistic(Map<String, Object> fields, FieldType[] required_fields) {
//		this.fields = fields;
//		this.api_endpoint = "";
//		this.required_fields = required_fields;
//		
//	}
	
	public void load(Connection c) {
		if(api_endpoint == "") {
			System.out.println("Endpoint missing");
		}
		else {
			if(fields == null) {
				fields = new HashMap<String, Object>();
			}
			for(FieldType type: required_fields) {
				if(!fields.containsKey(type.toString())) {
						fields.put(type.toString(), type.getDefault());
				}
			}
			for(String key: fields.keySet()) {
				FieldType type = FieldType.getFieldTypeFromString(key);
				if(!(type.possibleValue((String)fields.get(key)))) {
					fields.put(key, type.getDefault());
				}
			}
				
			JSONObject data = c.get(api_endpoint, fields);
			description = data.getString("resource");
			if(data.has("resultSets")) {
				this.items = data.getJSONArray("resultSets");
			}
			else if(data.has("resultSet")) {
				this.items = data.getJSONArray("resultSet");
			}
			else {
				this.items = null;
			}
		}
		loaded = true;
		loadStatItems();
		
	}
	
	public void load(String endpoint, Connection c) {
		this.api_endpoint = endpoint;
		load(c);
	}
	
	public void loadStatItems() {
		statItems = new LinkedList<StatItem>();
		for(Object item: items) {
			statItems.add(new StatItem((JSONObject)item));
		}
	}
	
	public void printItemDescriptions() {
		if(loaded) {
			for(StatItem item: statItems) {
				System.out.println(item.description);
			}
		}
		else System.out.println("Statistic not loaded yet");
		
	}
	
	public StatItem getItem(int index) {
		if(loaded) {
			return statItems.get(index);
		} else return null;
	}
	
	public void print() {
		if(loaded) {
			for(StatItem item: statItems) item.print();
		} else System.out.println("Statistic not loaded yet");
	}
	
	public void printItem(int index) {
		if(loaded) {
			statItems.get(index).print();
		}
		else System.out.println("Statistic not loaded yet");
	}
	
	public void printItems(int...indices) {
		if(loaded) {
			for(int i: indices) {
				if(!(i >= statItems.size())) {
					printItem(i);
				}
			}
		}
		else System.out.println("Statistic not loaded yet");
		
	}
}

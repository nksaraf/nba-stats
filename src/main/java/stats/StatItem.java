package stats;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class StatItem {
	
	public String description;
	List<String> headers;
	List<JSONArray> rows;
	
	public StatItem(JSONObject item) {
		
		description = item.getString("name");
		headers = new LinkedList<String>();
		rows = new LinkedList<JSONArray>();
		List<Object> headerList = item.getJSONArray("headers").toList();
		JSONArray rowList = item.getJSONArray("rowSet");
		for(Object header: headerList) {
			headers.add((String)header);
		}
		for(Object row: rowList) {
			rows.add((JSONArray)row);
		}
		
	}

	public void print() {
		System.out.println(description +"\n");
		for(String header: headers) System.out.print(header +"\t");
		System.out.println();
		for(JSONArray row: rows) {
			for(Object item: row) {
				System.out.print(item + "\t");
			}
			System.out.println();
		}	
		System.out.println();
	}
	
	public List<Object> getColumn(String header) {
		int index = header.indexOf(header);
		return getColumn(index);
		
	}
	
	public List<Object> getColumn(int index) {
		List<Object> column = new LinkedList<Object>();
		for(JSONArray row: rows) {
			column.add(row.get(index));
		}
		return column;
	}
	
	public int numberOfRows() {
		return rows.size();
	}
	
	public int numberOfColumns() {
		return headers.size();
	}

}

package stats.api.util;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dnl.utils.text.table.TextTable;

public class StatItem {

	public String description;
	public List<String> headers;
	public List<JSONArray> rows;
	TextTable table;

	public StatItem(JSONObject item) {

		description = item.getString("name");
		headers = new LinkedList<String>();
		rows = new LinkedList<JSONArray>();
		List<Object> headerList = item.getJSONArray("headers").toList();
		JSONArray rowList = item.getJSONArray("rowSet");
		for (Object header : headerList) {
			headers.add((String) header);
		}
		for (Object row : rowList) {
			rows.add((JSONArray) row);
		}
		Object[][] row_sets = new Object[rows.size()][headers.size()];
		for (int i = 0; i < rows.size(); i++) {
			row_sets[i] = rows.get(i).toList().toArray();
		}
		String[] header_set = new String[headers.size()];
		for (int i = 0; i < headers.size(); i++) {
			header_set[i] = headers.get(i);
		}
		table = new TextTable(header_set, row_sets);

	}

	public void print(PrintStream ps) {

		ps.println("\t\t\t\t" + description.toUpperCase());
		table.printTable(ps, 0);
		ps.println("\n");
	}

	public List<Object> getColumn(String header) {
		int index = headers.indexOf(header);
		return getColumn(index);

	}

	public List<Object> getColumn(int index) {
		List<Object> column = new LinkedList<Object>();
		for (JSONArray row : rows) {
			column.add(row.get(index));
		}
		return column;
	}

	public Object getDataPoint(String header, int rowIndex) {
		return getColumn(header).get(rowIndex);
	}

	public Object getDataPoint(int columnIndex, int rowIndex) {
		return getColumn(columnIndex).get(rowIndex);
	}

	public Object getDataPoint(int columnIndex, JSONArray row) {
		return row.get(columnIndex);
	}

	public Object getDataPoint(String header, JSONArray row) {
		return row.get(headers.indexOf(header));
	}

	public int numberOfRows() {
		return rows.size();
	}

	public int numberOfColumns() {
		return headers.size();
	}

	public JSONArray getRow(int index) {
		return rows.get(index);
	}

	public JSONArray getRowContaining(String header, Object value) {
		return rows.get(getColumn(header).indexOf(value));
	}

	public void printHeaders() {
		for (String header : headers) {
			System.out.println("  " + headers.indexOf(header) + "\t" + header);
		}
	}
}

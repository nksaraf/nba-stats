/**
 * Package dealing with querying the API
 */
package stats.connection;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

/**
 * @author nikhilsaraf
 * Establishes a connection with the API and gets a response
 */
public class Connection {
	
	public static Connection connection;
	
	private final String base_url;
	private Map<String, String> headers;
	
	public Connection(String base_url, Map<String, String> additionalHeaders) {
		this.base_url = base_url;
		this.headers = new HashMap<String, String>();
		headers.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5)");
		for(String key: additionalHeaders.keySet()) {
			headers.put(key, additionalHeaders.get(key));
		}
	}
	
	public JSONObject get(String endpoint, Map<String, Object> fields) {
		return new UnirestQuery(base_url, endpoint, headers, fields).getResponse();
	}
	
	public JSONObject get(String endpoint, Map<String, Object> fields, Map<String, String> additionalHeaders) {
		headers.forEach(additionalHeaders::putIfAbsent);
		return new UnirestQuery(base_url, endpoint, additionalHeaders, fields).getResponse();
	}
}

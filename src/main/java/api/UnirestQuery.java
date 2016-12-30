/**
 * Package dealing with querying the API
 */
package api;

import java.util.Map;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

/**
 * @author nikhilsaraf
 * Implementation of Query based on the Unirest HTTP Client Api.
 */
public class UnirestQuery implements Query {

	HttpRequest request;
	
	public UnirestQuery(String base_url, String endpoint, Map<String, String> headers, Map<String, Object> fields) {
		request = Unirest.get(base_url)
				.routeParam("endpoint", endpoint)
				.headers(headers)
				.queryString(fields);
	}
	
	public JSONObject getResponse() {
		try {
			HttpResponse<JsonNode> response = request.asJson();
			if(response.getStatus() == 200) {
				return response.getBody().getObject();
			}
			else throw new UnirestException("Error while requesting from API");
		} catch (UnirestException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String getUrl() {
		return request.getUrl();
	}
}

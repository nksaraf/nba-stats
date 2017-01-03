/**
 * 
 */
package stats.api;

import java.util.HashMap;
import java.util.Map;

import stats.connection.Connection;

/**
 * @author nikhilsaraf
 *
 */
public class Element {
	
	protected Map<Feature, Statistic> features;
	protected Map<FieldType, Object> fields;
	protected String id;
	/**
	 *  
	 */
	public Element(String id) {
		this.id = id;
		fields = new HashMap<FieldType, Object>();
		features = new HashMap<Feature, Statistic>();
	}
	
	public Statistic getFeature(Feature feature) {
		Statistic s = features.get(feature);
		s.checkLoad();
		return s;
	}
	
	public Statistic loadFeature(Feature feature) {
		return features.get(feature);
	}
	
	public String getID() {
		return id;
	}
	
	public void load(Connection c) {
		for(Statistic statistic: features.values()) {
			statistic.load(c);
		}
	}
	
	public void addFields(Map<FieldType, Object> additionalFields) {
		for(FieldType key: additionalFields.keySet()) {
			fields.put(key, additionalFields.get(key));
			for(Statistic statistic: features.values()) {
				if(statistic.isRequiredField(key)) statistic.loaded = false;
			}
		}
	}
	
	public interface Feature {
		String toString();
	}

}

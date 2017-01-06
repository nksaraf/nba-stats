package stats.cli;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import stats.api.FieldType;
import stats.api.Statistic;
import stats.api.StatsFactory;

class ElementCLI {

	public static Statistic stat;
	
	public static void options(String[] args) {
		if(contains(args, "-f")) {
			int indexField = Arrays.binarySearch(args, "-f");
			int nextOption  = nextOption(args, indexField + 1);
			String fieldOptions[] = Arrays.copyOfRange(args, indexField + 1, nextOption);
			applyFields(fieldOptions);
		}
		
		if(contains(args, "-h")) {
			stat.printItemDescriptions();
		}
		
		if(contains(args, "-i")) {
			int itemField = Arrays.binarySearch(args, "-i");
			int nextOption  = nextOption(args, itemField + 1);
			String itemOptions[] = Arrays.copyOfRange(args, itemField + 1, nextOption);
			printItems(itemOptions);
		}
		else {
			stat.printItems();
		}
			
	}
	
	public static void printItems(String itemOptions[]) {
			for(String item: itemOptions) {
				try {
					
						stat.printItem(Integer.parseInt(item));
					
				}
				catch(NumberFormatException e) {}
			}
	}
	
	public static int nextOption(String[] args, int indexField) {
		if(args.length == 0) {
			
		}
		else {
		for(int i = indexField; i < args.length; i++) {
			if(args[i].charAt(0) == '-') return i;
		}
		}
		return args.length;
	}
	
	public static void applyFields(String fieldOptions[]) {
		Map<FieldType, Object> fields = new HashMap<FieldType, Object>();
		for(String field: fieldOptions) {
			String type;
			String value;
			int sep;
			if(field.contains(":")) {
				sep = Arrays.binarySearch(field.toCharArray(),':');
			}
			else if(field.contains("=")) {
				sep = Arrays.binarySearch(field.toCharArray(),'=');
			}
			else continue;
			type = field.substring(0, sep);
			value = field.substring(sep + 1);
			FieldType ft = FieldType.getFieldTypeFromString(type);
			if(!(ft == null)) {
				fields.put(FieldType.getFieldTypeFromString(type), value);
			}
		}
		stat.addFields(fields);
		stat.load(StatsFactory.getConnection());
	}
	
	public static boolean contains(String[] arr, String key) {
		for(String i: arr) {
			if(i.equals(key)) return true;
		}
		return false;
	}
}

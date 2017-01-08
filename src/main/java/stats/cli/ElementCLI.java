package stats.cli;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import stats.api.StatsFactory;
import stats.api.util.FieldType;
import stats.api.util.Statistic;

class ElementCLI {

	public static Statistic stat;

	public static void options(String[] args) {
		boolean fields = contains(args, "-f");
		boolean help = contains(args, "-h");
		boolean writeToFile = contains(args, "-w");
		boolean printToConsole = contains(args, "-p");
		boolean items = contains(args, "-i");
		
		if (fields) {
			int indexField = Arrays.binarySearch(args, "-f");
			int nextOption = nextOption(args, indexField + 1);
			String fieldOptions[] = Arrays.copyOfRange(args, indexField + 1, nextOption);
			applyFields(fieldOptions);
		}
		stat.checkLoad();

		if (help) {
			stat.printItemDescriptions();
		}
		String itemOptions[] = new String[stat.statItems.size()];
		
		if (items) {
			int itemField = Arrays.binarySearch(args, "-i");
			int nextOption = nextOption(args, itemField + 1);
			itemOptions = Arrays.copyOfRange(args, itemField + 1, nextOption);
		} 
		
		PrintStream ps;
		if(writeToFile) {
			try {
				ps = new PrintStream("samples/tables/" + stat.description + "_" + LocalDate.now().toString() + "_" + (new Random()).nextInt(100));
				if(items) {
					printItems(itemOptions, ps);
				}
				else {
					stat.printItems(ps);
				}
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		if(printToConsole) {
			if(items) {
				printItems(itemOptions, System.out);
			}
			else {
				stat.printItems(System.out);
			}
		}

	}

	public static void printItems(String itemOptions[], PrintStream ps) {
		for (String item : itemOptions) {
			try {

				stat.printItem(ps, Integer.parseInt(item));

			} catch (NumberFormatException e) {
			}
		}
	}

	public static int nextOption(String[] args, int indexField) {
		if (args.length == 0) {

		} else {
			for (int i = indexField; i < args.length; i++) {
				if (args[i].charAt(0) == '-')
					return i;
			}
		}
		return args.length;
	}

	public static void applyFields(String fieldOptions[]) {
		Map<FieldType, Object> fields = new HashMap<FieldType, Object>();
		for (String field : fieldOptions) {
			String type;
			String value;
			int sep;
			if (field.contains(":")) {
				sep = field.indexOf(':');
			} else if (field.contains("=")) {
				sep = field.indexOf('=');
			} else
				continue;
			type = field.substring(0, sep);
			value = field.substring(sep + 1);
			FieldType ft = FieldType.getFieldTypeFromString(type);
			if (!(ft == null)) {
				fields.put(FieldType.getFieldTypeFromString(type), value);
			}
		}
		stat.addFields(fields);
		stat.load(StatsFactory.getConnection());
	}

	public static boolean contains(String[] arr, String key) {
		for (String i : arr) {
			if (i.equals(key))
				return true;
		}
		return false;
	}
}

package utils;
import java.io.FileReader;
import java.util.Properties;

public class ConfigReader {	
	private static Properties p = new Properties();

	public static void loadConfig(String filePath) throws Exception{
		try(FileReader file = new FileReader(filePath)) {
			p.load(file);
		}
	}

	public static String getProperty(String key) {
		return p.getProperty(key);
	}

}

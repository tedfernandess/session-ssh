package mz.co.noobs.framework.ssh;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {

	private static Properties prop = null;
	private static InputStream input = null;
	private static final String FILENAME = "message.properties";

	public static void startProperties() {
		try {
			input = LoadProperties.class.getClassLoader().getResourceAsStream(FILENAME);
			prop = new Properties();
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		if (prop == null) {
			startProperties();
		}
		return prop.getProperty(key);
	}
	
	public static void main(String[] args) {
		System.out.println(LoadProperties.getValue("NoRouteToHostException"));
	}

}

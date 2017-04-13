package fr.univartois.sonarhs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.CORBA.portable.InputStream;
import org.sonar.api.utils.log.Loggers;

public class GateRuleKey {
	public static final String PATH_FILE = "/rulesKeys.properties";
	private static final org.sonar.api.utils.log.Logger LOGGER = Loggers.get(GateRuleKey.class);
	private static Properties properties;

	private GateRuleKey() {
		throw new IllegalAccessError("Utility class");
	}

	/**
	 * Loads the properties file
	 */
	private synchronized static void init() {
		if (properties == null) {
			properties = new Properties();
			try {
				LOGGER.info("LOADING " + PATH_FILE);
				InputStream input = (InputStream) GateRuleKey.class.getResourceAsStream(PATH_FILE);
				if (input == null) {
					throw new FileNotFoundException(PATH_FILE);
				}
				properties.load(input);
			} catch (IOException e) {
				LOGGER.error("Unable to load the config file", e);
			}
		}
	}

	/**
	 * Matches keys with error messages
	 * 
	 * @param error
	 * @return
	 */
	public static String getKeyFromError(HaskellLintIssuesLoaderSensor.HaskellLintError error) {
		init();
		Pattern pattern;
		Matcher matcher;
		for (java.util.Map.Entry<Object, Object> e : properties.entrySet()) {
			pattern = Pattern.compile((String) e.getValue());
			LOGGER.info((new StringBuilder()).append("Pattern: ").append(e.getValue())
					.append(", error message: ").append(error.getDescription()).toString());
			matcher = pattern.matcher(error.getDescription());
			if (!matcher.find())
				continue;
			return (String) e.getKey();
		}
		LOGGER.warn("This description \"" + error.getDescription() + "\" is not usable");
		return null;
	}
}

package fr.univartois.sonarhs;

import static java.util.Arrays.asList;
import java.util.List;
import org.sonar.api.config.PropertyDefinition;

public class HaskellProperties {
	public static final String REPORT_PATH_KEY = "sonar.haskelllint.reportPath";
	public static final String REPORT_PATH_DEFAULT = "hlintReport.json";
	public static final String COVERAGE_REPORT_PATH_KEY = "sonar.coverage.reportPath";
	public static final String COVERAGE_REPORT_PATH_DEFAULT = "coverage.out";

	private HaskellProperties() {
	
	}

	public static List<PropertyDefinition> getProperties() {
		return asList(
				PropertyDefinition.builder(REPORT_PATH_KEY).defaultValue(REPORT_PATH_DEFAULT).category("Haskell")
						.name("hlint report path").description("hlint report relative path").build(),
				PropertyDefinition.builder(COVERAGE_REPORT_PATH_KEY).defaultValue(COVERAGE_REPORT_PATH_DEFAULT)
						.category("Haskell").name("coverage report Report path")
						.description("coverage report relative path").build());
	}
}

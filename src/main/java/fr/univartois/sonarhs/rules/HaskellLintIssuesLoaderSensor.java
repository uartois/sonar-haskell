package fr.univartois.sonarhs.rules;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.sonar.api.internal.apachecommons.lang.StringUtils;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.config.Settings;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import fr.univartois.sonarhs.languages.HaskellLanguage;



/**
 * The goal of this Sensor is to load the results of an analysis performed by an external tool named: HaskellLint
 * Results are provided as an xml file and are corresponding to the rules defined in 'rules.xml'.
 * To be very abstract, these rules are applied on source files made with the fictive language Haskell.
 */
public class HaskellLintIssuesLoaderSensor implements Sensor {

	  private static final Logger LOGGER = Loggers.get(HaskellLintIssuesLoaderSensor.class);

	  protected static final String REPORT_PATH_KEY = "sonar.foolint.reportPath";

	  protected final Settings settings;
	  protected final FileSystem fileSystem;
	  protected SensorContext context;
	
	public HaskellLintIssuesLoaderSensor(final Settings settings, final FileSystem fileSystem) {
		this.settings = settings;
		this.fileSystem = fileSystem;
	}
	  
	@Override
	public void describe(SensorDescriptor descriptor) {
	    descriptor.name("HaskellLint Issues Loader Sensor");
	    descriptor.onlyOnLanguage(HaskellLanguage.KEY);	
	}

	protected String reportPathKey() {
		return REPORT_PATH_KEY;
	}
	
	protected String getReportPath() {
		String reportPath = settings.getString(reportPathKey());
		if (!StringUtils.isEmpty(reportPath)) {
			return reportPath;
		} else {
			return null;
		}
	}
	
	@Override
	public void execute(SensorContext context) {
	    if (!StringUtils.isEmpty(getReportPath())) {
	        this.context = context;
	        String reportPath = getReportPath();
	        File analysisResultsFile = new File(reportPath);
	        try {
	          parseAndSaveResults(analysisResultsFile);
	        } catch (XMLStreamException e) {
	          throw new IllegalStateException("Unable to parse the provided HaskellLint file", e);
	        }
	    }
	}
	
	protected void parseAndSaveResults(final File file) throws XMLStreamException {
		LOGGER.info("(mock) Parsing 'HaskellLint' Analysis Results");
		HaskellLintAnalysisResultsParser parser = new HaskellLintAnalysisResultsParser();
		List<HaskellLintError> errors = parser.parse(file);
		for (HaskellLintError error : errors) {
			getResourceAndSaveIssue(error);
		}
	}
	
	private void getResourceAndSaveIssue(final HaskellLintError error) {
	    LOGGER.debug(error.toString());
		InputFile inputFile = fileSystem.inputFile(fileSystem.predicates().and(fileSystem.predicates()
				.hasRelativePath(error.getFilePath()),fileSystem.predicates().hasType(InputFile.Type.MAIN)));
		LOGGER.debug("inputFile null ? " + (inputFile == null));

		if (inputFile != null) {
			saveIssue(inputFile, error.getLine(), error.getType(), error.getDescription());
		} else {
			LOGGER.error("Not able to find a InputFile with " + error.getFilePath());
		}
	}

	private void saveIssue(final InputFile inputFile, int line, final String externalRuleKey, final String message) {
		RuleKey ruleKey = RuleKey.of(getRepositoryKeyForLanguage(inputFile.language()), externalRuleKey);

		NewIssue newIssue = context.newIssue().forRule(ruleKey);

		NewIssueLocation primaryLocation = newIssue.newLocation().on(inputFile).message(message);
		if (line > 0) {
			primaryLocation.at(inputFile.selectLine(line));
		}
		newIssue.at(primaryLocation);

		newIssue.save();
	}
	
	private static String getRepositoryKeyForLanguage(String languageKey) {
		return languageKey.toLowerCase() + "-" + HaskellLintRulesDefinition.KEY;
	}
	
	@Override
	public String toString() {
		return "HaskellLintIssuesLoaderSensor";
	}
	
	private class HaskellLintError {

		private final String type;
		private final String description;
		private final String filePath;
		private final int line;

		public HaskellLintError(final String type, final String description, final String filePath, final int line) {
			this.type = type;
			this.description = description;
			this.filePath = filePath;
			this.line = line;
		}
	
	    public String getType() {
	      return type;
	    }

	    public String getDescription() {
	      return description;
	    }

	    public String getFilePath() {
	      return filePath;
	    }

	    public int getLine() {
	      return line;
	    }

	    @Override
	    public String toString() {
	      StringBuilder s = new StringBuilder();
	      s.append(type);
	      s.append("|");
	      s.append(description);
	      s.append("|");
	      s.append(filePath);
	      s.append("(");
	      s.append(line);
	      s.append(")");
	      return s.toString();
	    }
	}
	
	private class HaskellLintAnalysisResultsParser {

		public List<HaskellLintError> parse(final File file) throws XMLStreamException {
			LOGGER.info("Parsing file {}", file.getAbsolutePath());

		      // as the goal of this example is not to demonstrate how to parse an xml file we return an hard coded list of FooError

		      HaskellLintError fooError1 = new HaskellLintError("ExampleRule1", "More precise description of the error", "src/MyClass.haskell", 5);
		      HaskellLintError fooError2 = new HaskellLintError("ExampleRule2", "More precise description of the error", "src/MyClass.haskell", 9);

		      return Arrays.asList(fooError1, fooError2);
		    }
		}
	
}

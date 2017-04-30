package fr.univartois.sonarhs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
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

import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



/**
 * The goal of this Sensor is to load the results of an analysis performed by an external tool named: hLint
 * Results are provided as an JSON file and are corresponding to the rules defined in 'haskelllint-rules.xml'.
 * To be very abstract, these rules are applied on source files made with Haskell.
 */
public class HaskellLintIssuesLoaderSensor implements Sensor {

	  private static final Logger LOGGER = Loggers.get(HaskellLintIssuesLoaderSensor.class);
	  

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
	
	protected String getReportPath() {
		String reportPath = settings.getString(HaskellProperties.REPORT_PATH_KEY);
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
	        } catch (ParseException e) {
	          throw new IllegalStateException("Unable to parse the provided HaskellLint report file", e);
	        }
	    }
	}
	
	protected void parseAndSaveResults(final File file) throws ParseException {
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
			saveIssue(inputFile, error.getLine(), error.getHint(), error.getDescription());  
		} else {
			LOGGER.error("Not able to find a InputFile with " + error.getFilePath());
		}
	}

	private void saveIssue(final InputFile inputFile, int line, final String externalRuleKey, final String message) {
		if(externalRuleKey==null){
			LOGGER.warn("The key of this message "+message+ " is null, issue not saved");
			return;
		}
		
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
	
	public class HaskellLintError {

		private final String hint;
		private final String filePath;//file
		private final int line;//startLine
		private final String description;//from to

		public HaskellLintError(final String hint, final String filePath, final int line, final String description) {
			this.hint = hint;
			this.description = description;
			this.filePath = filePath;
			this.line = line;
		}
	
	    public String getHint() {
	    	return hint;
	    }

	    public String getFilePath() {
	    	return filePath;
		}

	    public int getLine() {
	    	return line;
	    }
	    
	    public String getDescription() {
	    	return description;
	    }


	    @Override
	    public String toString() {
	      StringBuilder s = new StringBuilder();
	      s.append(hint);
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

	
		public List<HaskellLintError> parse(final File file) throws ParseException {
			LOGGER.info("Parsing file {}", file.getAbsolutePath());
			List<HaskellLintError> errorsAsList = new ArrayList<>();
			try {
				FileReader reader = new FileReader(file);
	            JsonParser jsonParser = new JsonParser();
				JsonArray jsonArray = (JsonArray) jsonParser.parse(reader);
				
				Iterator<JsonElement> i = jsonArray.iterator();
				while (i.hasNext()) {
					JsonObject innerObj = (JsonObject) i.next();
					errorsAsList.add(new HaskellLintError(
							innerObj.get("hint").getAsString()
							, innerObj.get("file").getAsString()
							, Integer.parseInt(innerObj.get("startLine").getAsString())
							, "Expression found : " + innerObj.get("from").getAsString() 
							+ " Should be replaced by : " + innerObj.get("to").getAsString()));
				}
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}
		
		      return errorsAsList;
		    }
		}
	
}

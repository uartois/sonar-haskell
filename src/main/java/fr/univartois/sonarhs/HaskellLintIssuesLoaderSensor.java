/*******************************************************************************
 *  Copyright 2017 - Universite d'Artois
 *  
 *  This file is part of SonarQube Haskell plugin (sonar-haskell).
 *  
 *  Sonar-haskell is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  Sonar-haskell is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Sonar-haskell.  If not, see <http://www.gnu.org/licenses/>.
 *  
 *  Contributors:
 *              Mohamed Boumati (mohamed_boumati@ens.univ-artois.fr)
 *******************************************************************************/
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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
	
    /**
     * Constructor
     * @param settings
     * @param fileSystem
     */
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
	    } catch (Exception e) {
		throw new RuntimeException(e);
	    } 
	}
    }
	
    protected void parseAndSaveResults(final File file) throws Exception {
	
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
	
	
	
    /**
     * Define Haskell Error 
     * @author Mohamed
     *
     */
    public class HaskellLintError {

	private final String hint;
	private final String filePath;
	private final int line;
	private final String description;

	/**
	 * Constructor
	 * @param hint
	 * @param filePath
	 * @param line
	 * @param description
	 */
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
	
	
    private String generateMessageForHints(final String hint,final String from,final String to){
		
	final String reduceDuplicationHint = "Reduce duplication";
	final String redundantBracketHint = "Redundant bracket";
	final String useFewerImportsHint = "Use fewer imports";
		
	if(hint.equals(reduceDuplicationHint)){
	    return "Reduce This Code Duplication: " + to; 
	}
		
	if(hint.equals(redundantBracketHint)){
	    return "Remove Unnecessary Parentheses. Replace by:  " + to;
	}
		
	if(hint.equals(useFewerImportsHint)){
	    return "Use: " + to + "  once only";
	}
		
	return "Expression found: " + from 
		+ " Should be replaced by: " + to;
    }
	
	
    private class HaskellLintAnalysisResultsParser {

	/**
	* parse the report provided by hlint
	* @param file hlintReport
	* @return List of Haskell Errors
	* @throws Exception
	*/
	public List<HaskellLintError> parse(final File file) throws Exception {
	
	    LOGGER.info("Parsing file {}", file.getAbsolutePath());
	    List<HaskellLintError> errorsAsList = new ArrayList<>();
	    try( FileReader reader = new FileReader(file) ) {
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = null;
		jsonArray = (JSONArray) jsonParser.parse(reader);

		Iterator i = jsonArray.iterator();
		while (i.hasNext()) {
		    JSONObject innerObj = (JSONObject) i.next();
		    errorsAsList.add(new HaskellLintError(
			    	"hlint:" + innerObj.get("hint").toString()
				, innerObj.get("file").toString()
				, Integer.parseInt(innerObj.get("startLine").toString())
				, generateMessageForHints(innerObj.get("hint").toString()
									,innerObj.get("from").toString()
									,innerObj.get("to").toString())));
				}
	    } catch (FileNotFoundException|NullPointerException ex) {
		throw ex;
	    }
		
	    return errorsAsList;
	}
    }
	
}

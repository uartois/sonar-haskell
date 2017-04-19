package fr.univartois.sonarhs;


import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.api.utils.log.Loggers;
import org.sonar.api.utils.log.Logger;

public class HaskellLintRulesDefinition implements RulesDefinition{

	
  private static final String PATH_TO_RULES_XML = "/haskelllint-rules.xml";
  public static final String KEY = "haskelllint";
  protected static final String NAME = "HaskellLint";
  private static final Logger LOGGER=Loggers.get(HaskellLintRulesDefinition.class);	
  
  public static final String REPO_KEY = HaskellLanguage.KEY + "-" + KEY;
  public static final String REPO_NAME = HaskellLanguage.KEY + "-" + NAME;
	
  protected String rulesDefinitionFilePath() {
    return PATH_TO_RULES_XML;
  }
  
  private void defineRulesForLanguage(Context context, String repositoryKey, String repositoryName, String languageKey) {
	  NewRepository repository = context.createRepository(repositoryKey, languageKey).setName(repositoryName);

	  InputStream rulesXml = this.getClass().getResourceAsStream(rulesDefinitionFilePath());
	  if (rulesXml != null) {
		  RulesDefinitionXmlLoader rulesLoader = new RulesDefinitionXmlLoader();
	      rulesLoader.load(repository, rulesXml, StandardCharsets.UTF_8.name());
	  }else{
	    	LOGGER.warn("Unable to load "+PATH_TO_RULES_XML);
	    }
	  repository.done();
	  LOGGER.info("The repository "+repositoryName+" is made: "+repository.rules());
	}
  
	@Override
	public void define(Context context) {
		defineRulesForLanguage(context, REPO_KEY, REPO_NAME, HaskellLanguage.KEY);		
	}

}

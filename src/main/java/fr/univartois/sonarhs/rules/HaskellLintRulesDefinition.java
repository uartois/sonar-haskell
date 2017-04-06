package fr.univartois.sonarhs.rules;


import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import fr.univartois.sonarhs.languages.HaskellLanguage;

public class HaskellLintRulesDefinition implements RulesDefinition{

  private static final String PATH_TO_RULES_XML = "/resources/example/haskelllint-rules.xml";
  protected static final String KEY = "haskelllint";
  protected static final String NAME = "HaskellLint";

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
	  }
	  repository.done();
	}
  
	@Override
	public void define(Context context) {
		defineRulesForLanguage(context, REPO_KEY, REPO_NAME, HaskellLanguage.KEY);		
	}

}

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

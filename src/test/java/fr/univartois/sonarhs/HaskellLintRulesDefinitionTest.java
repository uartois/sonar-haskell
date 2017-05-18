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

import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition.Rule;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinition.Param;
import org.sonar.api.server.rule.RulesDefinition.Repository;
import org.sonar.api.utils.Version;

import static org.fest.assertions.Assertions.assertThat;

public class HaskellLintRulesDefinitionTest {

    @Test
    public void testRepository() {
      
	Repository repository = buildRepository();
	
	assertThat(repository.name()).isEqualTo("haskell-HaskellLint");
	assertThat(repository.language()).isEqualTo("haskell");
      
    }

    @Test
    public void testRules(){
	
	Repository repository = buildRepository();
	Rule rule = repository.rule("hlint:Use putStr");
	
	assertThat(rule).isNotNull();
	assertThat(rule.name()).isEqualTo("Use putStr");
	assertThat(rule.type()).isEqualTo(RuleType.BUG);
	for (Rule r : repository.rules()) {
	    for (Param param : r.params()) {
		assertThat(param.description()).isNotEmpty();
	    }
	}
	
    }
    
    
    private RulesDefinition.Repository buildRepository() {
     
	HaskellLintRulesDefinition rulesDefinition = new HaskellLintRulesDefinition();
	RulesDefinition.Context context = new RulesDefinition.Context();
	rulesDefinition.define(context);
	
	return context.repository("haskell-haskelllint");
    }
    
}

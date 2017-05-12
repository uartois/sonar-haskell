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
      
	Repository repository = buildRepository(Version.parse("6.0"));
	
	assertThat(repository.name()).isEqualTo("haskell-HaskellLint");
	assertThat(repository.language()).isEqualTo("haskell");
      
    }

    @Test
    public void testRules(){
	
	Repository repository = buildRepository(Version.parse("6.0"));
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
    
    
    private RulesDefinition.Repository buildRepository(Version sonarRuntimeVersion) {
     
	HaskellLintRulesDefinition rulesDefinition = new HaskellLintRulesDefinition();
	RulesDefinition.Context context = new RulesDefinition.Context();
	rulesDefinition.define(context);
	Repository repository = context.repository("haskell-haskelllint");
	
	return repository;
    }
    
}

package fr.univartois.sonarhs.languages;

import static org.sonar.api.rules.RulePriority.BLOCKER;
import static org.sonar.api.rules.RulePriority.CRITICAL;
import static org.sonar.api.rules.RulePriority.MAJOR;
import static fr.univartois.sonarhs.rules.HaskellLintRulesDefinition.REPO_KEY;

import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.Rule;
import org.sonar.api.utils.ValidationMessages;

public class HaskellQualityProfile extends ProfileDefinition{

	@Override
	public RulesProfile createProfile(ValidationMessages validation) {
	    RulesProfile profile = RulesProfile.create("HaskellLint Rules", HaskellLanguage.KEY);
	    profile.activateRule(Rule.create(REPO_KEY, "ExampleRule1"), BLOCKER);
	    profile.activateRule(Rule.create(REPO_KEY, "ExampleRule2"), MAJOR);
	    profile.activateRule(Rule.create(REPO_KEY, "ExampleRule3"), CRITICAL);
	    return profile;
	}

}

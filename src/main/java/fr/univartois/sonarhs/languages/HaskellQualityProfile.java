package fr.univartois.sonarhs.languages;

import static fr.univartois.sonarhs.HaskellLintRulesDefinition.REPO_KEY;
import static fr.univartois.sonarhs.HaskellLintRulesDefinition.REPO_NAME;

import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.Rule;
import org.sonar.api.utils.ValidationMessages;

public class HaskellQualityProfile extends ProfileDefinition{

	@Override
	public RulesProfile createProfile(ValidationMessages validation) {
	    RulesProfile profile = RulesProfile.create("HaskellLint Rules", HaskellLanguage.KEY);
		profile.setDefaultProfile(true);
	    profile.activateRule(Rule.create(REPO_KEY, "ExampleRule1",REPO_NAME), null);
	    profile.activateRule(Rule.create(REPO_KEY, "ExampleRule2",REPO_NAME), null);
	    profile.activateRule(Rule.create(REPO_KEY, "ExampleRule3",REPO_NAME), null);
	    return profile;
	}

}

package fr.univartois.sonarhs.languages;

import static fr.univartois.sonarhs.HaskellLintRulesDefinition.REPO_KEY;
import static fr.univartois.sonarhs.HaskellLintRulesDefinition.REPO_NAME;

import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.Rule;
import org.sonar.api.utils.ValidationMessages;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import java.util.Properties;
import java.util.Map.Entry;
import java.io.IOException;

public class HaskellQualityProfile extends ProfileDefinition{

	@Override
	public RulesProfile createProfile(ValidationMessages validation) {

		final Logger LOGGER=Loggers.get(HaskellQualityProfile.class);
		final String PROFILE_PATH="profile.properties";
		
		LOGGER.info("Haskelllint Quality profile");  
		RulesProfile profile = RulesProfile.create("Haskelllint Rules", HaskellLanguage.KEY);
		profile.setDefaultProfile(Boolean.TRUE);
		Properties property=new Properties();
		try {
			property.load(HaskellQualityProfile.class.getResourceAsStream(PROFILE_PATH));
			for (Entry<Object, Object> e : property.entrySet()) {
				if(Boolean.TRUE.equals(Boolean.valueOf((String) e.getValue()))){
					profile.activateRule(Rule.create(REPO_KEY,(String) e.getKey(),REPO_NAME), null);
				}
			}
		}catch (IOException e) {
			LOGGER.error((new StringBuilder()).append("Unable to load ").append(PROFILE_PATH).toString(), e);
		}
		LOGGER.info((new StringBuilder()).append("Profil generated: ").append(profile.getActiveRules()).toString());
		return profile;
	}

}

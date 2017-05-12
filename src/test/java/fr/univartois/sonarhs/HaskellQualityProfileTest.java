package fr.univartois.sonarhs;

import org.junit.Test;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.ActiveRule;
import org.sonar.api.utils.ValidationMessages;

import static org.fest.assertions.Assertions.assertThat;

public class HaskellQualityProfileTest {
    
    @Test
    public void testProfile() {

	ValidationMessages validation = ValidationMessages.create();
	HaskellQualityProfile definition = new HaskellQualityProfile();
	RulesProfile profile = definition.createProfile(validation);

	assertThat(profile.getLanguage()).isEqualTo(HaskellLanguage.KEY);
	assertThat(profile.getName()).isEqualTo("Sonar way");
	assertThat(validation.hasErrors()).isFalse();
	for (ActiveRule rule : profile.getActiveRules()) {
	    assertThat(rule.isEnabled());
	}
	assertThat(profile.getActiveRules().size()).isGreaterThan(100);
    }

}

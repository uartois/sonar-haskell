package fr.univartois.sonarhs;

import org.sonar.api.Plugin;

public class SonarHaskellPlugin implements Plugin{

	@Override
	public void define(Context context) {
		
		context.addExtensions(HaskellLanguage.class, HaskellQualityProfile.class);
		context.addExtension(HaskellProperties.getProperties());
		
	    context.addExtensions(HaskellLintRulesDefinition.class, HaskellLintIssuesLoaderSensor.class);

	}

}

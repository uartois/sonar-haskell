package fr.univartois.sonarhs;

import org.sonar.api.Plugin;

public class SonarHaskellPlugin implements Plugin{

	@Override
	public void define(Context context) {
		
		//Language
		context.addExtensions(HaskellLanguage.class, HaskellQualityProfile.class);
		//context.addExtension(HaskellLanguageProperties.getProperties());
		

	    //Measures
	    //context.addExtensions(ExampleMetrics.class, SetSizeOnFilesSensor.class, ComputeSizeAverage.class, ComputeSizeRating.class);
		
		
	    //Rules
	    context.addExtensions(HaskellLintRulesDefinition.class, HaskellLintIssuesLoaderSensor.class);
	    
	    //Web
	    //context.addExtensions(ExampleFooter.class, ExampleWidget.class);
	}

}

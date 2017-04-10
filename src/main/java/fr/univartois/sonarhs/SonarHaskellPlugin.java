package fr.univartois.sonarhs;

import org.sonar.api.Plugin;

//import static java.util.Arrays.asList;

//import org.sonar.api.config.PropertyDefinition;
//import org.sonarsource.plugins.example.hooks.DisplayIssuesInScanner;
//import org.sonarsource.plugins.example.hooks.DisplayQualityGateStatus;
import fr.univartois.sonarhs.languages.HaskellLanguage;
import fr.univartois.sonarhs.languages.HaskellQualityProfile;
//import fr.univartois.sonarhs.measures.ComputeSizeAverage;
//import fr.univartois.sonarhs.measures.ComputeSizeRating;
//import fr.univartois.sonarhs.measures.ExampleMetrics;
//import fr.univartois.sonarhs.measures.SetSizeOnFilesSensor;
import fr.univartois.sonarhs.rules.HaskellLintIssuesLoaderSensor;

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

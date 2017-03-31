package fr.univartois.sonarhs.languages;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.config.Settings;
import org.sonar.api.internal.apachecommons.lang.StringUtils;
import org.sonar.api.resources.AbstractLanguage;
import fr.univartois.sonarhs.settings.HaskellLanguageProperties;


public class HaskellLanguage extends AbstractLanguage{

	  public static final String NAME = "Haskell";
	  public static final String KEY = "haskell";
	
	private final Settings settings;
	
	public HaskellLanguage(Settings settings){
	    super(KEY, NAME);
	    this.settings = settings;
	}
	
	@Override
	public String[] getFileSuffixes() {
	    String[] suffixes = filterEmptyStrings(settings.getStringArray(HaskellLanguageProperties.FILE_SUFFIXES_KEY));
	    if (suffixes.length == 0) {
	      suffixes = StringUtils.split(HaskellLanguageProperties.FILE_SUFFIXES_DEFAULT_VALUE, ",");
	    }
	return suffixes;
	}
	
	private String[] filterEmptyStrings(String[] stringArray) {
		
		List<String> nonEmptyStrings = new ArrayList<>();
		for (String string : stringArray) {
			if (StringUtils.isNotBlank(string.trim())) {
				nonEmptyStrings.add(string.trim());
		    }
		}
		return nonEmptyStrings.toArray(new String[nonEmptyStrings.size()]);
	}
	
}
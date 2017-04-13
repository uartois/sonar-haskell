package fr.univartois.sonarhs.languages;

import org.sonar.api.resources.AbstractLanguage;

public class HaskellLanguage extends AbstractLanguage{

	  public static final String NAME = "Haskell";
	  public static final String KEY = "haskell";
	

	private static final String[] DEFAULT_FILE_SUFFIXES={"hs"};

	
	public HaskellLanguage(){
	    super(KEY, NAME);
	}
	
	@Override
	public String[] getFileSuffixes() {
	    return DEFAULT_FILE_SUFFIXES;
	}
	
}
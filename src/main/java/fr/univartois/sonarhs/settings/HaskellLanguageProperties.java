package fr.univartois.sonarhs.settings;

import static java.util.Arrays.asList;

import java.util.List;

import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

public class HaskellLanguageProperties {

	public static final String FILE_SUFFIXES_KEY = "sonar.haskell.file.suffixes";
	public static final String FILE_SUFFIXES_DEFAULT_VALUE = ".haskell";

	private HaskellLanguageProperties() { 
		
	}

	public static List<PropertyDefinition> getProperties() {
	    return asList(PropertyDefinition.builder(FILE_SUFFIXES_KEY)
	      .defaultValue(FILE_SUFFIXES_DEFAULT_VALUE)
	      .category("Haskell")
	      .name("File Suffixes")
	      .description("Comma-separated list of suffixes for files to analyze.")//**
	      .onQualifiers(Qualifiers.PROJECT)
	      .build());
	}
	
}

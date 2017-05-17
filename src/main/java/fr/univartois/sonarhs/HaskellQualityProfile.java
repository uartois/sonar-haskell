/*******************************************************************************
 *  Copyright 2017 - Universite d'Artois
 *  
 *  This file is part of SonarQube Haskell plugin (sonar-haskell).
 *  
 *  Sonar-haskell is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  Sonar-haskell is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Sonar-haskell.  If not, see <http://www.gnu.org/licenses/>.
 *  
 *  Contributors:
 *              Mohamed Boumati (mohamed_boumati@ens.univ-artois.fr)
 *******************************************************************************/
package fr.univartois.sonarhs;

import java.util.List;


import org.sonar.api.profiles.AnnotationProfileParser;
import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.profiles.XMLProfileParser;
import org.sonar.api.rules.ActiveRule;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.utils.ValidationMessages;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import com.google.common.collect.ImmutableList;


public class HaskellQualityProfile extends ProfileDefinition{

    	public static final String PROFILE_NAME = "Sonar way";
    
    	private final AnnotationProfileParser annotationProfileParser;
    	private final XMLProfileParser xmlProfileParser;
	  
	public HaskellQualityProfile(AnnotationProfileParser annotationProfileParser,
	                       XMLProfileParser xmlProfileParser) {
	    this.annotationProfileParser = annotationProfileParser;
	    this.xmlProfileParser = xmlProfileParser;
	}
	

	@Override
	public RulesProfile createProfile(ValidationMessages validation) {

		final Logger LOGGER=Loggers.get(HaskellQualityProfile.class);
		
		LOGGER.info("Haskelllint Quality profile");  
		
		RulesProfile profile = RulesProfile.create(PROFILE_NAME, "haskell");
		profile.setDefaultProfile(Boolean.TRUE);
		
		List<Class> empty = ImmutableList.<Class>of();
		
	    RulesProfile checks = annotationProfileParser.parse("haskell-haskelllint",
	    		"haskell-HaskellLint", "haskell", empty, validation);
	    
	    LOGGER.info(checks.getActiveRules().toString());
	    
	    RulesProfile dialyzer = xmlProfileParser.parseResource(getClass().getClassLoader(),
	      "default-profile.xml", validation);
	    
	    List<ActiveRule> rules = checks.getActiveRules();
	    rules.addAll(dialyzer.getActiveRules());

	    profile.setActiveRules(rules);

	    LOGGER.info((new StringBuilder()).append("Profil generated: ").append(profile.getActiveRules()).toString());
	    return profile;
	}

}

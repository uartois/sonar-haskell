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

import static fr.univartois.sonarhs.HaskellLintRulesDefinition.REPO_KEY;
import static fr.univartois.sonarhs.HaskellLintRulesDefinition.REPO_NAME;

import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.Rule;
import org.sonar.api.utils.ValidationMessages;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;


public class HaskellQualityProfile extends ProfileDefinition{

	@Override
	public RulesProfile createProfile(ValidationMessages validation) {

		final Logger LOGGER=Loggers.get(HaskellQualityProfile.class);
		
		LOGGER.info("Haskelllint Quality profile");  
		RulesProfile profile = RulesProfile.create("Sonar way", HaskellLanguage.KEY);
		profile.setDefaultProfile(Boolean.TRUE);
		
		
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use print",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use putStr",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use getChar",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use getLine",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use getContents",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use putChar",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use putStrLn",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use hReady",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use hPrint",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use isEOF",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use exitSuccess",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use minimum",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use maximum",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use minimumBy",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use maximumBy",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Avoid reverse",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use show",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use shows",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use reads",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use showHex",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use showOct",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use concatMap",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use map once",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use head",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use replicate",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use repeat",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use last",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use init",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use isSuffixOf",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use concat",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use foldl1",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use foldr1",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use break",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use span",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use takeWhile",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use dropWhile",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use unlines",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use id",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use any",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use all",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use zip",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use zip3",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use null",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Redundant bracket",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use :",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use zipWith",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use notElem",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use infix",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use unwords",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use intercalate",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use or",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use and",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use elem",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use section",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use elemIndex",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use elemIndices",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Length always non-negative",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use foldl",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use foldr",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use delete",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use group",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use insert",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use intersect",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use nub",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use sort",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use union",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use sequence_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use sum",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use product",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use const",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use snd",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use fst",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use curry",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use uncurry",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Redundant $",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Redundant flip",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use on",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use isAsciiLower",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use isAsciiUpper",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use isDigit",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use isOctDigit",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use isAlpha",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Redundant ==",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Redundant /=",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Redundant if",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use second",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use &&&",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use ***",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use first",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Redundant pair",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Functor law",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Monad law, left identity",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Monad law, right identity",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use liftM",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use when",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use unless",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use mapM",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use mapM_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use forM",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use forM_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use join",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use .",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use void",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use <=<",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use >=>",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use =<<",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use >>=",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use forever",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use ap",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use zipWithM",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use evalState",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use execState",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use mapAndUnzipM",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use zipWithM_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use replicateM",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use replicateM_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use for",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use for_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use sequenceA_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use asum",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use <**>",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use optional",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use list_comprehension",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Redundant list_comprehension",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use fromMayBe",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use isJust",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use isNothing",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use mapMayBe",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use fmap",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use catMayBes",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use lefts",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use rights",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use sqrt",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use floor",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use handle",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use handleJust",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use catchJust",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use withFile",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use withBinaryFile",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use error",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use nonTermination",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use nestedAtomically",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use mkWeakPtr",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use mkWeakPair",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use Foldable.forM_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Evaluate",REPO_NAME), null);
		
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Avoid lambda",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Used otherwise as a pattern",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use guards",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Redundant do",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use list literal pattern",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use list literal",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Eta reduce",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"hlint:Use map",REPO_NAME), null);
		
		LOGGER.info((new StringBuilder()).append("Profil generated: ").append(profile.getActiveRules()).toString());
		return profile;
	}

}

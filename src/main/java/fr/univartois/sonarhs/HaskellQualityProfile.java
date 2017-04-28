package fr.univartois.sonarhs;

import static org.sonar.api.rules.RulePriority.MINOR;

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
		
		
		profile.activateRule(Rule.create(REPO_KEY,"Use print",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use putStr",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use getChar",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use getLine",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use getContents",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use putChar",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use putStrLn",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use hReady",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use hPrint",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use isEOF",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use exitSuccess",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use minimum",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use maximum",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use minimumBy",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use maximumBy",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Avoid reverse",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use show",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use shows",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use reads",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use showHex",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use showOct",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use concatMap",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use map once",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use head",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use replicate",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use repeat",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use last",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use init",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use isSuffixOf",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use concat",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use foldl1",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use foldr1",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use break",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use span",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use takeWhile",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use dropWhile",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use unlines",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use id",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use any",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use all",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use zip",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use zip3",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use null",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Redundant bracket",REPO_NAME), MINOR);
		profile.activateRule(Rule.create(REPO_KEY,"Use :",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use zipWith",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use notElem",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use infix",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use unwords",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use intercalate",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use or",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use and",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use elem",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use section",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use elemIndex",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use elemIndices",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Length always non-negative",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use foldl",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use foldr",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use delete",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use group",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use insert",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use intersect",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use nub",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use sort",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use union",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use sequence_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use sum",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use product",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use const",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use snd",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use fst",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use curry",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use uncurry",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Redundant $",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Redundant flip",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use on",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use isAsciiLower",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use isAsciiUpper",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use isDigit",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use isOctDigit",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use isAlpha",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Redundant ==",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Redundant /=",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Redundant if",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use second",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use &&&",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use ***",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use first",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Redundant pair",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Functor law",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Monad law, left identity",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Monad law, right identity",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use liftM",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use when",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use unless",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use mapM",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use mapM_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use forM",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use forM_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use join",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use .",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use void",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use <=<",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use >=>",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use =<<",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use >>=",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use forever",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use ap",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use zipWithM",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use evalState",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use exitState",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use mapAndUnzipM",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use zipWithM_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use replicateM",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use replicateM_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use for",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use for_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use sequenceA_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use asum",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use <**>",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use optional",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use list_comprehension",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Redundant list_comprehension",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use fromMayBe",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use isJust",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use isNothing",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use mapMayBe",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use fmap",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use catMayBes",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use lefts",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use rights",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use sqrt",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use floor",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use handle",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use handleJust",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use catchJust",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use withFile",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use withBinaryFile",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use error",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use nonTermination",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use nestedAtomically",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use mkWeakPtr",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use mkWeakPair",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Use Foldable.forM_",REPO_NAME), null);
		profile.activateRule(Rule.create(REPO_KEY,"Evaluate",REPO_NAME), null);
		
		LOGGER.info((new StringBuilder()).append("Profil generated: ").append(profile.getActiveRules()).toString());
		return profile;
	}

}

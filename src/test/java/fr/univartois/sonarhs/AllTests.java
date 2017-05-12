package fr.univartois.sonarhs;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ HaskellLanguageTest.class, HaskellLintIssuesLoaderSensorTest.class, HaskellLintRulesDefinitionTest.class, HaskellQualityProfileTest.class,
        SonarHaskellPluginTest.class })
public class AllTests {

}

package fr.univartois.sonarhs;

import org.junit.Test;
import org.sonar.api.Plugin;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.utils.Version;

import static org.fest.assertions.Assertions.assertThat;

public class SonarHaskellPluginTest {

    @Test
    public void SonarHaskellPluginTester() {
	
	Plugin.Context context = new Plugin.Context(SonarRuntimeImpl.forSonarQube(Version.create(6, 0), SonarQubeSide.SERVER));
	new SonarHaskellPlugin().define(context);
	
	assertThat(context.getExtensions()).hasSize(5);
    }
   
}

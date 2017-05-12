package fr.univartois.sonarhs;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class HaskellLanguageTest {
 
    private HaskellLanguage haskellLanguage;

    @Before
    public void setUp() {
	haskellLanguage = new HaskellLanguage();
    }

    @Test
    public void testSuffixes() {
	assertThat(haskellLanguage.getFileSuffixes()).containsOnly("hs");
    }
    
}
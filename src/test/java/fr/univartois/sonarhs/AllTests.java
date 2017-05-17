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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ HaskellLanguageTest.class, HaskellLintIssuesLoaderSensorTest.class, 
    		HaskellLintRulesDefinitionTest.class, SonarHaskellPluginTest.class })
public class AllTests {

}

/*******************************************************************************
 * Copyright 2017 - Université d'Artois
 *  
 * This file is part of SonarQube Haskell plugin (sonar-haskell).
 *  
 * Sonar-haskell is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published 
 * by the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 *  
 * Sonar-haskell is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *  
 * You should have received a copy of the GNU Lesser General Public License
 * along with Sonar-haskell.  If not, see <http://www.gnu.org/licenses/>.
 *  
 * Contributors:
 *              Mohamed Boumati (mohamed_boumati@ens.univ-artois.fr)
 *******************************************************************************/
package fr.univartois.sonarhs;

import org.junit.Test;
import org.sonar.api.Plugin;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.utils.Version;

import static org.fest.assertions.Assertions.assertThat;

public class SonarHaskellPluginTest {

    @Test
    public void testSonarHaskellPlugin() {
	
	Plugin.Context context = new Plugin.Context(SonarRuntimeImpl.forSonarQube(Version.create(6, 0), SonarQubeSide.SERVER));
	new SonarHaskellPlugin().define(context);
	
	assertThat(context.getExtensions()).hasSize(5);
    }
   
}

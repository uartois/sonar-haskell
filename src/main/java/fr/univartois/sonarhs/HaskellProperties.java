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
 *              Mohamed Boutmati (mohamed_boumati@ens.univ-artois.fr)
 *******************************************************************************/
package fr.univartois.sonarhs;

import static java.util.Arrays.asList;
import java.util.List;
import org.sonar.api.config.PropertyDefinition;

public class HaskellProperties {
	public static final String REPORT_PATH_KEY = "sonar.hlint.reportPath";
	public static final String REPORT_PATH_DEFAULT = "hlintReport.json";
	public static final String COVERAGE_REPORT_PATH_KEY = "sonar.coverage.reportPath";
	public static final String COVERAGE_REPORT_PATH_DEFAULT = "coverage.out";

	private HaskellProperties() {
	
	}

	public static List<PropertyDefinition> getProperties() {
		return asList(
				PropertyDefinition.builder(REPORT_PATH_KEY).defaultValue(REPORT_PATH_DEFAULT).category("Haskell")
						.name("hlint report path").description("hlint report relative path").build(),
				PropertyDefinition.builder(COVERAGE_REPORT_PATH_KEY).defaultValue(COVERAGE_REPORT_PATH_DEFAULT)
						.category("Haskell").name("coverage report Report path")
						.description("coverage report relative path").build());
	}
}

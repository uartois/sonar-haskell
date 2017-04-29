# SonarQube plugin for Haskell

The purpose of this project is to build a code analyzer for [Haskell](https://www.haskell.org)

It integrates `hlint` reports into SonarQube dashboard.

The user must generate a jason hint report for his code. This report is thus integrated to SonarQube using sonar-scanner.

# Installation

- Download the latest version of the artefact
- Stop Sonarqube server
- Copy the jar file in $SONAR_PATH/extensions/plugins
- Start Sonarqube server
    
# Use the plugin
- Create a sonar-project.properties file
```
sonar.projectKey=my:project
sonar.projectName=My project
sonar.projectVersion=1.0
sonar.haskelllint.reportPath=hlintReport.json
sonar.sources=./
```

- Install hlint
```
cabal update
cabal install hlint
```

- Produce the hlint report of your code to be analyzed, and save it using in a file named hlintReport.json   
```
hlint YOUR_CODE.hs --json > hlintReport.json
hlint . --json > hlintReport.json
```

- Start the analysis with sonar scanner 
```
sonar-scanner
```
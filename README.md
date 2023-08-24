# cvrgo
Test project with Gherkin (cucumber) for CoverGo

# Test Execution Instruction
## Prerequisites
1. Java 18+ installed
2. maven installed
3. To have access to maven repository as well to selenium

## Run Tests
Use 
```mvn clean test```
To run all tests

If it was the first run, wait for a while until all
necessary libraries will be downloaded

## Observe Results
During tests execution you should see scenario lines
But there is an option to see html-report.

When test execution will be finished go to the ```target\cucumber-reports``` folder
and find out ```report.html``` file there. Open it in browser and you will see 
test execution report


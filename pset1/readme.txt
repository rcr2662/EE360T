To execute tests from command line, compile the test files using command javac -cp "<Path to JUnit, Selenium, and other Jars>;." FileName.java
replacing what is in <> with the path to the appropriate jars and replacing "FileName" with the test file. Then execute the tests using
coomand java -cp "<Path to JUnit, Selenium, and other Jars>;." org.junit.runner.JUnitCore FileName replacing what is in <> with the appropriate 
path and replacing "FileName" with the appropriate test file.
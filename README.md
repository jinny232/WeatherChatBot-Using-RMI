This is mini java project to ask for the weather forecast and ai chatting. I added the manual so that you can follow along and learn from my project . 
Manual
1.  Install Maven
-----------------------------
- Extract the zip file to a folder, for example:
  C:\apache-maven-3.9.10

2. Configure Environment Variables (Windows)
--------------------------------------------
- Open Environment Variables settings:
  Search "Edit environment variables" in Start Menu

- Under User variables, add:
  Variable name: MAVEN_HOME
  Variable value: C:\apache-maven-3.9.10  (or your extracted folder)

- Edit Path variable, add a new entry:
  %MAVEN_HOME%\bin

- Open PowerShell or Command Prompt, verify Maven:
  mvn -version
  (You should see Maven version info)

3. Java Version
---------------
- Use JDK 22 (Java 22)
- Check by running:
  java -version

5. Running the Project
----------------------
- Open terminal in the folder and run:
  mvn clean compile exec:java -Dexec.mainClass=ChatBotWeatherUI

- This compiles and runs the Swing UI chatbot app.
click X button or typ0e "exit" to close 
--------------------------------------------
Enjoy your AI + Weather desktop chatbot app!
--------------------------------------------



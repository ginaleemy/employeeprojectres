
----------------------------------------------------
Introduction 
----------------------------------------------------
This Spring REST project was built using Spring Boot 3.3.4. 
The project uses a testing database named Hikari. 
Each time the server is restarted, the data will be refreshed.

----------------------------------------------------
Environment Setup
----------------------------------------------------
1) Download Java
Version: At least JDK 17.0.12
Download Path: Java JDK 17 Archive Downloads

2) Download Latest Eclipse
Version: Latest version (2024)
Download Path: Eclipse Downloads

3) Add the plugin "Talend API Tester - Free Edition" in Google Chrome.


----------------------------------------------------
API Documentation
----------------------------------------------------
After starting the local server, open http://localhost:8080/swagger-ui/index.html.

----------------------------------------------------
Steps to Startup the Project
----------------------------------------------------
1)Ensure your Java version is 17.

2) Download the project named "employeeprojectres-main" as a ZIP file.
   
4) Open Eclipse. Go to File > Import > Maven > Existing Maven Projects.
   
6) Browse to the location of the ZIP file. In the Projects box, tick the pom.xml.
   
8) Click Next until finished.
   
10) After importing, in the Eclipse menu, navigate to Project > Properties > Java Build Path > Libraries tab > Classpath.
    
12) Click Add External JARs. Select the restful-web-services01\lib\h2-2.2.224.jar file and click Open.
    
14) Click Apply and Close.
    
16) Under the project name "employee-project-api," right-click and select Run As > Maven Clean. All the JARs should be downloaded in the background by Maven.
    
18) In the Project Explorer, check that the "Maven Dependencies" folder appears with all the attached JAR files. If the folder does not exist, click Maven Install.
    
20) If the "Maven Dependencies" folder appears, run RestfulApiEmpApplication.
    
22) If you see the message "Started RestfulApiEmpApplication" in the console, you have successfully started the application.
    
24) Open Google Chrome and paste http://localhost:8080/actuator to check the application status. If prompted to sign in, use the username username and password password (set up in application-dev.properties).
    
26) Copy http://localhost:8080/actuator/health. If everything is set up successfully, you should see {"status":"UP"}.
    
28) To view the API documentation, go to Swagger UI: http://localhost:8080/swagger-ui/index.html.

----------------------------------------------------
Testing
----------------------------------------------------
You can start testing using the following methods:

i. Swagger-UI: Click the method and then click "Try It Out."

ii. Open Google Chrome and use Talend API Tester: Paste the following to start API testing:

Method: GET
Path: http://localhost:8080/api/employeeprojects

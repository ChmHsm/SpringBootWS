#SpringBootWS

Getting the heck of Spring Boot.
Following Spring's official tutorial <a href="https://spring.io/guides/tutorials/bookmarks/">here</a>, this repository includes 2 <b>model</b> classes (in the model directory) and 3 "versions" of the same <b>web service</b>: an open version (no security), a HATEOAS version (which use I don't fully understand yet) in the hateoas directory, and finally a secured (Spring security and Oauth) version in the security directory.

Things to know about this WS repo:
- It is intended to be deployed on <b>heroku directly from github</b>. Hence, the unsecured version of the WS (/rest) containt a pom that specifies a war packaging. The root directory also contains a <b>Procfile</b> that specifies the commands that the heroku container runs in order to deploy the app.
- The root directory contains an <b>Application.properties</b> file that disables Springs default security. (No login dialogbox for every request)
- If you want to test the secured version of the WS, you'll need to create your own <b>tomcat.keystore</b> in the /security/src/main/resources directory, based on the application-https.properties file in the <b>same</b> directory.

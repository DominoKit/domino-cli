#set($hash='#')
#set($hash4='####')
#set($hash5='#####')
$hash How to run

- $hash4 run `mvn clean install` to build

- $hash4 Run for development :

  - $hash5 For super dev mode 
  
    - In one terminal run `mvn gwt:codeserver -pl *-frontend -am`
    
    - In another terminal `cd ${name}-backend`
    - execute `mvn exec:java`
    - the server port will be printed in the logs access the application on `http://localhost:[port]`

  - $hash5 For gwt compiled mode 
  
    - `cd ${name}-backend`
    - execute `mvn exec:java -Dmode=compiled`
    - the server port will be printed in the logs access the application on `http://localhost:[port]`

  - $hash5 For production mode 
  
    - `cd ${name}-backend`
    - execute `java -jar target/${name}-backend-${version}-fat.jar`
    - the server port will be printed in the logs access the application on `http://localhost:[port]`

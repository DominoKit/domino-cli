# How to run

- #### run `mvn clean verify` to build

- #### Run for development :

  - ##### For development 
  
    - In one terminal run `mvn j2cl:watch`
    
    - In another terminal `cd ${name}-backend`
    - execute `mvn exec:java`
    - the server port will be printed in the logs access the application on `http://localhost:[port]`

  - ##### For gwt compiled mode 
  
    - `cd ${name}-backend`
    - execute `mvn exec:java -Dmode=compiled`
    - the server port will be printed in the logs access the application on `http://localhost:[port]`

  - ##### For production mode 
  
    - `cd ${name}-backend`
    - execute `java -jar target/${name}-backend-${version}-fat.jar`
    - the server port will be printed in the logs access the application on `http://localhost:[port]`

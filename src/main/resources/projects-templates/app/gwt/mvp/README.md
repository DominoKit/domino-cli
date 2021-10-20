# How to run

- #### run `mvn clean install` to build

- #### Run for development :

  - ##### For super dev mode 
  
    - In one terminal run `mvn gwt:codeserver -pl *-frontend -am`
    
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

  - ##### Running the API server
    - In a separate terminal `cd ${name}-api` and from there follow the readme file inside `${name}-api` module


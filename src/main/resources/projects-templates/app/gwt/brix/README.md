# ${name}

DominoKit Brix application scaffold (frontend, backend, shared modules).

## Project structure

- `${name}-frontend`: GWT client module and web resources.
- `${name}-backend`: Quarkus backend (REST endpoints, static assets).
- `${name}-shared`: shared DTOs and utilities used by frontend/backend.
- `.idea/runConfigurations`: optional IDE run configs (if generated).

## Build

Build all modules:

```
mvn clean verify
```

## Run (development)

Start the Quarkus backend in one terminal:

```
mvn compile quarkus:dev -pl ${name}-backend
```

Start the GWT code server in another terminal:

```
mvn gwt:codeserver -pl ${name}-frontend -am
```

## Run (production)

Follow the instructions in `${name}-backend/README.md` to build and run the backend.

## More info

- Domino Brix: https://www.dominokit.com/solutions/domino-brix
- Domino CLI: https://www.dominokit.com/resources/domino-cli

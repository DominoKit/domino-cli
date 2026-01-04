NOTE: Update this file with any learnings and findings after every task or session.

# domino-cli AI context

## Purpose
`domino-cli` is a Quarkus-based CLI that scaffolds DominoKit projects and modules. It uses Picocli for commands and FreeMarker templates plus static resources embedded under `src/main/resources/projects-templates`.

## Entrypoint and runtime
- Main entrypoint: `src/main/java/org/dominokit/cli/DominoCli.java` (Quarkus main).
- Top-level Picocli command: `src/main/java/org/dominokit/cli/commands/DominoCommand.java` with subcommands `generate`/`gen`.
- Quarkus packaging uses an uber-jar (`quarkus.package.type=uber-jar`) and includes templates in native resources via `src/main/resources/application.properties`.

## CLI commands and behavior
Reference help text and examples: `README.md`, `COMMAND_USAGE.md`.

### `domino generate app`
Creates a new DominoKit project scaffold.
- Command options are defined in `src/main/java/org/dominokit/cli/commands/GenerateAppCommand.java`.
- Key flags:
  - `--name` (required): project name and artifactId.
  - `--groupId`: groupId and root package.
  - `--dir`: absolute output dir, defaults to current working directory.
  - `--type`: `basic`, `mvp`, `brix` (default `brix`).
  - `--version`: `v1`, `v2`, `dev` (default `v2`).
  - `--generate-api`: adds an API module (Quarkus JAX-RS). Not supported for Brix apps.
  - `--compiler`: `gwt` or `j2cl` (default `gwt`).
- If the CLI runs inside an existing Maven project, it attempts to load the parent `pom.xml` and:
  - Reuses the parent groupId/version.
  - Adds the new project to the parent `<modules>` list.
  - This logic lives in `GenerateAppCommand.addProjectToParent`.

### `domino generate module`
Adds a module to an existing DominoKit project (expects to run from a generated project root).
- Command options are defined in `src/main/java/org/dominokit/cli/commands/GenerateModuleCommand.java`.
- Key flags:
  - `--name` (required): module artifactId.
  - `--single`: merge client/shared into one module, no backend module.
  - `--tests`: generate tests in multi-module layout.
  - `--framework`: `mvp` (default) or `brix`.
  - `--backend`: generate backend module (not supported for Brix).
  - `--compiler`: `gwt` or `j2cl`.
  - `--subpackage`: appended to project root package.
  - `--prefix`: class-name prefix (defaults to module name).
  - `--dir`: absolute output dir, defaults to current working directory.
- `Module.init()` loads `pom.xml`, `<artifactId>-frontend/pom.xml`, and `<artifactId>-backend/pom.xml`. Missing these files will cause errors, so `generate module` should run from a valid DominoKit project root.

## Project generation architecture
Scaffolding is modeled as folders and files, written to disk with conditions.

### Core types
- `Folder` (`src/main/java/org/dominokit/cli/generator/Folder.java`): a tree node with child folders/files; `write` creates directories and delegates to children.
- `Package` (`src/main/java/org/dominokit/cli/generator/Package.java`): `Folder` that converts dot packages into folder paths.
- `ProjectFile` interface: implemented by `TemplatedFile` and `ResourceFile`.
- `TemplatedFile`: renders FreeMarker templates to disk (skips if file exists).
- `ResourceFile`: copies raw resources from the classpath (skips if file exists).

### Template rendering
- `TemplateProvider` configures FreeMarker to load from `/projects-templates/<version>`.
- Versions are defined in `VersionProfile` (`src/main/java/org/dominokit/cli/VersionProfile.java`).
- Template variables include the project/module context plus tool versions.
- System properties with the same keys as version variables override defaults.

### Version profiles
`VersionProfile.setVersion` must be called before template rendering. Profiles:
- `v1`, `v2`, `dev` with different DominoKit, Quarkus, GWT, and J2CL versions.
- `dev` uses the `v2` template path and HEAD-SNAPSHOT for most DominoKit artifacts.

## Project types (app generation)
Factories are selected by compiler and project type.

### GWT projects
Factory: `GWTProjectFactory` in `src/main/java/org/dominokit/cli/generator/project/gwt/GWTProjectFactory.java`.
- `basic`: client/shared/server, plus optional API module.
  - Generator: `GwtBasicProject`.
- `mvp`: frontend/backend + IDEA run configs, plus optional API module.
  - Generator: `GwtMVPProject`.
- `brix`: frontend/backend/shared with Quarkus backend assets, plus IDEA run configs.
  - Generator: `GwtBrixProject`.

### J2CL projects
Factory: `J2CLProjectFactory` in `src/main/java/org/dominokit/cli/generator/project/j2cl/J2CLProjectFactory.java`.
- `basic`: `J2clBasicProject`.
- `mvp`: `J2clMVPProject`.
- No `brix` support in J2CL.

## Module types (module generation)
Factories are selected by compiler, framework, and `--single`.

### GWT modules
Factory: `GWTModuleFactory` (`src/main/java/org/dominokit/cli/generator/module/gwt/GWTModuleFactory.java`).
- MVP:
  - Single module: `GwtSingleModule` (client+shared under one module).
  - Multi module: `GwtMultiModule` (backend, frontend, frontend-ui, shared).
- Brix:
  - Multi module only: `BrixMultiModule` (frontend, ui, shared).
- Both MVP generators also inject dependencies into existing frontend/backend poms and add the module to the root `<modules>` list.

### J2CL modules
Factory: `J2CLModuleFactory` (`src/main/java/org/dominokit/cli/generator/module/j2cl/J2CLModuleFactory.java`).
- Uses the same GWT templates (`GwtSingleModule` or `GwtMultiModule`).

## API module
`ApiModuleFactory` builds a Quarkus REST module using templates under `projects-templates/<version>/api`.
- Generated when `--generate-api` is true.
- Adds Dockerfile variants, JAX-RS resource, and a Quarkus README.

## File templates and resources
- Templates live under `src/main/resources/projects-templates/v1` and `v2`.
- FreeMarker templates use context keys defined by `Project.context()` and `Module.context()`.
- Binary assets (icons/images) are copied via `ResourceFile`.

## Key data model and context
### Project context (`Project.context()`)
- `name`, `groupId`, `artifactId`, `version`
- `rootPackage`, `moduleShortName`
- `hasParent`, `parentArtifactId`
- `generateApi`, `compiler`
- `port` (9090 if API module is generated, otherwise 8080)

### Module context (`Module.context()`)
- Project fields plus:
  - `artifactId`, `rootArtifactId`
  - `subpackage`, `moduleName`, `modulePackage`
  - `prefix`, `token`
  - `projectPom`, `frontendPom`, `backendPom`
  - `generateTests`, `compiler`, `generateBackend`

## Notable behaviors and constraints
- File writes are skipped if the target already exists; repeated commands are additive but do not overwrite templates.
- Module generation assumes `*-frontend` and `*-backend` pom files exist (MVP and Brix use different layouts).
- Brix apps/modules are GWT-only and do not support API module or backend module options.
- J2CL modules reuse GWT templates; the structural layout mirrors GWT modules.

## Build and packaging
- Maven build uses Quarkus plugin; Java release 17.
- Installers can be built with Install4j profile (see `pom.xml` and `domino-cli.install4j`).
- Example build command is documented in `README.md`.


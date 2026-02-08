# Domino CLI command usage

The CLI entrypoint is the `dominokit` command. It ships with standard Picocli flags (`-h/--help`, `-V/--version`) and commands for scaffolding applications/modules or managing cached dependency versions.

## Top-level

```
dominokit [-hV] [COMMAND]
```

- `help` prints help for any command path.
- `generate` (`gen`) routes to project and module generators.
- `list-versions` prints the cached release profile versions (may prompt if newer releases are detected).
- `update-versions` checks for newer releases and updates the cache on confirmation.

## `generate` / `gen`

```
dominokit generate [COMMAND]
```

Subcommands:

- `app` — create a DominoKit project scaffold.
- `brix-app` — create a DominoKit Brix app scaffold (shortcut for `-t brix`).
- `basic-app` — create a DominoKit basic app scaffold (shortcut for `-t basic`).
- `module` — add a DominoKit module to an existing project.

## `list-versions`

Prints the cached release profile versions (from `~/.domino-cli/versions.json`). If Maven Central reports newer releases for auto-update groupIds, it prompts to update the cache.

```
dominokit list-versions
```

## `update-versions`

Checks Maven Central for newer release versions in the auto-update groupIds and updates the cache if approved. If stdin is unavailable, it defaults to "no" and keeps cached values.

```
dominokit update-versions
```

## `generate app`

Creates a new DominoKit project (basic or Brix). If you run it from inside an existing Maven project, the tool tries to read the parent `pom.xml` so the new module inherits groupId/version and is added to `<modules>`. For Brix apps, the CLI prompts to optionally create a default `shell` module after generation.

```
dominokit generate app \
  -n <name> \
  [-g <groupId>] \
  [-d <absoluteOutputDir>] \
  [-t basic|brix] \
  [-dev] \
  [--generate-api[=<true|false>]]
```

Options:

- `-n`, `--name` (required): Project name; used as `artifactId` and to derive a module short name.
- `-g`, `--groupId`: Group ID and root package. If omitted and a parent POM is found, the parent groupId is reused.
- `-d`, `--dir`: Absolute directory to generate into; defaults to the current working directory.
- `-t`, `--type` (default `brix`): Template type. `basic` creates client/shared/server; `brix` creates a domino-brix frontend/backend/shared layout with assets and run configs.
- `-dev`, `--dev` (default `false`): Use HEAD-SNAPSHOT versions for DominoKit dependencies during generation.
- `--generate-api`, `-api` (default `true`): Generate an API module using Quarkus REST (JAX-RS). Not supported for Brix apps.

Example:

```
dominokit gen app -n sample -g org.dominokit.samples -t basic -api=false
```

## `generate brix-app`

Shortcut for `generate app -t brix` with the same options (without the `-t` flag).

```
dominokit generate brix-app \
  -n <name> \
  [-g <groupId>] \
  [-d <absoluteOutputDir>] \
  [-dev] \
  [--generate-api[=<true|false>]]
```

## `generate basic-app`

Shortcut for `generate app -t basic` with the same options (without the `-t` flag).

```
dominokit generate basic-app \
  -n <name> \
  [-g <groupId>] \
  [-d <absoluteOutputDir>] \
  [-dev] \
  [--generate-api[=<true|false>]]
```

## `generate module`

Creates a module inside an existing DominoKit project. Use `--dir` if you want to target a directory other than the current one. Module POMs reference versions from the parent project properties and expect the standard `*-frontend`/`*-backend` layout to exist.

```
dominokit generate module \
  -n <name> \
  [-sp <subPackage>] \
  [-p <prefix>] \
  [-d <absoluteOutputDir>]
```

Options:

- `-n`, `--name` (required): Module name and `artifactId`.
- `-sp`, `--subpackage`: Subpackage appended to the application root package. Defaults to a lowercase, dot-separated form of the module name.
- `-p`, `--prefix`: Prefix for generated class names; falls back to the module name when omitted.
- `-d`, `--dir`: Absolute path where the module should be generated; defaults to the current working directory.

Examples:

```
# Brix module (GWT only)
dominokit gen module -n dashboard -p Layout -sp layout
```

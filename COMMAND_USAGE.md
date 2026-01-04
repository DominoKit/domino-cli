# Domino CLI command usage

The CLI entrypoint is the `domino` command. It ships with standard Picocli flags (`-h/--help`, `-V/--version`) and the `generate`/`gen` subcommand for scaffolding applications or modules.

## Top-level

```
domino [-hV] [COMMAND]
```

- `help` prints help for any command path.
- `generate` (`gen`) routes to project and module generators.

## `generate` / `gen`

```
domino generate [COMMAND]
```

Subcommands:

- `app` — create a DominoKit project scaffold.
- `module` — add a DominoKit module to an existing project.

## `generate app`

Creates a new DominoKit project (basic, MVP, or Brix). If you run it from inside an existing Maven project, the tool tries to read the parent `pom.xml` so the new module inherits groupId/version and is added to `<modules>`.

```
domino generate app \
  -n <name> \
  [-g <groupId>] \
  [-d <absoluteOutputDir>] \
  [-t basic|mvp|brix] \
  [-v v1|v2|dev] \
  [--generate-api[=<true|false>]] \
  [-c gwt|j2cl]
```

Options:

- `-n`, `--name` (required): Project name; also used as `artifactId` and module short name.
- `-g`, `--groupId`: Group ID and root package. If omitted and a parent POM is found, the parent groupId is reused.
- `-d`, `--dir`: Absolute directory to generate into; defaults to the current working directory.
- `-t`, `--type` (default `brix`): Template type. `basic` creates client/shared/server; `mvp` creates a domino-mvp template; `brix` creates a domino-brix template. `brix` requires the `gwt` compiler.
- `-v`, `--version` (default `v2`): Version profile for generated dependencies/templates. Valid values: `v1`, `v2`, `dev`.
- `--generate-api`, `-api` (default `true`): Generate an API module using Quarkus REST endpoints. Not supported for Brix apps.
- `-c`, `--compiler` (default `gwt`): Java-to-JS compiler (`gwt` or `j2cl`). With `j2cl`, only `basic` and `mvp` project types are available.

Example:

```
domino gen app -n sample -g org.dominokit.samples -t mvp -v v2 -api=false
```

## `generate module`

Creates a module inside an existing DominoKit project. Use `--dir` if you want to target a directory other than the current one.

```
domino generate module \
  -n <name> \
  [-sp <subPackage>] \
  [-p <prefix>] \
  [-s] \
  [-t] \
  [-b] \
  [-d <absoluteOutputDir>] \
  [-v v1|v2|dev] \
  [-c gwt|j2cl] \
  [-f mvp|brix]
```

Options:

- `-n`, `--name` (required): Module name and `artifactId`.
- `-sp`, `--subpackage`: Subpackage appended to the application root package. Defaults to a lowercase, dot-separated form of the module name.
- `-p`, `--prefix`: Prefix for generated class names; falls back to the module name when omitted.
- `-s`, `--single` (default `false`): Generate a single combined client/shared module; no backend module is created.
- `-t`, `--tests` (default `false`): Generate tests for multi-module layouts (`--single` must be false).
- `-b`, `--backend` (default `false`): Generate a backend module (Vert.x). Not supported for Brix modules.
- `-d`, `--dir`: Absolute path where the module should be generated; defaults to the current working directory.
- `-v`, `--version` (default `v2`): Version profile; same accepted values as `generate app`.
- `-c`, `--compiler` (default `gwt`): Compiler (`gwt` or `j2cl`).
- `-f`, `--framework` (default `mvp`): Module framework. `mvp` produces domino-mvp modules; `brix` produces domino-brix modules. Brix requires the `gwt` compiler.

Examples:

```
# Multi-module MVP module with tests
domino gen module -n shell -sp shell -p layout -t

# Single-module Brix module (GWT only)
domino gen module -n dashboard -f brix -s
```

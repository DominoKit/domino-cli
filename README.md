# domino-cli

CLI for scaffolding DominoKit applications and modules, plus managing cached dependency versions.

## Install

Download an installer from the releases page:
`https://github.com/DominoKit/domino-cli/releases`

## Quick start

Generate an application:

```
dominokit gen app -n sample -g org.dominokit.samples
```

Generate a module (run inside the generated project root):

```
dominokit gen module -n shell -p Layout -sp shell
```

List or update cached release versions:

```
dominokit list-versions
dominokit update-versions
```

## Commands

Top-level:

```
dominokit [-hV] [COMMAND]
```

Commands:

- `generate` (`gen`): scaffold projects and modules.
- `list-versions`: print cached release profile versions (may prompt if newer releases are available).
- `update-versions`: check Maven Central and update the cache on confirmation.

## generate app

Creates a DominoKit application scaffold (basic or brix). If you run it from inside an existing Maven project, it reads the parent `pom.xml` and reuses the parent groupId/version and adds the new module to `<modules>`.
For Brix apps, the CLI prompts to optionally generate a default `shell` module after the project is created.
Use `generate brix-app` or `generate basic-app` to skip the `-t` flag.

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

- `-n`, `--name` (required): project name; used as the `artifactId` and module short name.
- `-g`, `--groupId`: groupId and root Java package. If omitted and a parent POM is found, the parent groupId is reused.
- `-d`, `--dir`: absolute output directory (defaults to the current directory).
- `-t`, `--type` (default `brix`): template type. `basic` creates client/shared/server modules; `brix` creates a domino-brix frontend/backend/shared layout with assets and run configs.
- `-dev`, `--dev` (default `false`): use HEAD-SNAPSHOT DominoKit versions.
- `--generate-api`, `-api` (default `true`): generate a Quarkus REST (JAX-RS) API module. Not supported for Brix apps.

Example:

```
dominokit gen app -n sample -g org.dominokit.samples -t basic -api=false
```

## generate brix-app

Shortcut for `generate app -t brix`.

```
dominokit generate brix-app \
  -n <name> \
  [-g <groupId>] \
  [-d <absoluteOutputDir>] \
  [-dev] \
  [--generate-api[=<true|false>]]
```

## generate basic-app

Shortcut for `generate app -t basic`.

```
dominokit generate basic-app \
  -n <name> \
  [-g <groupId>] \
  [-d <absoluteOutputDir>] \
  [-dev] \
  [--generate-api[=<true|false>]]
```

## generate module

Creates a module inside an existing DominoKit project. Module POMs reference versions from the parent project properties.

```
dominokit generate module \
  -n <name> \
  [-sp <subPackage>] \
  [-p <prefix>] \
  [-d <absoluteOutputDir>]
```

Options:

- `-n`, `--name` (required): module name and `artifactId`.
- `-sp`, `--subpackage`: subpackage appended to the application root package. Defaults to a lowercase, dot-separated form of the module name.
- `-p`, `--prefix`: prefix for generated class names; defaults to the module name.
- `-d`, `--dir`: absolute output directory (defaults to the current directory).

Example:

```
dominokit gen module -n dashboard -p Layout -sp layout
```

## list-versions

Prints cached release profile versions from `~/.domino-cli/versions.json`. If newer releases are available for auto-update groupIds, it prompts to update the cache.

```
dominokit list-versions
```

## update-versions

Checks Maven Central for newer release versions in the auto-update groupIds and updates the cache on confirmation. If stdin is unavailable, it defaults to "no" and keeps cached values.

```
dominokit update-versions
```

## Build

```
./mvnw -Pinstall4j,release package -Dinstall4j.home=/home/vegegoku/install4j10 -Drelease.version=0.0.0-HEADSNAPSHOT
```

# domino-cli
CLI tool to generate domino projects and modules.

### How to install:

navigate to [releases](https://github.com/DominoKit/domino-cli/releases) then download and install tour preferred package.

**npm** users can install the cli tool by executing `npm i dominokit`

### Usage :

- ##### Generate application template

The cli tool can be used to create a new domino-mvp project template :

e.g : to generate and application with name `sample` and groupId `org.dominokit.samples` execute the command below

`dominokit gen app -n sample -g org.dominokit.samples` 

for more help about the generate app command execute `dominokit gen app help`


- ##### Generate a module template 

once an application template is generated, we cd inside the generated application e.g `cd sample`.

then to generate a new module with the name `layout` and sub-package `layout` we execute the command below

`dominokit gen module -n layout -sp layout`

this will generate a module that is split into 4 sub-modules.

to generate a module without sub-modules use the `-s` option

`dominokit gen module -n layout -sp layout -s`


#### Detailed instructions 

```
Usage: domino [COMMAND]
Executes domino commands
Commands:
  help           Displays help information about the specified command
  generate, gen  Generates a domino template project/module
```

```
Usage: domino generate [COMMAND]
Generates a domino template project/module
Commands:
  help    Displays help information about the specified command
  app     Use with generate command to generate a domino-mvp template project
  module  Use with generate command to generate a domino-mvp module template
```

```
Usage: domino generate app [-d=<workingDire>] -g=<groupId> -n=<name> [COMMAND]
Use with generate command to generate a domino-mvp template project
  -d, --dir=<workingDire>   absolute path to the directory where the project
                              should be generated.
  -g, --groupId=<groupId>   The project group ID, this will be used also for
                              root package name
  -n, --name=<name>         The project name, also will be use as the artifact
                              ID
  -t, --type=<type>         The type of the project, available types are [mvp,
                              ui], [mvp] will generate a domino-mvp
                              application, [ui] will generate simple gwt with
                              domino-ui application.
Commands:
  help  Displays help information about the specified command
```

```
Usage: domino generate module [-st] [-d=<workingDire>] -n=<name>
                              [-sp=<subPackage>] [COMMAND]
Use with generate command to generate a domino-mvp module template
  -d, --dir=<workingDire>   absolute path to the module where the project
                              should be generated.
  -n, --name=<name>         The module name, also will be use as the artifact ID
  -s, --single              if true will split the module into more submodules,
                              [name]-backend, [name]-frontend, [name]
                              -frontend-ui, [name]-shared
  -sp, --subpackage=<subPackage>
                            the module sub package, this will be appended to
                              the application rootPackage
  -t, --tests               if true will generate tests for a multi submodules
                              module.
Commands:
  help  Displays help information about the specified command
```
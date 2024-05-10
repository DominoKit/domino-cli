# domino-cli
CLI tools to generate domino projects and modules.

### How to install:

navigate to [releases](https://github.com/DominoKit/domino-cli/releases) then download and install your preferred package.


### Usage :

- ##### Generate application template

The cli tool can be used to create a new domino-mvp project template :

e.g : to generate and application with name `sample` and groupId `org.dominokit.samples` execute the command below

`dominokit gen app -n sample -g org.dominokit.samples` 

for more help about the generated app command execute `dominokit gen app help`


- ##### Generate a module template 

once an application template is generated, we `cd` inside the generated application e.g `cd sample`.

then to generate a new module with the name `shell` and sub-package `shell` that include a `layout` proxy we execute the command below

`dominokit gen module -n shell -p layout -sp shell`

this will generate a module that is split into 4 sub-modules.

to generate a module without sub-modules use the `-s` option

`dominokit gen module -n shell -p layout -sp shell -s`


#### Detailed instructions 

```
Usage: domino [-hV] [COMMAND]
Executes domino commands
Use this command to generate basic template project or an MVP project.

  -h, --help      Show this help message and exit.
  -V, --version   Print version information and exit.
Commands:
  help           Display help information about the specified command.
  generate, gen  Generates a domino template project/module
```

```
Usage: domino generate [COMMAND]
Generates a domino template project/module
Commands:
  help    Display help information about the specified command.
  app     Use with generate command to generate a domino-mvp template project
  module  Use with generate command to generate a domino-mvp module template

```

```
Usage: domino generate app [-api] [-c=<compiler>] [-d=<workingDire>]
                           [-g=<groupId>] -n=<name> [-t=<type>] [-v=<version>]
                           [COMMAND]
Use with generate command to generate a domino-mvp template project
  -api, --generate-api       If true will generate an api module for REST endpoints implementation, 
                                current implementation is Quarkus with jax-rs. - not supported by Brix app -.
                                
  -c, --compiler=<compiler>  The Java to JavaScript compiler to be used possible values [gwt, j2cl] default is [gwt].
  
  -d, --dir=<workingDire>    absolute path to the directory where the project should be generated.
  
  -g, --groupId=<groupId>    The project group ID, this will be used also for root package name.
  
  -n, --name=<name>          The project name, also will be use as the artifact ID.
  
  -t, --type=<type>          The type of the project :
                             -[basic] : will generate a simple project with (client, shared, server)
                             -[mvp] : will generate a domino-mvp project template.
                             -[brix] : will generate a domino-brix project template.
                             
  -v, --version=<version>    DominoKit tools version
                             -[v1] : Will generate a project using DominoKi tools version 1.x.x. - not supported by brix -
                             -[v2] : Will generate a project using DominoKi tools version 2.x.x.
                             -[dev] : Will generate a project using DominoKit tools HEAD-SNAPSHOT versions.
Commands:
  help  Display help information about the specified command.

```

```
Usage: domino generate module [-bst] [-c=<compiler>] [-d=<workingDire>]
                              -n=<name> [-p=<prefix>] [-sp=<subPackage>]
                              [COMMAND]
Use with generate command to generate a domino-mvp module template
  -b, --backend             if true will generate a domino-mvp backend module, default implementation is vertx.
                              
  -c, --compiler=<compiler> The Java to JavaScript compiler to be used possible values [gwt, j2cl] default is [gwt].
                              
  -d, --dir=<workingDire>   absolute path to the module where the project should be generated.
                              
  -n, --name=<name>         The module name, also will be use as the artifact ID.
  
  -p, --prefix=<prefix>     The module prefix to be used in the generated
                              classes name, if not present module name will be used instead.
                              
  -s, --single              If true it will merge client an shared as one module, a backend module will not be generated.
                              
  -sp, --subpackage=<subPackage>
                            the module sub package, this will be appended to the application rootPackage.
                              
  -t, --tests               if true will generate tests for a multi submodules module.
Commands:
  help  Displays help information about the specified command

```
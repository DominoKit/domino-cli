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

`dominokit gen module -n layout -p layout`

this will generate a module that is split into 4 sub-modules.

to generate a module without sub-modules use the `-s` option

`dominokit gen module -n layout -p layout -s`

for more help with the generate module command please execute 

`dominokit gen module help`

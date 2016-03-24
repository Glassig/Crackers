# The Game

## Needed in order to run
You need node.js from https://nodejs.org/en/
There you can also get npm, so get that. Every package downloaded with npm need to be installed using --save (ie, npm install _ --save) so it is locally saved. For easier use.

## Commands
On mac, use command "node app.js"
On Linux, use command "nodejs app.js" (apparently "node" is an old and bad package that comes with linux, so let's not use that).

## PoS-tagger
We got the PoS-tagger from https://www.npmjs.com/package/parts-of-speech. Documentation is available there.
It has massive flaws. like, move and jump are nouns. so we have to figure all of those out and convert them or have special-cases.

## Favicon
Right now we got one from http://www.favicon.cc/?action=icon&file_id=545707, which I basically use so I can easier find the tab. 
If you want to create a custome favicon: (only tips) http://www.favicon-generator.org/editor/ and then replace it in the folder /public
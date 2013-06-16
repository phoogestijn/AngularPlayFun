# AngularPlayFun
*A demo application of Angular JS MVC, Coffeescript, Less, Play Scala, Webjars. 

##About
Based on [AngularFun](https://github.com/CaryLandholt/AngularFun) with the server part ported to the Play framework and Scala.

Server side and client side logging shows
- the interaction between browser and server with a JS MVC application
- the JS MVC internal mechanisms:
  - browser side url routing
  - event broadcasting and listening
- REST style json apis

##Prerequisites
Java 7 and [Sbt](http://www.scala-sbt.org/).

##Running
`sbt ~run` runs the application in dev mode, continuously watching for changes.  
`sbt stage` builds the application for staging. The application can then be run by `target/start`  

##Known issues

- requirejs optimisation doesn't work with the special requirejs library provided by webjars-play. This seems to be 
an issue with webjars-pla
- unittesting of js with jasmine doesnt work



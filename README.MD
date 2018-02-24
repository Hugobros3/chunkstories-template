# chunkstories-template

![alt text](http://chunkstories.xyz/img/github_header4.png "Header screenshot")

## A template for making your own mods and plugins

A template that comes with a basic mod consisting of some basic pieces of content, and a client/server agnostic plugin that answers 'Hello World' to `/template`

### Configuring

Edit `build.gradle` and `settings.gradle` to configure your mod's internal name, shown name, version and description. Comment out the two lines mentioned in `settings.gradle` to disable the plugin subproject.

If you want to use the plugin template too, you can fill in your information in `plugin/src/main/resources/plugin.info`. It will inherit the name of your mod specified earlier.

### Building

 * `./gradlew mod` Builds the mod.txt using the information in build.gradle and settings.gradle and then packages up the mod. The finished mod is available under `build/distributions/yourmodname.zip`
 * `./gradlew install` Does the above step and then looks for your chunkstories install directory in `gradle.properties` to put it directly in your `mods` folder.
 * `./gradlew plugin` Builds and packages the plugin. Resulting plugin can be found under `plugin/build/libs/yourmodname-1.0.jar`

### License

It's a template. Do whatever you want to with this !
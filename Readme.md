# Babylon Posts

## Architecture
The app uses a feature module approach.
In this case the feature modules are 
- postlist 
- postdetails 

Both rely on the 
- core module
 
The core module contains all the dependencies which the feature modules need and also sets them up.

The app module then simply combines the needed feature modules by implementing them in it's own gradle file and 
coordinating there usage.

Apart from that it uses a MVVM pattern with the ViewModel from Jetpack's Architecture Components and a repository 
pattern to be able to provide the data from different sources. In this case the data sources are `network` and `memory cache`. 
Once the data is loaded it is retrieved from memory unless the `forceRefresh` flag is set. 

## Technology

- Kotlin
- Kotlin Coroutines
- Retrofit
- Glide
- Koin
- JUnit 4

## Tests

- Due to it's simplicity the project is only unit tested - You can run the tests by typing
```
./gradlew testAll 
```
This combines the unit tests from all modules

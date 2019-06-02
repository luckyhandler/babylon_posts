# Babylon Posts - A Showcase Application

## Architecture
The app uses a feature module approach.
In this case the feature modules are 
- `postlist` module
- `postdetails` module

In a real life scenario this would probably have been one feature module but for demonstration purposes they are split into two now.

All feature modules rely on the  
- `core` module

The `core` module contains all the dependencies the feature modules need and also sets them up.
It also shares the app's resources like colors, dimensions, strings, styles, themes, and typography.

The 
- `app` module 

then simply combines the needed feature modules by implementing them in it's own gradle file and 
coordinating their usage. The app follows the single activity pattern --> each feature module contains fragments 
which are displayed within this one activity.

Navigation is achieved with Jetpack's Navigation Graph. The nav_graph.xml is also situated within the `app` module. 

Apart from that the application uses a MVVM pattern with the ViewModel from Jetpack's Architecture Components and a repository 
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
This task combines the unit tests from all modules

## Design

The app's theme inherits from the MaterialComponents theme to be able to use the Material Theming, e.g. components like 
[MaterialCardView](https://material.io/develop/android/components/material-card-view/).

The parallax effect in the details screen is achieved with a CoordinatorLayout with a CollapsingToolbarLayout. 
The hero animation is done with a Shared Element Transition.
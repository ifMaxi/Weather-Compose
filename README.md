# Weather Compose

A weather app that provides real-time information about the location provided. 

The app is made up of two screens:

- A ***welcome screen*** that welcomes you when you enter the app **(for demonstration the app shows this screen every time it starts, in a production app this screen should be shown only once.)**
- The ***main screen*** which will show information obtained from the Api [Weather Api](https://www.weatherapi.com/) such as current weather, humidity, sunrise and sunset, a list with hourly data of the day's weather and the next day's weather

**NOTE**: Due to limitations of the API, only data from the current day and the next day is shown.

> [!IMPORTANT]
> In order to obtain the data from the **API** you will need an ***API_KEY*** that you will get from [Weather Api](https://www.weatherapi.com/). Once you obtain the ***API_KEY*** you will need to place it in your ***local.properties*** file. In the ***Build.gradle*** file at the module level, the configuration is already listed.

## Architecture

The architecture used for the project was **MVVM (Model - View - ViewModel)**. This aims to separate the **UI (View)** from the **business logic (ViewModel)** and **data (Model)**.

![MVVM architecture graph](https://github.com/ifMaxi/WeatherCompose/assets/112733459/d3584ce6-40a7-489a-a845-1d98a4cbfe8b)

## Language and libraries

- Kotlin
- Jetpack
    - Compose
    - Navigation
    - ViewModel
    - Hilt
- Material Ui 3
- Flow
- Retrofit
- Kotlin Serialization
- Coil
- Lottie
- Mockk

## Project structure
**TODO**

## Screenshots
**TODO**

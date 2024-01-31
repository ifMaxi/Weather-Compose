# Weather Compose

A weather app that provides real-time information about the location provided. 

The app is made up of two screens:

- A ***welcome screen*** that welcomes you when you enter the app **(for demonstration the app shows this screen every time it starts, in a production app this screen should be shown only once.)**
- The ***main screen*** which will show information obtained from [Weather Api](https://www.weatherapi.com/) such as current weather, sunrise and sunset, a list with hourly data of the day's weather and the next day's weather

> [!NOTE]
> Due to limitations of the API, only data from the current day and the next day is shown.

> [!IMPORTANT]
> When you have the project in Android Studio and Gradle finishes synchronizing, you will need an ***API KEY*** that you can obtain by registering from the following link [Weather Api](https://www.weatherapi.com/).
>
> Once you finish, you will need to place the **KEY** in your ***"local.properties"*** file with the name **"KEY"** and import it into the ***"ApiKeyInterceptor"*** file that you will find in the following field: ***main/java/com/maxidev/weather/data/network/remote/ApiKeyInterceptor***.
>
> The configuration in the **Build.Gradle** file at the App level has already been done.

## Architecture

The architecture used for the project was **MVVM (Model - View - ViewModel)**. This aims to separate the **UI (View)** from the **business logic (ViewModel)** and **data (Model)**.

![MVVM architecture graph](https://github.com/ifMaxi/WeatherCompose/assets/112733459/d3584ce6-40a7-489a-a845-1d98a4cbfe8b)

## Language and libraries

- [Kotlin](https://kotlinlang.org/docs/home.html)
- Jetpack
    - [Compose](https://developer.android.com/jetpack/compose?hl=en)
    - [Navigation](https://developer.android.com/guide/navigation?hl=en)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=en)
    - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=en)
- [Material Ui 3](https://m3.material.io/)
- [Flow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
- [Retrofit](https://square.github.io/retrofit/)
- [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html)
- [Coil](https://coil-kt.github.io/coil/)
- [Lottie](https://lottiefiles.com/)
- [Mockk](https://mockk.io/)

## Screenshots

### Light Mode

| Welcome Screen  | StandBy |
| ------------- | ------------ |
| <img src="https://github.com/ifMaxi/WeatherCompose/assets/112733459/9ce740e8-ac27-4471-8629-1b153f163e71" width="290" height="600">  | <img src="https://github.com/ifMaxi/WeatherCompose/assets/112733459/0c1a0157-74af-4769-9a15-18fef090e77d" width="290" height="600"> |

| Error Screen | Main Screen |
| ------------ | ------------ |
| <img src="https://github.com/ifMaxi/WeatherCompose/assets/112733459/d18d5180-f66e-4b33-af7b-c006c7b99046" width="290" height="600"> | <img src="https://github.com/ifMaxi/WeatherCompose/assets/112733459/e3d38a1e-ca15-4eb5-bdc8-7404258e69e2" width="290" height="600"> |

### Dark Mode

| Welcome Screen  | StandBy |
| ------------- | ------------ |
| <img src="https://github.com/ifMaxi/WeatherCompose/assets/112733459/19695209-88b1-4dff-a9cd-1b63271229ca" width="290" height="600">  | <img src="https://github.com/ifMaxi/WeatherCompose/assets/112733459/88ac217b-4597-4b62-ba17-86c319bd321f" width="290" height="600"> |

| Error Screen | Main Screen |
| ------------ | ------------ |
| <img src="https://github.com/ifMaxi/WeatherCompose/assets/112733459/114f7677-106d-4348-afa7-cc19598c0912" width="290" height="600"> | <img src="https://github.com/ifMaxi/WeatherCompose/assets/112733459/7525d845-ced9-48dd-aa06-00300f46c8cd" width="290" height="600"> |

## Demo

| Welcome Screen  | Main Screen | Dark Mode |
| ------------- | ------------ | ------------ |
| <img src="https://github.com/ifMaxi/WeatherCompose/assets/112733459/4acb2163-d419-4812-a89f-0eb2d3e817d1" width="290" height="600">  | <img src="https://github.com/ifMaxi/WeatherCompose/assets/112733459/ccdb40bb-7da1-46c2-825a-23d3a82549e8" width="290" height="600"> | <img src="https://github.com/ifMaxi/WeatherCompose/assets/112733459/5c46d910-5407-472f-b033-6e7a1e48d4a3" width="290" height="600"> |

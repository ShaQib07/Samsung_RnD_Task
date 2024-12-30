# SansungR&DTask

##  Overview
This project is developed for the sole purpose of Samsung R&D's recruitment task. It fetches data from the JSON placeholder API and displays them in a list. It primarily uses `IntentService` as background service but can be switched to `WorkManager` with a one time worker since using `WorkManager` is recommended now following the deprecation of `IntentService`.
The switch between `IntentService` and `WorkManager` can be performed by toggling the below `buildConfigField` value.

`if USE_WORK_MANAGER == true` -> use `WorkManager`

`if USE_WORK_MANAGER == false` -> use `IntentService`

## Tech Stack

**Language:** Kotlin

**Architecture:** MVVM + MVI with Clean Architecture

**Background Processing:** IntentService / WorkManager

**Concurrency:** Coroutine + Flow

**Dependency Injection:** Hilt

**Networking:** Retrofit & okhttp

**Mocking:** MockK

**Testing:** JUnit, Espresso

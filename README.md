About The Project
-------------
This android project is a project where football match results are displayed on the screen by accessing Misli API using Kotlin language.

Features
-------------
- Viewing the results of all matches in the leagues
- Grouping matches by leagues
- Adding and removing matches to favorites
- Viewing match details

Tech stack & Open-source libraries
-------------
- Minimum SDK level 24
- Kotlin
- Jetpack
  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - ViewBinding: View binding is a feature that makes it easier to write code that interacts with views.
  - Room: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
  - Hilt: For dependency injection.
- Architecture
  - MVVM Architecture (View - ViewModel - Model)
  - Repository Pattern
- Retrofit2: Construct the REST APIs and listing, grouping, paging network data.
- Coil: An image loading library for Android


Screenshots
-------------
<img src="https://github.com/kyagan/MisliCase/assets/11218070/104d90cc-6379-4c03-a0d9-2cee952558c6)" width="250" height="500" />
<img src="https://github.com/kyagan/MisliCase/assets/11218070/a7c3424e-2a54-4da2-bf28-99560da21679" width="250" height="500" />
<img src="https://github.com/kyagan/MisliCase/assets/11218070/d58fe05d-ad12-44ce-81fb-be4bd67e506f)" width="250" height="500" />



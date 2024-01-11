# Sample Compose, MVVM, Hilt, Retrofit, RoomDB and UnitTest

## Compose 
Jetpack Compose is a modern toolkit for building native Android UI.
Jetpack Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.

Installation:

```Gradle
android {
    buildFeatures {
        compose true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {
    def composeBom = platform('androidx.compose:compose-bom:2023.10.01')
    implementation composeBom
    androidTestImplementation composeBom
    // Choose one of the following:
    // Material Design 3
    implementation 'androidx.compose.material3:material3'
    // or Material Design 2
    implementation 'androidx.compose.material:material'
    // or skip Material Design and build directly on top of foundational components
    implementation 'androidx.compose.foundation:foundation'
    // or only import the main APIs for the underlying toolkit systems
}
```

## MVVM
App Architecture is a way of designing and organizing the code of an app, following some principles and best practices.
Having an architecture can help you create apps that are more robust, testable, and maintainable, even as they grow in size and complexity.
Some of the benefits of having an architecture are:
- It separates the concerns of different parts of the app, such as the UI, the data, and the logic, making the code more readable and modular.
- It reduces the coupling and dependency between the app components, such as activities, fragments, services, etc., making the code more flexible and adaptable.

There are different types of architectures that we can use for your Android app, such as MVC, MVP, MVVM, MVI.
So I used MVVM architectures to build this project.


## Hilt
Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project. Dependency injection is a technique that helps you create more modular, testable, and maintainable code by providing the dependencies of your classes instead of creating them yourself. Hilt is built on top of Dagger, a popular dependency injection framework, and provides some benefits such as:

- Simplified setup and configuration of Dagger components and modules.
- Automatic injection of Android classes, such as activities, fragments, view models, etc.
- Integration with Jetpack libraries, such as Navigation, WorkManager, Compose, etc.
- Enhanced testing and debugging capabilities.

To implement Hilt in your Android app, you need to follow these steps:

Add the Hilt Gradle plugin and dependencies to your project’s build files.
Annotate your Application class with @HiltAndroidApp to trigger Hilt’s code generation and create an application-level component.
Annotate your Android classes, such as activities, fragments, view models, etc., with @AndroidEntryPoint to enable field injection and access the dependencies provided by Hilt.
Define your dependencies and bindings using Hilt modules, which are classes annotated with @Module and @InstallIn. Specify the component scope for each module and use @Provides or @Binds annotations to declare how to provide or bind the dependencies.
Use constructor injection, field injection, or @EntryPoint to inject your dependencies into your classes.


## Retrofit 

Retrofit is a library that allows you to make HTTP requests from a Java or Android application. It can convert the response data into Java objects using various converters. To use Retrofit, you need to do the following steps:

1- Define an interface that represents your API endpoints and annotate them with the HTTP method, URL, and parameters.
2- Create a Retrofit instance using the builder pattern and specify the base URL and the converter you want to use.
3- Use the Retrofit instance to create an implementation of your interface and call the methods to make the requests.
For example, if you want to use Retrofit to access the GitHub API, you could write something like this:

```Kotlin
// Define the interface
interface GitHubService {
  @GET("users/{user}/repos")
  fun listRepos(@Path("user") user: String): Call<List<Repo>>
}
```
You can also use the Retrofit instance to create an implementation of your interface and call the methods to make the requests, like this:

```Kotlin
// Create the Retrofit instance
val retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

// Create the service implementation
val service = retrofit.create(GitHubService::class.java)

// Make the request
val repos = service.listRepos("octocat")
```

You can find more information and examples on the [official website](https://square.github.io/retrofit/) or the [GitHub repository](https://codersee.com/retrofit-with-kotlin-the-ultimate-guide/) of Retrofit.


## RoomDB

RoomDB is a library that allows you to store and access data in a local SQLite database on Android devices.
It simplifies the database operations and provides compile-time verification of SQL queries. 
To use RoomDB with Kotlin, you need to define data entities, data access objects (DAOs), and a database class. You can also use Kotlin coroutines and Flow to perform asynchronous database operations.

```Gradle
dependencies {
    def room_version = "2.6.1"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"

    // To use Kotlin annotation processing tool (kapt)
    kapt "androidx.room:room-compiler:$room_version"
    // To use Kotlin Symbol Processing (KSP)
    ksp "androidx.room:room-compiler:$room_version"

    // optional - RxJava2 support for Room
    implementation "androidx.room:room-rxjava2:$room_version"

    // optional - RxJava3 support for Room
    implementation "androidx.room:room-rxjava3:$room_version"

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation "androidx.room:room-guava:$room_version"

    // optional - Test helpers
    testImplementation "androidx.room:room-testing:$room_version"

    // optional - Paging 3 Integration
    implementation "androidx.room:room-paging:$room_version"
}
```
### Data entity
The following code defines a User data entity. Each instance of User represents a row in a user table in the app's database.

```Kotlin
@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)
```
### Data access object (DAO)
The following code defines a DAO called UserDao. UserDao provides the methods that the rest of the app uses to interact with data in the user table

```Kotlin
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
           "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}
```

### Database
The following code defines an AppDatabase class to hold the database. AppDatabase defines the database configuration and serves as the app's main access point to the persisted data. The database class must satisfy the following conditions:

The class must be annotated with a @Database annotation that includes an entities array that lists all of the data entities associated with the database.
The class must be an abstract class that extends RoomDatabase.
For each DAO class that is associated with the database, the database class must define an abstract method that has zero arguments and returns an instance of the DAO class.

```Kotlin
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
```

### Usage
After you have defined the data entity, the DAO, and the database object, you can use the following code to create an instance of the database:
```Kotlin
val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
```

You can then use the abstract methods from the AppDatabase to get an instance of the DAO. In turn, you can use the methods from the DAO instance to interact with the database:

```Kotlin
val userDao = db.userDao()
val users: List<User> = userDao.getAll()
```

## Unit test
Why do we need a Unit test if we can test it manually on the device, Unit testing has several benefits

1) it enforces you to have a good app architecture without good architecture you can't Unit test

2) let say you have added functionality to a class that already has its unit test in place, now you will verify by running all existing Tests so to check that you did not break anything else when adding new Functionality, it gives you confidence that your code is working.

3) There are  2 cases where a Unit test Fails:

    i) when it does not meet the requirement (when we write the test the first time)
    
    ii) when the requirement is changed (then you have to change test cases as well)

Don't pass context in viewmodel it makes it easier to test.

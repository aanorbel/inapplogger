# InAppLogger
InAppLogger is a very useful app to log your data in app screen. Normally logs will be shown in android studio Logcat. In some cases like remote debugging, you want to display the logs on mobile screen to debug.In that case you can use InAppLogger to display the logs in device screen.

![InAppLogger Demo](https://user-images.githubusercontent.com/4497904/151669760-4691dbfa-3ab8-44d7-bcc5-eb1848492d62.gif)

## Fatures of InAppLogger
- Logging Info
- Logging Error
- Logging Warnings
- Logging API
- Filters Logs
- Clear Log any time
- Auto clean log on startup

# Installation
To get a Git project into your build:

Step 1. Add the JitPack repository to your build file
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```
dependencies {
	 implementation 'com.github.velmurugan-murugesan:InAppLogger:1.4'
}
```

# Usage
To initialize the InAppLogger, You need to add the following line in the oncreate() of the Application class.
```
override fun onCreate() {
        super.onCreate()
        InAppLogger.initialize(this).apply {
            deleteOldLog()
        }
}
```

Adding Logging View in your layout file where you want to show the logs.

```
 <com.velmurugan.inapplogger.InAppLogView
        android:id="@+id/tv"
        android:layout_width="match_parent"
        android:layout_height="350dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```

To show the logger in the view, you need to attach the view into the logger.
```
logger = InAppLogger.loggerInstance!!
logger.attachView(v)
```

## Adding Info Log
```
logger = InAppLogger.loggerInstance!!
logger.i("Info Message added")
```
<img src="https://user-images.githubusercontent.com/4497904/151669796-dedacb04-1734-41c5-90c2-eb9ba9d916b2.png" width=50% height=50%>

## Adding Warning Log
```
logger = InAppLogger.loggerInstance!!
logger.w("warring Message added")
```
<img src="https://user-images.githubusercontent.com/4497904/151669803-d762e641-3e0c-4e1b-9fe9-652b35b66364.png" width=50% height=50%>

## Adding Error Log
```
logger = InAppLogger.loggerInstance!!
logger.e("error Message added")
```
<img src="https://user-images.githubusercontent.com/4497904/151669807-322f3bfe-d1fc-496d-8af9-127f46195eca.png" width=50% height=50%>

## Adding API Log
TO enable the api logging, you need to add the LoggingInterceptor in your retrofit configurations.
```
fun getInstance(context: Context): ApiService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .client(
                        OkHttpClient().newBuilder().addInterceptor(InAppLoggerInterceptor())
                            .build()
                    )
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!
        }
```
<img src="https://user-images.githubusercontent.com/4497904/151669814-ce56d885-d795-481a-8568-3322bb5ab6b8.png" width=50% height=50%>

## Log Filter
Log filtering option available in the Logger view to filter the log to want to see. For example you want to see only the API logs you can select the API in the log filter.
like that we have,
- Info Log Filter
- Warning Log Filter
- Error Log Filter
- API Log Filter
<img src="https://user-images.githubusercontent.com/4497904/151669822-a15556be-c917-4cee-b58b-09a6fc4e8960.png" width=50% height=50%>

## deleting Log
By default, InAppLogger wont delete the logs when launching the app. To enable the you need to call the deleteOldLog() on the initialization of the InAppLogger in Applicaion class.

```
InAppLogger.initialize(this).apply {
            deleteOldLog()
        }
```

Also, you can clear the log on any time using the clear button on the Logger view.





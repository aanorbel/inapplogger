# InAppLogger
InAppLogger is a very useful app to log your data in app screen. Normally logs will be shown in android studio Logcat. In some cases like remote debugging, you want to display the logs on mobile screen to debug.In that case you can use InAppLogger to display the logs in device screen.

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

## Adding Warning Log
```
logger = InAppLogger.loggerInstance!!
logger.w("warring Message added")
```
## Adding Error Log
```
logger = InAppLogger.loggerInstance!!
logger.e("error Message added")
```
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

## Log Filter
Log filtering option available in the Logger view to filter the log to want to see. For example you want to see only the API logs you can select the API in the log filter.
like that we have,
- Info Log Filter
- Warning Log Filter
- Error Log Filter
- API Log Filter



## deleting Log
By default, InAppLogger wont delete the logs when launching the app. To enable the you need to call the deleteOldLog() on the initialization of the InAppLogger in Applicaion class.

```
InAppLogger.initialize(this).apply {
            deleteOldLog()
        }
```

Also, you can clear the log on any time using the clear button on the Logger view.





# RedditNews
 In this task i have used two ways to fetch data and caching it into ( Room database ) in Home Fragment
        and caching it into ( Realm database ) in Explorer Fragment
        Technologies used
       * Kotlin
       * Mvvm
       * Coroutines
       * Room  database used in home fragment
       * Realm database used in explorer fragment
       * Dagger hilt
       * Retrofit
       * navigation component

        * i have used RedditApi to make calling for api with a suspend fun to run code into background
          by Coroutines and i handle it with HomeRepository and HomeViewModel

        * we will call api request when internet connection is available if there is no internet connection we will not call api
          object NetworkStatus will take care about internet connection

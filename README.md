# Kotlin flow

## Coroutines & Suspend function
* `Coroutines` helps to implement asynchronous, non blocking code. 
* To implement this we use `Suspend function`.
* Either you fire and forget using `launch` or wait for data(i.e. single object) using `async`.
> launch
```
    suspend fun deleteUser() {
        CoroutineScope(Dispatcher.IO).launch {
            //Fire and forget code with no return value expected. 
            //If we assign launch to a variable that variable will be of Job type. 
            // This variable can be use to cancel or suspend coroutines.
        }
    }
```

> async
```
    suspend fun createUser(): User {
        val user = CoroutineScope(Dispatcher.IO).async {
            //Network call with expected return of Deferred<User> type.
            //There the user variable will be of Deferred<User> type. 
        }
        return user.await()
    }
```
* Suspend function only return a single object.
* Suspend function work great for things like:
  * Storing some value in database
  * Network calls
  * Doing task that return single value.
* But there are scenarios where you have stream of data:
  * Video streaming
  * FM radio
  * Mobile sending audio single to Bluetooth speaker.


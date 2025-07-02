package com.merajhossen20001.foodrecipe.recipe.core.data

import com.merajhossen20001.foodrecipe.recipe.core.domain.util.Result
import com.merajhossen20001.foodrecipe.recipe.core.domain.util.NetworkError
import kotlinx.coroutines.ensureActive
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class SafeApiCall @Inject constructor() {

    suspend fun <T> execute(apiCall: suspend () -> Response<T>): Result<T, NetworkError> {
        return try {

            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.Success(body)
                } else {
                    Result.Error(NetworkError.SERIALIZATION)
                }
            } else {
                when (response.code()) {
                    408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                    429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
                    in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
                    else -> Result.Error(NetworkError.UNKNOWN)
                }
            }
        } catch (e: IOException) {
            Result.Error(NetworkError.NO_INTERNET)
        } catch (e: HttpException) {
            Result.Error(NetworkError.SERVER_ERROR)
        }  catch (e: Exception) {
            coroutineContext.ensureActive()
            Result.Error(NetworkError.UNKNOWN)
        }
    }
}
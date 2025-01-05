package com.sparshchadha.stocktracker.core.base.domain

import com.sparshchadha.stocktracker.core.common.utils.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): ApiResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful && response.body() != null) {
                    ApiResult.Success(data = response.body()!!)
                } else {
                    ApiResult.Error(
                        code = response.code(),
                        exception = Throwable(response.errorBody()?.string()),
                        message = response.message()
                    )
                }
            } catch (e: HttpException) {
                ApiResult.Error(
                    message = e.message ?: "Something went wrong",
                    exception = Throwable(e.message ?: "Something went wrong"),
                    code = e.code()
                )
            } catch (e: IOException) {
                ApiResult.Error(
                    message = e.message ?: "No Internet Connection!",
                    exception = Throwable(e.message ?: "No Internet Connection!"),
                    code = -1
                )
            } catch (e: Exception) {
                ApiResult.Error(
                    message = e.message ?: "Something went wrong",
                    exception = Throwable(e.message ?: "Something went wrong"),
                    code = -1
                )
            }
        }
    }
}
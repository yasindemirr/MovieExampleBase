package com.example.moviesbaseexample.ui.theme.data.service.interceptor

sealed class ErrorTypes(
    val errorMessage :String? = null,
    val errorCode : Int
) : Exception() {

    /**
     * http isteğinde alınabilecek hatalar
     */
    sealed class NetworkErrorTypes(errorMessage : String? = null, httpCode : Int) : ErrorTypes(errorMessage = errorMessage, httpCode) {

        /**
         * serverdan gelen mesaj ekranda gösterilecek
         */
        object ServerMessageErrorTypes: NetworkErrorTypes(httpCode = 400)

        /**
         * server 500 durumu
         */
        object ServerErrorTypes : NetworkErrorTypes(httpCode = 500)

        object ServerUnavailableErrorTypes : NetworkErrorTypes(httpCode = 503)

        /**
         * server 405 durumu
         */
        object MethodNotAllowedErrorTypes : NetworkErrorTypes(httpCode = 405)

        /**
         * server 401 durumu
         */
         object AuthErrorTypes : NetworkErrorTypes(httpCode = 401)
    }

    /**
     * servisle iletişim bitti ancak gelen response un ayıklanmasında hata var
     */
     object ParsingErrorTypes : ErrorTypes(errorCode = 1000)

    /**
     * http request timeout error
     */
     object TimeoutErrorTypes : ErrorTypes(errorCode = 1001)

     object NoNetworkErrorTypes : ErrorTypes(errorCode = 1002)

    /**
     * bilinmeyen hata türü
     */
    class HoustonWeHaveAProblemErrorTypes(errorCode : Int = 1100) : ErrorTypes(errorCode = errorCode)
}

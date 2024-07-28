package com.example.moviesbaseexample.ui.theme.data.service.interceptor

import android.content.Context
import com.example.moviesbaseexample.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HttpErrorHandler @Inject constructor(@ApplicationContext private val context : Context) {

    /**
     * gelen http hatasının ekranda nasıl görüntüleneceğine karar verir
     *
     * @param ENUseCaseError
     *
     * @return popup da gösterilecek metin
     */
    fun handle(errorTypes : ErrorTypes) : String {

        val errorMessage = StringBuilder()

        when (errorTypes) {

            /**
             * bu hata tipinde obje içerisinde bir mesaj listesi oluyor, bu liste içerisinden metinler alıp, alt alta gösteriyoruz.
             */
            is ErrorTypes.NetworkErrorTypes.ServerMessageErrorTypes -> {
                errorMessage.append(context.getString(R.string.http_error_message_server_unavailable))
            }

            is ErrorTypes.NetworkErrorTypes.ServerErrorTypes -> {

                errorMessage.append(context.getString(R.string.http_error_message_server_unavailable))
            }

            is ErrorTypes.NetworkErrorTypes.ServerUnavailableErrorTypes -> {

                errorMessage.append(context.getString(R.string.http_error_message_network))
            }

            is ErrorTypes.NetworkErrorTypes.MethodNotAllowedErrorTypes -> {

                errorMessage.append(context.getString(R.string.http_error_message_method_not_allowed))
            }

            is ErrorTypes.NetworkErrorTypes.AuthErrorTypes -> {

                errorMessage.append(context.getString(R.string.http_error_message_auth))
            }

            is ErrorTypes.ParsingErrorTypes -> {

                errorMessage.append(context.getString(R.string.http_error_message_timeout))
            }

            /**
             * gerçek 500 hatası [R.string.http_error_message_server] den metni alır
             */
            is ErrorTypes.TimeoutErrorTypes -> {

                errorMessage.append(context.getString(R.string.http_error_message_server))
            }

            /**
             * json parse da hata alınırsa
             */
            is ErrorTypes.NoNetworkErrorTypes -> {

                errorMessage.append(context.getString(R.string.http_error_message_parsing))
            }

            /**
             * tanımlanamayan hata [R.string.http_error_message_unknown] den metni alır
             */
            else -> {

                errorMessage.append(context.getString(R.string.http_error_message_unknown))
            }

        }

        return errorMessage.toString()
    }
}
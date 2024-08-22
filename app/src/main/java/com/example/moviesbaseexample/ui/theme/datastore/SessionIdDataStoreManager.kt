package com.example.moviesbaseexample.ui.theme.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionIdDataStoreManager @Inject constructor(
    context : Context
):BaseDataStoreManager<String>(context){

    private val Context.dataStore : DataStore<SessionIdStore> by dataStore(
        fileName = "session_id.json",
        serializer = SessionIdSerializer
    )
    override suspend fun save(w : String?) {
        context.dataStore.updateData {
            it.copy(sessionId = w)
        }
    }

    suspend fun delete() {

        context.dataStore.updateData {
            it.copy(sessionId = null)
        }
    }

    override suspend fun get() :String?{
        return context.dataStore.data.map { it.sessionId }.first()
        }


        object SessionIdSerializer:Serializer<SessionIdStore> {
            override val defaultValue : SessionIdStore
                get() = SessionIdStore()

            override suspend fun readFrom(input : InputStream) : SessionIdStore {
                return try {
                    Json.decodeFromString(
                        deserializer = SessionIdStore.serializer(),
                        string = input.readBytes().decodeToString()
                    )
                } catch (e:SerializationException){
                    e.printStackTrace()
                    defaultValue
                }
            }

            override suspend fun writeTo(t : SessionIdStore, output : OutputStream) {
                withContext(Dispatchers.IO) {
                    output.write(
                        /* b = */ Json.encodeToString(
                            serializer = SessionIdStore.serializer(),
                            value = t
                        ).encodeToByteArray()
                    )
                }
            }
        }
}
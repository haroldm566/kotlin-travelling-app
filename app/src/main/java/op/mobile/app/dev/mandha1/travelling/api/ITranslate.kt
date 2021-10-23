package op.mobile.app.dev.mandha1.travelling.api

import op.mobile.app.dev.mandha1.travelling.model.Translate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ITranslate {
    @GET("translate?")
    suspend fun getResponse(
        //  Using query annotation to define query params for requests
        @Query("key") key: String,
        @Query("text") text: String,
        @Query("lang") lang: String
    ): Translate
}
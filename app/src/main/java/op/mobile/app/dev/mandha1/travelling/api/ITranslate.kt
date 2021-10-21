package op.mobile.app.dev.mandha1.travelling.api

import op.mobile.app.dev.mandha1.travelling.model.Translate
import retrofit2.http.GET

interface ITranslate {
    @GET("raw")
    suspend fun getResponse(): List<Translate>
}
package op.mobile.app.dev.mandha1.travelling.api

import op.mobile.app.dev.mandha1.travelling.model.Country
import retrofit2.http.GET

interface ICountry {
    @GET("raw")
    suspend fun getResponse(): List<Country>
}

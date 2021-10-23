package op.mobile.app.dev.mandha1.travelling.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class Translate(
    val key: String,
    val text: String,
    val lang: String
)
package op.mobile.app.dev.mandha1.travelling.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Translate(
    val text: String,
    val lang: String
) : Parcelable
package op.mobile.app.dev.mandha1.travelling.login

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import op.mobile.app.dev.mandha1.travelling.database.LoginDb
import op.mobile.app.dev.mandha1.travelling.repository.LoginRepository

class LoginApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { LoginDb.getDatabase(this, applicationScope) }
    val repository by lazy { LoginRepository(database.loginDao()) }
}

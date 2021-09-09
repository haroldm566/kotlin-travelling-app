package op.mobile.app.dev.mandha1.travelling.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import op.mobile.app.dev.mandha1.travelling.database.ILoginDao
import op.mobile.app.dev.mandha1.travelling.model.Login

class LoginRepository (private val loginDao: ILoginDao) {
    val allLoginDetails: Flow<List<Login>> = loginDao.getAll()

    @WorkerThread
    suspend fun insertLoginDetail(login: Login) {
        loginDao.insert(login)
    }
}
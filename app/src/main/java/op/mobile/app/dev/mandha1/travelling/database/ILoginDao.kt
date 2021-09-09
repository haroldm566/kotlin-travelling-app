package op.mobile.app.dev.mandha1.travelling.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import op.mobile.app.dev.mandha1.travelling.model.Login

@Dao
interface ILoginDao {
    @Query("SELECT * FROM login")
    fun getAll(): Flow<List<Login>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(login: Login)

    @Query("DELETE FROM login")
    suspend fun deleteAll()
}
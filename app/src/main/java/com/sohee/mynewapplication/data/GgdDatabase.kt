package com.sohee.mynewapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sohee.mynewapplication.model.Ggd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Ggd::class], version = 1, exportSchema = false)
abstract class GgdDatabase : RoomDatabase() {

    abstract fun ggdDao() : GgdDao
    companion object{
        @Volatile
        private var instance : GgdDatabase? = null

        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        fun getDatabase(context : Context) : GgdDatabase? {
            if(instance == null){
                synchronized(GgdDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GgdDatabase::class.java,
                        "ggd_database"
                    ).addCallback(object : RoomDatabase.Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            fillInDb(context.applicationContext)
                        }
                    }).build()
                }
            }
            return instance
        }
        fun fillInDb(context: Context){
            CoroutineScope(Dispatchers.IO).launch {
                GgdDatabase.getDatabase(context)!!.ggdDao().addUserDb(
                    USER_DATA
                )
            }
        }

    }
}
private val USER_DATA = arrayListOf(
    Ggd(0,"수원","베이글이 맛있었다"),
    Ggd(0,"인천","사람이 많았다")
)
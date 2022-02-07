package com.sohee.mynewapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sohee.mynewapplication.model.Chung2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Chung2::class], version = 1, exportSchema = false)
abstract class Chung2Database : RoomDatabase() {

    abstract fun chung2Dao() : Chung2Dao
    companion object{
        @Volatile
        private var instance : Chung2Database? = null

        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        fun getDatabase(context : Context) : Chung2Database? {
            if(instance == null){
                synchronized(Chung2Database::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Chung2Database::class.java,
                        "chung2_database"
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
                Chung2Database.getDatabase(context)!!.chung2Dao().addUserDb(
                    USER_DATA
                )
            }
        }

    }
}
private val USER_DATA = arrayListOf(
    Chung2(0,"담양","수영장")
)
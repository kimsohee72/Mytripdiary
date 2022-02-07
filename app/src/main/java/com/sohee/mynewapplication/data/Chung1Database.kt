package com.sohee.mynewapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sohee.mynewapplication.model.Chung1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Chung1::class], version = 1, exportSchema = false)
abstract class Chung1Database : RoomDatabase() {

    abstract fun chung1Dao() : Chung1Dao
    companion object{
        @Volatile
        private var instance : Chung1Database? = null

        // 싱글톤으로 생성 (자주 생성 시 성능 손해). 이미 존재할 경우 생성하지 않고 바로 반환
        fun getDatabase(context : Context) : Chung1Database? {
            if(instance == null){
                synchronized(Chung1Database::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Chung1Database::class.java,
                        "chung1_database"
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
                Chung1Database.getDatabase(context)!!.chung1Dao().addUserDb(
                    USER_DATA
                )
            }
        }

    }
}
private val USER_DATA = arrayListOf(
    Chung1(0,"제천","할머니네집")
)